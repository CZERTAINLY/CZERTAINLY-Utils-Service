package com.czertainly.utils.service.impl;

import com.czertainly.utils.data.pkcs10.Pkcs10RequestAsn1Data;
import com.czertainly.utils.data.pkcs10.Pkcs10RequestBasicData;
import com.czertainly.utils.data.pkcs10.Pkcs10RequestExtendedData;
import com.czertainly.utils.dto.ParseRequestRequestDto;
import com.czertainly.utils.dto.ParseRequestResponseDto;
import com.czertainly.utils.enums.RequestType;
import com.czertainly.utils.enums.RequestUtilsError;
import com.czertainly.utils.exception.RequestUtilsException;
import com.czertainly.utils.service.RequestUtilsService;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.pkcs.Attribute;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.util.ASN1Dump;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.operator.DefaultSignatureNameFinder;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestUtilsServiceImpl implements RequestUtilsService {

    @Override
    public ParseRequestResponseDto parseRequest(RequestType requestType, ParseRequestRequestDto parseRequestRequestDto) throws RequestUtilsException {
        ParseRequestResponseDto parseRequestResponseDto = new ParseRequestResponseDto();

        switch (requestType) {
            case PKCS10 -> {
                parseRequestResponseDto.setType(RequestType.PKCS10);
                JcaPKCS10CertificationRequest jcaPKCS10CertificationRequest;
                try {
                    jcaPKCS10CertificationRequest = new JcaPKCS10CertificationRequest(parseRequestRequestDto.getRequest());
                } catch (IOException e) {
                    throw new RequestUtilsException(HttpStatus.BAD_REQUEST, RequestUtilsError.REQUEST_PARSING_ERROR, e);
                }
                switch (parseRequestRequestDto.getParseType()) {
                    case BASIC -> parseRequestResponseDto.setData(parsePkcs10RequestBasicData(jcaPKCS10CertificationRequest));
                    case EXTENDED -> parseRequestResponseDto.setData(parsePkcs10RequestExtendedData(jcaPKCS10CertificationRequest));
                    case ASN1 -> parseRequestResponseDto.setData(parsePkcs10RequestAsn1Data(jcaPKCS10CertificationRequest));
                    default -> throw new RequestUtilsException(HttpStatus.BAD_REQUEST, RequestUtilsError.REQUEST_PARSING_ERROR);
                }
            }
            default -> throw new RequestUtilsException(HttpStatus.BAD_REQUEST, RequestUtilsError.REQUEST_PARSING_ERROR);
        }

        return parseRequestResponseDto;
    }

    private Pkcs10RequestBasicData parsePkcs10RequestBasicData(JcaPKCS10CertificationRequest jcaPKCS10CertificationRequest) {
        return new Pkcs10RequestBasicData(
                jcaPKCS10CertificationRequest.getSubject().toString(),
                extractSanFromCsr(jcaPKCS10CertificationRequest)
        );
    }

    private Pkcs10RequestExtendedData parsePkcs10RequestExtendedData(JcaPKCS10CertificationRequest jcaPKCS10CertificationRequest) throws RequestUtilsException {
        try {
            return new Pkcs10RequestExtendedData(
                    jcaPKCS10CertificationRequest.getSubject().toString(),
                    new DefaultSignatureNameFinder().getAlgorithmName(jcaPKCS10CertificationRequest.getSignatureAlgorithm().getAlgorithm()),
                    jcaPKCS10CertificationRequest.getPublicKey().getAlgorithm(),
                    jcaPKCS10CertificationRequest.getPublicKey().getEncoded(),
                    extractAttributesFromCsr(jcaPKCS10CertificationRequest)
            );
        } catch (InvalidKeyException e) {
            throw new RequestUtilsException(HttpStatus.BAD_REQUEST, RequestUtilsError.REQUEST_INVALID_KEY, e);
        } catch (NoSuchAlgorithmException e) {
            throw new RequestUtilsException(HttpStatus.BAD_REQUEST, RequestUtilsError.REQUEST_INVALID_ALGORITHM, e);
        }
    }

    private Pkcs10RequestAsn1Data parsePkcs10RequestAsn1Data(JcaPKCS10CertificationRequest jcaPKCS10CertificationRequest) throws RequestUtilsException {
        try {
            ASN1Primitive derObject = ASN1Primitive.fromByteArray(jcaPKCS10CertificationRequest.getEncoded());

            // double-check the size of der object
            if (derObject.getEncoded().length < jcaPKCS10CertificationRequest.getEncoded().length) {
                throw new RequestUtilsException(
                        HttpStatus.BAD_REQUEST,
                        RequestUtilsError.REQUEST_PARSING_ERROR,
                        "ASN1 dump exception"
                );
            }
            return new Pkcs10RequestAsn1Data(ASN1Dump.dumpAsString(derObject, true));
        } catch (IOException e) {
            throw new RequestUtilsException(HttpStatus.BAD_REQUEST, RequestUtilsError.REQUEST_ASN1_DUMP_ERROR, e);
        }

    }

    private List<String> extractSanFromCsr(JcaPKCS10CertificationRequest csr) {
        List<String> sans = new ArrayList<>();
        Attribute[] certAttributes = csr.getAttributes();
        for (Attribute attribute : certAttributes) {
            if (attribute.getAttrType().equals(PKCSObjectIdentifiers.pkcs_9_at_extensionRequest)) {
                Extensions extensions = Extensions.getInstance(attribute.getAttrValues().getObjectAt(0));
                GeneralNames gns = GeneralNames.fromExtensions(extensions, Extension.subjectAlternativeName);
                GeneralName[] names = gns.getNames();
                for (GeneralName name : names) {
                    //logger.info("Type: " + name.getTagNo() + " | Name: " + name.getName());
                    String title = "";
                    if (name.getTagNo() == GeneralName.dNSName) {
                        title = "DNS";
                    } else if (name.getTagNo() == GeneralName.iPAddress) {
                        title = "IP Address";
                        // name.toASN1Primitive();
                    } else if (name.getTagNo() == GeneralName.otherName) {
                        title = "Other Name";
                    }
                    sans.add(title + ": " + name.getName());
                }
            }
        }
        return sans;
    }

    private List<String> extractAttributesFromCsr(JcaPKCS10CertificationRequest csr) {
        List<String> attributes = new ArrayList<>();
        Attribute[] certAttributes = csr.getAttributes();
        for (Attribute attribute : certAttributes) {
            StringBuilder values = new StringBuilder();
            for (ASN1Encodable value : attribute.getAttributeValues()) {
                values.append(value);
                values.append(", ");
            }
            attributes.add(attribute.getAttrType().toString() + ": " + values.substring(0, values.length() - 2));
        }
        return attributes;
    }

}
