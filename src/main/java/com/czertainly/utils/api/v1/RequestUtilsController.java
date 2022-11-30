package com.czertainly.utils.api.v1;

import com.czertainly.utils.dto.ParseRequestRequestDto;
import com.czertainly.utils.dto.ParseRequestResponseDto;
import com.czertainly.utils.enums.RequestType;
import com.czertainly.utils.exception.RequestUtilsException;
import com.czertainly.utils.service.RequestUtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/request")
public class RequestUtilsController {

    @Autowired
    private RequestUtilsService requestUtilsService;

    @RequestMapping(
            path = "/{requestType}/parse",
            method = RequestMethod.POST,
            consumes = {"application/json"},
            produces = {"application/json"}
    )
    public ParseRequestResponseDto parseRequest(
            @PathVariable RequestType requestType,
            @Valid @RequestBody ParseRequestRequestDto request) throws RequestUtilsException {
        return  requestUtilsService.parseRequest(requestType, request);
    }

}
