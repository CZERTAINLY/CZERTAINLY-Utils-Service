package com.czertainly.utils.service.impl;

import com.czertainly.utils.data.x509.X509CertificateAsn1Data;
import com.czertainly.utils.data.x509.X509CertificateBasicData;
import com.czertainly.utils.data.x509.X509CertificateExtendedData;
import com.czertainly.utils.dto.*;
import com.czertainly.utils.enums.CertificateUtilsError;
import com.czertainly.utils.enums.CertificateType;
import com.czertainly.utils.exception.CertificateUtilsException;
import com.czertainly.utils.service.CertificateUtilsService;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.util.ASN1Dump;
import org.bouncycastle.asn1.x509.GeneralName;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.cert.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CertificateUtilsServiceImpl implements CertificateUtilsService {

    @Override
    public ParseCertificateResponseDto parseCertificate(CertificateType certificateType, ParseCertificateRequestDto parseCertificateRequestDto) throws CertificateUtilsException {
        ParseCertificateResponseDto parseCertificateResponseDto = new ParseCertificateResponseDto();

        switch (certificateType) {
            case X509 -> {
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
                    case ASN1 -> {
                        //parseCertificateResponseDto.setData(new X509CertificateAsn1Data(ASN1Dump.dumpAsString(parseCertificateRequestDto.getCertificate())));
                        parseCertificateResponseDto.setData(parseX509CertificateAsn1Data(x509Certificate));
                    }
                    default -> throw new CertificateUtilsException(HttpStatus.BAD_REQUEST, CertificateUtilsError.CERTIFICATE_PARSE_TYPE_UNSUPPORTED);
                }
            }
            case SSH -> throw new CertificateUtilsException(HttpStatus.BAD_REQUEST, CertificateUtilsError.CERTIFICATE_TYPE_UNSUPPORTED, "Unsupported certificate type SSH");
            default -> throw new CertificateUtilsException(HttpStatus.BAD_REQUEST, CertificateUtilsError.CERTIFICATE_TYPE_UNSUPPORTED);
        }

        return parseCertificateResponseDto;
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

    private X509CertificateExtendedData parseX509CertificateExtendedData(X509Certificate x509Certificate) throws java.security.cert.CertificateParsingException {
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
                extractSanFromCertificate(x509Certificate)
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

    private List<String> extractSanFromCertificate(X509Certificate x509Certificate) throws CertificateParsingException {
        List<String> sans = new ArrayList<>();
        Collection<List<?>> sanList = x509Certificate.getSubjectAlternativeNames();
        for (List<?> san : sanList) {
            String title = "";
            Integer type = (Integer) san.get(0);
            if (type == GeneralName.dNSName) {
                title = "DNS";
            } else if (type == GeneralName.iPAddress) {
                title = "IP Address";
                // name.toASN1Primitive();
            } else if (type == GeneralName.otherName) {
                title = "Other Name";
            }
            sans.add(title + ": " + san.get(1));
        }
        return sans;
    }
}
