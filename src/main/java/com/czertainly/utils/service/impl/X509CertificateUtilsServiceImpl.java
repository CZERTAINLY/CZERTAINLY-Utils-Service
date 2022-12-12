package com.czertainly.utils.service.impl;

import com.czertainly.utils.data.x509.X509CertificateAsn1Data;
import com.czertainly.utils.data.x509.X509CertificateBasicData;
import com.czertainly.utils.data.x509.X509CertificateExtendedData;
import com.czertainly.utils.dto.*;
import com.czertainly.utils.enums.CertificateUtilsError;
import com.czertainly.utils.enums.CertificateType;
import com.czertainly.utils.exception.CertificateUtilsException;
import com.czertainly.utils.service.CertificateUtilsService;
import com.czertainly.utils.tools.X509Tools;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.util.ASN1Dump;
import org.bouncycastle.operator.OperatorCreationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.*;

@Service
public class X509CertificateUtilsServiceImpl implements CertificateUtilsService {

    private static final Logger logger = LoggerFactory.getLogger(X509CertificateUtilsServiceImpl.class);

    @Override
    public ParseCertificateResponseDto parseCertificate(ParseCertificateRequestDto parseCertificateRequestDto) throws CertificateUtilsException {
        ParseCertificateResponseDto parseCertificateResponseDto = new ParseCertificateResponseDto();

        parseCertificateResponseDto.setType(CertificateType.X509);
        X509Certificate x509Certificate;
        try {
            x509Certificate = (X509Certificate) CertificateFactory.getInstance("X.509")
                    .generateCertificate(new ByteArrayInputStream(parseCertificateRequestDto.getCertificate()));
        } catch (CertificateException e) {
            throw new CertificateUtilsException(HttpStatus.BAD_REQUEST, CertificateUtilsError.CERTIFICATE_PARSING_ERROR, e);
        }
        switch (parseCertificateRequestDto.getParseType()) {
            case BASIC -> parseCertificateResponseDto.setData(parseX509CertificateBasicData(x509Certificate));
            case EXTENDED -> {
                try {
                    parseCertificateResponseDto.setData(parseX509CertificateExtendedData(x509Certificate));
                } catch (java.security.cert.CertificateParsingException e) {
                    throw new CertificateUtilsException(HttpStatus.BAD_REQUEST, CertificateUtilsError.CERTIFICATE_PARSING_ERROR, e);
                }
            }
            case ASN1 -> parseCertificateResponseDto.setData(parseX509CertificateAsn1Data(x509Certificate));
            default -> throw new CertificateUtilsException(HttpStatus.BAD_REQUEST, CertificateUtilsError.CERTIFICATE_PARSE_TYPE_UNSUPPORTED);
        }

        return parseCertificateResponseDto;
    }

    @Override
    public RandomCertificateResponseDto randomCertificate() throws CertificateUtilsException {
        RandomCertificateResponseDto randomCertificateResponseDto = new RandomCertificateResponseDto();

        try {
            randomCertificateResponseDto.setCertificate(X509Tools.generateRandomX509Certificate());
        } catch (NoSuchAlgorithmException | OperatorCreationException | CertificateException | SignatureException | InvalidKeyException |
                 NoSuchProviderException e) {
            throw new CertificateUtilsException(HttpStatus.BAD_REQUEST, CertificateUtilsError.CERTIFICATE_RANDOM_GEN_ERROR, e);
        }

        return randomCertificateResponseDto;
    }

    @Override
    public boolean identifyCertificate(IdentifyCertificateRequestDto identifyCertificateRequestDto) {
        try {
            CertificateFactory.getInstance("X.509")
                    .generateCertificate(new ByteArrayInputStream(identifyCertificateRequestDto.getData()));
        } catch (CertificateException e) {
            logger.debug("Identification of X.509 certificate failed", e);
            return false;
        }

        return true;
    }

    private X509CertificateBasicData parseX509CertificateBasicData(X509Certificate x509Certificate) {
        return new X509CertificateBasicData(
                x509Certificate.getSubjectX500Principal().getName(),
                x509Certificate.getIssuerX500Principal().getName(),
                x509Certificate.getNotBefore().getTime(),
                x509Certificate.getNotAfter().getTime(),
                x509Certificate.getSerialNumber().toString(16)
        );
    }

    private X509CertificateExtendedData parseX509CertificateExtendedData(X509Certificate x509Certificate) throws CertificateParsingException {
        return new X509CertificateExtendedData(
                x509Certificate.getSubjectX500Principal().getName(),
                x509Certificate.getIssuerX500Principal().getName(),
                x509Certificate.getNotBefore().getTime(),
                x509Certificate.getNotAfter().getTime(),
                x509Certificate.getSerialNumber().toString(16),
                x509Certificate.getVersion(),
                x509Certificate.getSigAlgName(),
                x509Certificate.getPublicKey().getAlgorithm(),
                x509Certificate.getPublicKey().getEncoded(),
                X509Tools.extractSanFromCertificate(x509Certificate)
        );
    }

    private X509CertificateAsn1Data parseX509CertificateAsn1Data(X509Certificate x509Certificate) throws CertificateUtilsException {
        try {
            ASN1Primitive derObject = ASN1Primitive.fromByteArray(x509Certificate.getEncoded());

            // double-check the size of der object
            if (derObject.getEncoded().length < x509Certificate.getEncoded().length) {
                throw new CertificateUtilsException(
                        HttpStatus.BAD_REQUEST,
                        CertificateUtilsError.CERTIFICATE_PARSING_ERROR,
                        "ASN1 dump exception"
                );
            }
            return new X509CertificateAsn1Data(ASN1Dump.dumpAsString(derObject, true));
        } catch (CertificateEncodingException | IOException e) {
            throw new CertificateUtilsException(HttpStatus.BAD_REQUEST, CertificateUtilsError.CERTIFICATE_ASN1_DUMP_ERROR, e);
        }
    }


}
