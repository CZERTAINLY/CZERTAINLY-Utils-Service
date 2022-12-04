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
public class Pkcs10RequestBasicData extends RequestData {

    @Schema(
            description = "Subject of the PKCS#10 certification request",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "CN=test"
    )
    private String subject;

    @Schema(
            description = "List of subject alternative names",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            example = "DNS: test.example.com"
    )
    private List<String> sans;

}
