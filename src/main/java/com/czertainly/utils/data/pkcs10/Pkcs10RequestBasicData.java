package com.czertainly.utils.data.pkcs10;

import com.czertainly.utils.data.RequestData;
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

    private String subject;
    private List<String> sans;

}
