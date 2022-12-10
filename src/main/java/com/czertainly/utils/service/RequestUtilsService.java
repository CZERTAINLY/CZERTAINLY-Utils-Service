package com.czertainly.utils.service;

import com.czertainly.utils.dto.ParseRequestRequestDto;
import com.czertainly.utils.dto.ParseRequestResponseDto;
import com.czertainly.utils.enums.RequestType;
import com.czertainly.utils.exception.RequestUtilsException;

public interface RequestUtilsService {

    ParseRequestResponseDto parseRequest(ParseRequestRequestDto parseRequestRequestDto) throws RequestUtilsException;

}
