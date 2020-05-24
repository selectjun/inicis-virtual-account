package com.inisis.vacct.utils;

import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class Crypt {

    @Value("${crypt.keyValue}")
    private String keyValue;

    @Value("${crypt.ivValue}")
    private String ivValue;

    public String encAes(String t) {
        String rVal = null;
        try {
            if(t != null && !"".equals(t))
                rVal = CipherAES.encryptCbc(t, keyValue, ivValue);
        } catch (Exception e) {};

        return rVal;
    }

    public String decAes(String t) {
        String rVal = null;
        try {
            if(t != null && !"".equals(t))
                rVal = CipherAES.decryptCbc(t, keyValue, ivValue);
        } catch (Exception e) {};

        return rVal;
    }

    public String getSha256(String strTarget) throws Exception {

        if(strTarget != null && !"".equals(strTarget)) {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(strTarget.getBytes());
            strTarget = bytesToHex(md.digest());
        }
        return strTarget;
    }


    public String getSha512(String strTarget) throws Exception {

        if(strTarget != null && !"".equals(strTarget)) {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(strTarget.getBytes());
            strTarget = bytesToHex(md.digest());
        }
        return strTarget;
    }

    public String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b: bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

}

