package com.czertainly.utils.dto.oid;

import io.swagger.v3.oas.annotations.media.Schema;

public class OidInfoResponseDto {

    @Schema(
            description = "OID number",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "2.5.4.3"
    )
    String identifier;

    @Schema(
            description = "Name of the OID number, if defined",
            requiredMode = Schema.RequiredMode.REQUIRED,
            nullable = true,
            example = "Common Name"
    )
    String name;

    public OidInfoResponseDto(String identifier, String name) {
        this.identifier = identifier;
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
