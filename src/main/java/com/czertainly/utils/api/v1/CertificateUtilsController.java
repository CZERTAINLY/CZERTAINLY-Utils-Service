package com.czertainly.utils.api.v1;

import com.czertainly.utils.dto.ParseCertificateRequestDto;
import com.czertainly.utils.dto.ParseCertificateResponseDto;
import com.czertainly.utils.enums.CertificateType;
import com.czertainly.utils.exception.CertificateParsingException;
import com.czertainly.utils.service.CertificateUtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/certificate")
public class CertificateUtilsController {

    @Autowired
    private CertificateUtilsService certificateUtilsService;

    @RequestMapping(
            path = "/{certificateType}/parse",
            method = RequestMethod.POST,
            consumes = {"application/json"},
            produces = {"application/json"}
    )
    public ParseCertificateResponseDto parseCertificate(
            @PathVariable CertificateType certificateType,
            @Valid @RequestBody ParseCertificateRequestDto request) throws CertificateParsingException {
        return  certificateUtilsService.parseCertificate(certificateType, request);
    }
}
