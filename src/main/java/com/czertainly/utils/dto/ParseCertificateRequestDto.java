package com.czertainly.utils.dto;

import com.czertainly.utils.enums.ParseType;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ParseCertificateRequestDto {

    @Schema(
            description = "Base64-encoded certificate data",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "MIIFcDCCA1igAwIBAgIUQ8qEcLshApVCEh1+wrszGBW9mHEwDQYJKoZIhvcNAQELBQAwNjEWMBQGA1UEAwwNTWFuYWdlbWVudCBDQTEcMBoGA1UECgwTM0tleSBDb21wYW55IHMuci5vLjAeFw0xOTA1MDgxMjUxNTZaFw0yOTA1MDUxMjUxNTZaMDYxFjAUBgNVBAMMDU1hbmFnZW1lbnQgQ0ExHDAaBgNVBAoMEzNLZXkgQ29tcGFueSBzLnIuby4wggIiMA0GCSqGSIb3DQEBAQUAA4ICDwAwggIKAoICAQDZ3+k1GPIGkimNd9MEw9cWCYYzUh11mv5NVJsw+uRbzxQsP7oqeQQdMxHqhSP0eGg6UxqQ6PQSCCkHvMtrpFGX+eMZuB/z1vFodHdBCUTNOUr6c0c+wMGuhJoYPAwY/OW5YDrw6r1oxtKbbD7HMOPE1tt5twGQTuy4BBQEWp/SSHs39UoDtLqbLwzuekv1GcQxCjWpHjX5fIbIf7JZfXrQ8WnMkHInbow9tQIAOtKyDJ32Y2DUSgK4v1zp0UtnABXBaVyih3am/Sghv6RLjP0xEnWpRtAt4HqtSFFMVCEkgqIEU7Ev9eTuGJvy76lvvrPpr5dow35FWz8tZcPwPwXKMqaHRPPIjS3HDKi/BOmqOGpnANcJyn4X+/NsOTNkIXLAj9H5t4deQR4jckSwNcPeh/5zXgqmPU5GLhhis+zu0TxfMKuEovXHOWlfiyU4Xzx6MAsjb9dPRqjR2ku7TNc9megR7yDV809eOa6M5roVZB2L71/rKKkzLS/u9kImShhtqlxQ9XckN0Op2GppPYIO8rzmZHzCv+gb/NIeVZ+UbTIbANA3NYXWjJlMzgyf/Dub0vZbhRxLpKRcziOZN8STB8p+d6kChct1u10SZwQglMUM8WPo8t+CHPw/AvApdwD2z45CktFiR6V83gqvqGdDkaCg/CdpnzltHN/Zr4NDpwIDAQABo3YwdDAPBgNVHRMBAf8EBTADAQH/MB8GA1UdIwQYMBaAFNSuk5EY8MsHAKr7hYgWlF7vvWIzMBEGA1UdIAQKMAgwBgYEVR0gADAdBgNVHQ4EFgQU1K6TkRjwywcAqvuFiBaUXu+9YjMwDgYDVR0PAQH/BAQDAgGGMA0GCSqGSIb3DQEBCwUAA4ICAQDZZN0oD9kIZ3puvGAcxwGKZ1DNCNtnNut5k4E+hBJc5TnMskfyM2drMi0vFvFXXJlu5NBprS0jw+x6XHYTC6BXwe4LwZib/Yr/6ROv73nC/ecJK2w2n6PIWehs7qwigakzh1tf8iypj8kTl2taMMEgzO7bF9CgLQnm0eVjuPzvRRSsZ0dbBisKwkOTpt9aYG/WsuLZ7LmFKBvpVSvJJBYmgGmeqkx0Pijdg9PEkUP3Ek2tN5DoOFQzlPicujZ5p3akfTG4L2PNuZWf52zmY5sXmxEr0zoyeuXrIM424c5qvh82yvP2M2AXty8s2O1jW11snemSLPVhxsnUlfMqic8rfO0QTPJg3WU/SmHpvmcyzUGpwYfm1wdOS2e9Ow5fSxR6TyHc14Lox4yXCLzGkPdBPcj8jG1qJ9Pqkwpaz9hGC3elTd3TDCocDRlrz6OQfE4j1OKCeaiuHCjZO3v3e0VxWh7T9Synez/thxi/UPKBL8Gh103AUCOQYGJkIdIKSyQusnfxCj93YE5cJUC9rPfrqe1Rjct3nGNj5E5wnVwU4HtvosPw6mGtPuUNA3fhvntZN+P+1hi9+322s1s0ttdBYjIYNKVRqiuGB8NH4xX8R6husmmRbOI1OFdtaruc30rSo1Y3/iYWdWaA1zokkYOztcjbffoznZlIBawmif4oKw=="
    )
    @NotEmpty(message = "Certificate must not be empty")
    byte[] certificate;

    @Schema(
            description = "Type of the parsing requested",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Parse type must not be null")
    ParseType parseType;

    public byte[] getCertificate() {
        return certificate;
    }

    public void setCertificate(byte[] certificate) {
        this.certificate = certificate;
    }

    public ParseType getParseType() {
        return parseType;
    }

    public void setParseType(ParseType parseType) {
        this.parseType = parseType;
    }
}
