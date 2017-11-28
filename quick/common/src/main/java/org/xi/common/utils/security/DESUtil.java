package org.xi.common.utils.security;

import org.xi.common.utils.text.HexUtil;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * Created by Xi on 5/17/2017.
 */
public class DESUtil {

    /**
     * 加密方法
     *
     * @param input    加解密的字符串
     * @param password 加密的密码（只能为8位长）
     * @param encoding 字符集
     * @return
     */
    public static String encrypt(String input, String password, String encoding) {
        try {
            SecureRandom secureRandom = new SecureRandom();
            DESKeySpec desKeySpec = new DESKeySpec(password.getBytes());
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, secureRandom);
            byte[] output = cipher.doFinal(input.getBytes(encoding));
            return new String(HexUtil.toHex(output), "UTF8");
        } catch (Exception err) {
            throw new RuntimeException(err.getMessage());
        }
    }

    /**
     * 加密方法
     *
     * @param input    待加密的字符串
     * @param password 加密的密码（只能为8位长）
     * @return
     */
    public static String encrypt(String input, String password) {
        return encrypt(input, password, "utf8");
    }

    /**
     * 解密方法
     *
     * @param input    待解密的字符串
     * @param password 解密的密码（只能为8位长）
     * @param encoding 字符集
     * @return
     */
    public static String decrypt(String input, String password, String encoding) {
        try {
            SecureRandom secureRandom = new SecureRandom();
            DESKeySpec desKeySpec = new DESKeySpec(password.getBytes());
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, secureRandom);
            byte[] output = cipher.doFinal(HexUtil.fromHex(input.getBytes("UTF8")));
            return new String(output, encoding);
        } catch (Exception err) {
            throw new RuntimeException(err.getMessage());
        }
    }

    /**
     * 解密方法
     *
     * @param input
     * @param password
     * @return
     */
    public static String decrypt(String input, String password) {
        return decrypt(input, password, "utf8");
    }
}
