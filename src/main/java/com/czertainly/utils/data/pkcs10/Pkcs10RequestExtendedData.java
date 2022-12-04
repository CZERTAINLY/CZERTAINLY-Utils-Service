package com.czertainly.utils.data.pkcs10;

import com.czertainly.utils.data.RequestData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pkcs10RequestExtendedData extends RequestData {

    @Schema(
            description = "Subject of the PKCS#10 certification request",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "CN=test"
    )
    private String subject;

    @Schema(
            description = "Signature algorithm for the certification request",
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
            description = "List of certification request attributes",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "2.5.29.17: samuser/K25_CQEQZR3UU45UXWBQJBXQNN6ZVH42CCSH"
    )
    private List<String> attributes;

}
