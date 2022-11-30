package com.czertainly.utils.dto;

import com.czertainly.utils.enums.ParseType;
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

    @NotEmpty(message = "Certification request must not be empty")
    byte[] request;

    @NotNull(message = "Parse type must not be null")
    ParseType parseType;

}
