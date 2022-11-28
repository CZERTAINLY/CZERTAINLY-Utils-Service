package com.czertainly.utils.tools;

import java.util.HashMap;
import java.util.Map;

public class OidFinder {

    private static final Map<String, String> oids = new HashMap<String, String>();

    static {
        // subject
        oids.put("2.5.4.3", "Common Name");
    }

    public String find(String identifier) {
        return oids.get(identifier);
    }
}
