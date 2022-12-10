package com.czertainly.utils.tools;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.*;
import java.util.Arrays;
import java.util.List;

public class KeyTools {

    public static KeyPair generateRandomKeyPair() throws NoSuchAlgorithmException {
        Security.addProvider(new BouncyCastleProvider());
        SecureRandom random = new SecureRandom();

        KeyPair keyPair;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

        List<Integer> keySizeList = Arrays.asList(1024, 2048, 4096);
        int randomKeySize = keySizeList.get(random.nextInt(keySizeList.size()));

        keyPairGenerator.initialize(randomKeySize);
        keyPair = keyPairGenerator.generateKeyPair();

        return keyPair;
    }

}
