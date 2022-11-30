package com.czertainly.utils.dto;

import com.czertainly.utils.data.RequestData;
import com.czertainly.utils.enums.RequestType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParseRequestResponseDto {

    private RequestType type;
    private RequestData data;

}
