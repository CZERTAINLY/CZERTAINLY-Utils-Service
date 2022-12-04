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
public class Pkcs10RequestAsn1Data extends RequestData {

    @Schema(
            description = "ASN.1 dump the PKCS#10 certification request",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String asn1dump;

}
