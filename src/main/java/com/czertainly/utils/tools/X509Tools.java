package com.czertainly.utils.tools;

import com.czertainly.utils.tools.DateTools;
import com.czertainly.utils.tools.KeyTools;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class X509Tools {

    public static byte[] generateRandomX509Certificate() throws NoSuchAlgorithmException, OperatorCreationException, CertificateException, SignatureException, InvalidKeyException, NoSuchProviderException {
        Security.addProvider(new BouncyCastleProvider());
        SecureRandom random = new SecureRandom();

        X500Name owner = new X500Name("CN=generatedCertificate,O=random");

        // current time minus 1 year, just in case software clock goes back due to time synchronization
        Date notBefore = new Date(System.currentTimeMillis() - 86400000L * 365);
        // random date between the generated time and 1 year from now
        Date notAfter = DateTools.between(new Date(System.currentTimeMillis() - 86400000L * 365),
                new Date(System.currentTimeMillis() + 86400000L * 365));

        KeyPair keyPair = KeyTools.generateRandomKeyPair();

        X509v3CertificateBuilder builder = new JcaX509v3CertificateBuilder(
                owner, new BigInteger( 64, random ), notBefore, notAfter, owner, keyPair.getPublic() );

        PrivateKey privateKey = keyPair.getPrivate();
        ContentSigner signer = new JcaContentSignerBuilder("SHA512WithRSAEncryption" ).build(privateKey);

        X509CertificateHolder certHolder = builder.build( signer );
        X509Certificate cert = new JcaX509CertificateConverter()
                .setProvider(new BouncyCastleProvider())
                .getCertificate(certHolder);

        //check so that cert is valid
        cert.verify(keyPair.getPublic());

        return cert.getEncoded();
    }

    public static List<String> extractSanFromCertificate(X509Certificate x509Certificate) throws CertificateParsingException {
        List<String> sans = new ArrayList<>();
        Collection<List<?>> sanList = x509Certificate.getSubjectAlternativeNames();
        for (List<?> san : sanList) {
            String title = "";
            Integer type = (Integer) san.get(0);
            if (type == GeneralName.dNSName) {
                title = "DNS";
            } else if (type == GeneralName.iPAddress) {
                title = "IP Address";
                // name.toASN1Primitive();
            } else if (type == GeneralName.otherName) {
                title = "Other Name";
            }
            sans.add(title + ": " + san.get(1));
        }
        return sans;
    }
}
