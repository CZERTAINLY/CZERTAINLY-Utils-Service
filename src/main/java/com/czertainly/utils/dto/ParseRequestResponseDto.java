package com.czertainly.utils.dto;

import com.czertainly.utils.data.RequestData;
import com.czertainly.utils.data.pkcs10.Pkcs10RequestAsn1Data;
import com.czertainly.utils.data.pkcs10.Pkcs10RequestBasicData;
import com.czertainly.utils.data.pkcs10.Pkcs10RequestExtendedData;
import com.czertainly.utils.enums.RequestType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParseRequestResponseDto {

    @Schema(
            description = "Type of the certification request parsed",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private RequestType type;

    @Schema(
            description = "Certification request data",
            requiredMode = Schema.RequiredMode.REQUIRED,
            oneOf = { Pkcs10RequestBasicData.class, Pkcs10RequestExtendedData.class, Pkcs10RequestAsn1Data.class }
    )
    private RequestData data;

}
