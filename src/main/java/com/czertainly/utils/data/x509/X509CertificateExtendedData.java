package com.czertainly.utils.data.x509;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class X509CertificateExtendedData extends X509CertificateBasicData {

    @Schema(
            description = "Version of the X.509 certificate",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "3"
    )
    private int version;

    @Schema(
            description = "Signature algorithm for the certificate",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "SHA256withRSA"
    )
    private String signatureAlgorithm;

    @Schema(
            description = "Public key algorithm",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "RSA"
    )
    private String algorithm;

    @Schema(
            description = "Base64-encoded public key",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEA2d/pNRjyBpIpjXfTBMPXFgmGM1IddZr+TVSbMPrkW88ULD+6KnkEHTMR6oUj9HhoOlMakOj0EggpB7zLa6RRl/njGbgf89bxaHR3QQlEzTlK+nNHPsDBroSaGDwMGPzluWA68Oq9aMbSm2w+xzDjxNbbebcBkE7suAQUBFqf0kh7N/VKA7S6my8M7npL9RnEMQo1qR41+XyGyH+yWX160PFpzJByJ26MPbUCADrSsgyd9mNg1EoCuL9c6dFLZwAVwWlcood2pv0oIb+kS4z9MRJ1qUbQLeB6rUhRTFQhJIKiBFOxL/Xk7hib8u+pb76z6a+XaMN+RVs/LWXD8D8FyjKmh0TzyI0txwyovwTpqjhqZwDXCcp+F/vzbDkzZCFywI/R+beHXkEeI3JEsDXD3of+c14Kpj1ORi4YYrPs7tE8XzCrhKL1xzlpX4slOF88ejALI2/XT0ao0dpLu0zXPZnoEe8g1fNPXjmujOa6FWQdi+9f6yipMy0v7vZCJkoYbapcUPV3JDdDqdhqaT2CDvK85mR8wr/oG/zSHlWflG0yGwDQNzWF1oyZTM4Mn/w7m9L2W4UcS6SkXM4jmTfEkwfKfnepAoXLdbtdEmcEIJTFDPFj6PLfghz8PwLwKXcA9s+OQpLRYkelfN4Kr6hnQ5GgoPwnaZ85bRzf2a+DQ6cCAwEAAQ=="
    )
    private byte[] publicKey;

    @Schema(
            description = "List of subject alternative names",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "DNS: test.example.com"
    )
    private List<String> sans;

    public X509CertificateExtendedData(String subject, String issuer, long validFrom, long validTo, String serialNumber, int version, String signatureAlgorithm, String algorithm, byte[] publicKey, List<String> sans) {
        super(subject, issuer, validFrom, validTo, serialNumber);
        this.version = version;
        this.signatureAlgorithm = signatureAlgorithm;
        this.algorithm = algorithm;
        this.publicKey = publicKey;
        this.sans = sans;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    public void setSignatureAlgorithm(String signatureAlgorithm) {
        this.signatureAlgorithm = signatureAlgorithm;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }

    public List<String> getSans() {
        return sans;
    }

    public void setSans(List<String> sans) {
        this.sans = sans;
    }
}
