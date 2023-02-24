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
import com.czertainly.utils.tools.Pkcs10Tools;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.util.ASN1Dump;
import org.bouncycastle.operator.DefaultSignatureNameFinder;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class Pkcs10RequestUtilsServiceImpl implements RequestUtilsService {

    @Override
    public ParseRequestResponseDto parseRequest(ParseRequestRequestDto parseRequestRequestDto) throws RequestUtilsException {
        ParseRequestResponseDto parseRequestResponseDto = new ParseRequestResponseDto();

        parseRequestResponseDto.setType(RequestType.PKCS10);
        JcaPKCS10CertificationRequest jcaPKCS10CertificationRequest;
        try {
            jcaPKCS10CertificationRequest = new JcaPKCS10CertificationRequest(
                    Base64.getDecoder().decode(
                            Pkcs10Tools.normalize(
                                    parseRequestRequestDto.getRequest()
                            ).getBytes(StandardCharsets.UTF_8)
                    )
            );
        } catch (IOException e) {
            throw new RequestUtilsException(HttpStatus.BAD_REQUEST, RequestUtilsError.REQUEST_PARSING_ERROR, e);
        }
        switch (parseRequestRequestDto.getParseType()) {
            case BASIC -> parseRequestResponseDto.setData(parsePkcs10RequestBasicData(jcaPKCS10CertificationRequest));
            case EXTENDED -> parseRequestResponseDto.setData(parsePkcs10RequestExtendedData(jcaPKCS10CertificationRequest));
            case ASN1 -> parseRequestResponseDto.setData(parsePkcs10RequestAsn1Data(jcaPKCS10CertificationRequest));
            default -> throw new RequestUtilsException(HttpStatus.BAD_REQUEST, RequestUtilsError.REQUEST_PARSING_ERROR);
        }

        return parseRequestResponseDto;
    }

    private Pkcs10RequestBasicData parsePkcs10RequestBasicData(JcaPKCS10CertificationRequest jcaPKCS10CertificationRequest) {
        return new Pkcs10RequestBasicData(
                jcaPKCS10CertificationRequest.getSubject().toString(),
                Pkcs10Tools.extractSanFromCsr(jcaPKCS10CertificationRequest)
        );
    }

    private Pkcs10RequestExtendedData parsePkcs10RequestExtendedData(JcaPKCS10CertificationRequest jcaPKCS10CertificationRequest) throws RequestUtilsException {
        try {
            return new Pkcs10RequestExtendedData(
                    jcaPKCS10CertificationRequest.getSubject().toString(),
                    new DefaultSignatureNameFinder().getAlgorithmName(jcaPKCS10CertificationRequest.getSignatureAlgorithm().getAlgorithm()),
                    jcaPKCS10CertificationRequest.getPublicKey().getAlgorithm(),
                    jcaPKCS10CertificationRequest.getPublicKey().getEncoded(),
                    Pkcs10Tools.extractAttributesFromCsr(jcaPKCS10CertificationRequest)
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

}
