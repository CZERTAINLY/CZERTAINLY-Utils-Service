package com.czertainly.utils.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RandomCertificateResponseDto {

    @Schema(
            description = "Base64-encoded certificate",
            requiredMode = Schema.RequiredMode.REQUIRED,
            implementation = String.class
    )
    private byte[] certificate;

}
