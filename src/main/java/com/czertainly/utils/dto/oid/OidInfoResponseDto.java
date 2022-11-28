package com.czertainly.utils.dto.oid;

public class OidInfoResponseDto {

    String identifier;
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
