package org.xi.quick.utils.security;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by Xi on 5/17/2017.
 */
public class MD5Util {

    /**
     * 加密方法
     * @param str
     * @return
     */
    public static String encrypt(String str) {

        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            byte[] digest = m.digest(str.getBytes("utf8"));
            String hash = new BigInteger(1, digest).toString(16);
            return hash.toLowerCase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
