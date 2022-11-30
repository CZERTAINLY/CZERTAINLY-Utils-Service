package com.czertainly.utils.service;

import com.czertainly.utils.dto.ParseCertificateRequestDto;
import com.czertainly.utils.dto.ParseCertificateResponseDto;
import com.czertainly.utils.enums.CertificateType;
import com.czertainly.utils.exception.CertificateUtilsException;

public interface CertificateUtilsService {

    ParseCertificateResponseDto parseCertificate(CertificateType certificateType, ParseCertificateRequestDto parseCertificateRequestDto) throws CertificateUtilsException;

}
