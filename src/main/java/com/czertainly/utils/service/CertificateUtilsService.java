package com.czertainly.utils.service;

import com.czertainly.utils.dto.IdentifyCertificateRequestDto;
import com.czertainly.utils.dto.ParseCertificateRequestDto;
import com.czertainly.utils.dto.ParseCertificateResponseDto;
import com.czertainly.utils.dto.RandomCertificateResponseDto;
import com.czertainly.utils.enums.CertificateType;
import com.czertainly.utils.exception.CertificateUtilsException;

public interface CertificateUtilsService {

    ParseCertificateResponseDto parseCertificate(ParseCertificateRequestDto parseCertificateRequestDto) throws CertificateUtilsException;

    RandomCertificateResponseDto randomCertificate() throws CertificateUtilsException;

    boolean identifyCertificate(IdentifyCertificateRequestDto identifyCertificateRequestDto);
}
