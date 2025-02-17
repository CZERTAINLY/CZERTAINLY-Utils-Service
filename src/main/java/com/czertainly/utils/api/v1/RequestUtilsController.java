package com.czertainly.utils.api.v1;

import com.czertainly.utils.dto.ApiErrorResponseDto;
import com.czertainly.utils.dto.ParseRequestRequestDto;
import com.czertainly.utils.dto.ParseRequestResponseDto;
import com.czertainly.utils.enums.RequestType;
import com.czertainly.utils.enums.RequestUtilsError;
import com.czertainly.utils.exception.RequestUtilsException;
import com.czertainly.utils.service.impl.Pkcs10RequestUtilsServiceImpl;
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
@RequestMapping("/v1/request")
@Tag(
        name = "Certification Request Utils API",
        description = "Certification request handling support tools."
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
public class RequestUtilsController {

    @Autowired
    private Pkcs10RequestUtilsServiceImpl pkcs10RequestUtilsService;

    @RequestMapping(
            path = "/{requestType}/parse",
            method = RequestMethod.POST,
            consumes = {"application/json"},
            produces = {"application/json"}
    )
    @Operation(
            summary = "Parse the certification request and provides details about its attributes"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Certification request parsed and information retrieved"
                    )
            }
    )
    public ParseRequestResponseDto parseRequest(
            @Parameter(
                    description = "Certification request type",
                    schema = @Schema(implementation = RequestType.class)
            )
            @PathVariable RequestType requestType,
            @Valid @RequestBody ParseRequestRequestDto request) throws RequestUtilsException {
        switch (requestType) {
            case PKCS10:
                return pkcs10RequestUtilsService.parseRequest(request);
            default:
                throw new RequestUtilsException(HttpStatus.BAD_REQUEST, RequestUtilsError.REQUEST_TYPE_UNSUPPORTED);
        }
    }

}
