package com.czertainly.utils.service.impl;

import com.czertainly.utils.dto.oid.OidInfoResponseDto;
import com.czertainly.utils.service.OidUtilsService;
import com.czertainly.utils.tools.OidFinder;
import org.springframework.stereotype.Service;

@Service
public class OidUtilsServiceImpl implements OidUtilsService {

    @Override
    public OidInfoResponseDto getOidInfo(String identifier) {
        OidFinder oidFinder = new OidFinder();
        return new OidInfoResponseDto(identifier, oidFinder.find(identifier));
    }

}
