package com.czertainly.utils.service;

import com.czertainly.utils.dto.oid.OidInfoResponseDto;

public interface OidUtilsService {

    OidInfoResponseDto getOidInfo(String identifier);
}
