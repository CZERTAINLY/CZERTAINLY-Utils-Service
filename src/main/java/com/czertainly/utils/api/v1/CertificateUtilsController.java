package com.czertainly.utils.api.v1;

import com.czertainly.utils.dto.*;
import com.czertainly.utils.enums.CertificateType;
import com.czertainly.utils.enums.CertificateUtilsError;
import com.czertainly.utils.exception.CertificateUtilsException;
import com.czertainly.utils.service.impl.X509CertificateUtilsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/certificate")
@Tag(
        name = "Certificate Utils API",
        description = "Certificate handling support tools that contains for example parsing of certificate in various formats."
)
@ApiResponses(
        value = {
                @ApiResponse(
                        responseCode = "400",
                        description = "Bad Request",
                        content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class))
                ),
                @ApiResponse(
                        responseCode = "500",
                        description = "Internal Server Error",
                        content = @Content(schema = @Schema(implementation = ApiErrorResponseDto.class))
                )
        }
)
public class CertificateUtilsController {

    @Autowired
    private X509CertificateUtilsServiceImpl x509CertificateUtilsService;

    @RequestMapping(
            path = "/{certificateType}/parse",
            method = RequestMethod.POST,
            consumes = {"application/json"},
            produces = {"application/json"}
    )
    @Operation(
            summary = "Parse the certificate and provides details about the certificate attributes"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Certificate parsed and information retrieved"
                    )
            }
    )
    public ParseCertificateResponseDto parseCertificate(
            @Parameter(
                    description = "Certificate type",
                    schema = @Schema(implementation = CertificateType.class)
            )
            @PathVariable CertificateType certificateType,
            @Valid @RequestBody ParseCertificateRequestDto request) throws CertificateUtilsException {
        switch (certificateType) {
            case X509:
                return x509CertificateUtilsService.parseCertificate(request);
            default:
                throw new CertificateUtilsException(HttpStatus.BAD_REQUEST, CertificateUtilsError.CERTIFICATE_TYPE_UNSUPPORTED);
        }
    }

    @RequestMapping(
            path = "/{certificateType}/random",
            method = RequestMethod.GET,
            produces = {"application/json"}
    )
    @Operation(
            summary = "Generate random certificate"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Certificate generated"
                    )
            }
    )
    public RandomCertificateResponseDto randomCertificate(
            @Parameter(
                    description = "Certificate type",
                    schema = @Schema(implementation = CertificateType.class)
            )
            @PathVariable CertificateType certificateType) throws CertificateUtilsException {
        switch (certificateType) {
            case X509:
                return x509CertificateUtilsService.randomCertificate();
            default:
                throw new CertificateUtilsException(HttpStatus.BAD_REQUEST, CertificateUtilsError.CERTIFICATE_TYPE_UNSUPPORTED);
        }
    }

    @RequestMapping(
            path = "/identify",
            method = RequestMethod.POST,
            consumes = {"application/json"},
            produces = {"application/json"}
    )
    @Operation(
            summary = "Identify certificate type"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Certificate identified"
                    )
            }
    )
    public IdentifyCertificateResponseDto identifyCertificate(
            @Valid @RequestBody IdentifyCertificateRequestDto request) throws CertificateUtilsException {
        IdentifyCertificateResponseDto response = new IdentifyCertificateResponseDto();

        if (x509CertificateUtilsService.identifyCertificate(request)) {
            response.setCertificateType(CertificateType.X509);
        } else {
            throw new CertificateUtilsException(HttpStatus.BAD_REQUEST, CertificateUtilsError.CERTIFICATE_IDENTIFY_UNKNOWN);
        }

        return response;
    }

}
