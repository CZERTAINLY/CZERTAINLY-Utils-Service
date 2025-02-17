package com.czertainly.utils.dto;

import com.czertainly.utils.enums.CertificateType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IdentifyCertificateResponseDto {

    @Schema(
            description = "Identified Type of certificate",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Certificate type must not be null")
    private CertificateType certificateType;

}
