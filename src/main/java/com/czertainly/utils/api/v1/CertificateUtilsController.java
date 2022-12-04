package com.czertainly.utils.api.v1;

import com.czertainly.utils.dto.ApiErrorResponseDto;
import com.czertainly.utils.dto.ParseCertificateRequestDto;
import com.czertainly.utils.dto.ParseCertificateResponseDto;
import com.czertainly.utils.enums.CertificateType;
import com.czertainly.utils.exception.CertificateUtilsException;
import com.czertainly.utils.service.CertificateUtilsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    private CertificateUtilsService certificateUtilsService;

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
        return  certificateUtilsService.parseCertificate(certificateType, request);
    }
}
