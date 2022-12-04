package com.czertainly.utils.api.v1;

import com.czertainly.utils.dto.ApiErrorResponseDto;
import com.czertainly.utils.dto.oid.OidInfoResponseDto;
import com.czertainly.utils.enums.RequestType;
import com.czertainly.utils.service.OidUtilsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/oid")
@Tag(
        name = "OID Utils API",
        description = "Details about object identifiers like friendly name."
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
public class OidUtilsController {

    @Autowired
    private OidUtilsService oidUtilsService;

    @RequestMapping(
            path = "/{identifier}",
            method = RequestMethod.GET,
            produces = {"application/json"}
    )
    @Operation(
            summary = "Get information and details about the OID number"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OID details retrieved"
                    )
            }
    )
    public OidInfoResponseDto getOidInfo(
            @Parameter(
                    description = "OID number",
                    example = "2.5.4.3"
            )
            @PathVariable String identifier) {
        return  oidUtilsService.getOidInfo(identifier);
    }
}
