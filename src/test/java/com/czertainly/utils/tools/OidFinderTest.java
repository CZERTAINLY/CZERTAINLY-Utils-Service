package com.czertainly.utils.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OidFinderTest {

    @Test
    public void getOidInfo_success() {
        String identifier = "2.5.4.3";

        OidFinder oidFinder = new OidFinder();
        String oidName = oidFinder.find(identifier);

        Assertions.assertEquals("Common Name", oidName);
    }

    @Test
    public void getOidInfo_null() {
        String identifier = "2.5.29.35";

        OidFinder oidFinder = new OidFinder();
        String oidName = oidFinder.find(identifier);

        Assertions.assertNull(oidName);
    }
}