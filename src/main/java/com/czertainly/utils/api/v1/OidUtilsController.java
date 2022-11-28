package com.czertainly.utils.api.v1;

import com.czertainly.utils.dto.oid.OidInfoResponseDto;
import com.czertainly.utils.service.OidUtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/utils/oid")
public class OidUtilsController {

    @Autowired
    private OidUtilsService oidUtilsService;

    @RequestMapping(
            path = "/{identifier}",
            method = RequestMethod.GET,
            produces = {"application/json"}
    )
    public OidInfoResponseDto getOidInfo(@PathVariable String identifier) {
        return  oidUtilsService.getOidInfo(identifier);
    }
}
