package com.czertainly.utils.dto;

import com.czertainly.utils.enums.ParseType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParseRequestRequestDto {

    @Schema(
            description = "Base64-encoded certification request data",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "MIICvTCCAaUCAQAwQTEQMA4GA1UEAwwHc2FtdXNlcjEtMCsGA1UEAwwkSzI1X0NRRVFaUjNVVTQ1VVhXQlFKQlhRTk42WlZINDJDQ1NIMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAukV6HeI/Rhg5uwRPnLXSAB74dgTxJBNGuLx1CFuv3AdhtOwLixcGUV1a/wA+VpvQUkUGa6RXpetnqxpHCiiq40Kv5ZdnsWvmORfmb+XJ6/06vajZfgOYst2g4CoNaFDhjRcvxa3vVx+phJnxZzrh2jYPPnopDcH19mK0u3yrCB3rsw3i47GT0QgSYW/uXyJzERRBjZ+DFcVNU50OfsngckCXaigysayg/BAUfeX9qjQoqEBbmP49cWPzXv7VMy8G9l9znMWAzqSLVSSJz+XtqwnIsQFsHRLTZvPNefCamtY+OVS3quEva/M+HRK4s2lHF8W0YPHDSbttVtF+u1bLqQIDAQABoDcwNQYDVR0RMS4MLHNhbXVzZXIvSzI1X0NRRVFaUjNVVTQ1VVhXQlFKQlhRTk42WlZINDJDQ1NIMA0GCSqGSIb3DQEBCwUAA4IBAQAOkdQ9ZcGnxBq8iGXKotebInOBSXLTM+6DkHF/r5sctNcdUUvfww0vmYheKZdcIbPqWRqATxLHKYTBDjHTGwsm8gxOJTJwNZ8BW2g2Lgsf+29kOlxH+X2P98XcNkVLbrkncKCjvIMnaHsv1Qg11rRAI1Im1uqtCtKwiKKAD1Sd8HIRFqG23UB8Uy4lhgaN4MLe+fQsY5lV0ohT2abe2j0jqqTz6yQZJBa53ZDPlsVVsyaA12Nt73gMqauiOqRcye4JKWZlpRMCkCIz+GorKxuZ+fYYj9uYBdXWSpRjHtFXrMN5aAamaJ98Dd42K4m5kP7k3Wp/HfISFezn/HyCvaW8"
    )
    @NotEmpty(message = "Certification request must not be empty")
    byte[] request;

    @Schema(
            description = "Type of the parsing requested",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Parse type must not be null")
    ParseType parseType;

}
