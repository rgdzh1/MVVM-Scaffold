package com.student.drop.hefanjiami.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DesUtils {
    private static final String DEFAULT_INCODING = "UTF-8";
    private static final String DES = "DES";
    private static final String PADDING = "DES/ECB/PKCS5Padding";
    private static final String keyData = "duskei_dueu7/32_2333239_4838363ess293w74jsuw";

    public static String encrypt(String str) throws Exception {
        return encrypt(str, "UTF-8");
    }

    public static String decrypt(String str) throws Exception {
        return decrypt(str, "UTF-8");
    }

    public static String encrypt(String str, String str2) throws Exception {
        return new String(Base64Utils.encode(encrypt(str.getBytes(str2))));
    }

    public static String decrypt(String str, String str2) throws Exception {
        return new String(descrypt(Base64Utils.decode(str.toCharArray())), str2);
    }

    private static byte[] encrypt(byte[] bArr) throws Exception {
        DESKeySpec dESKeySpec = new DESKeySpec(keyData.getBytes());
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey secretKey = secretKeyFactory.generateSecret(dESKeySpec);
        Cipher cipher = Cipher.getInstance(PADDING);
        getKey();
        SecureRandom secureRandom = new SecureRandom();
        cipher.init(1, secretKey, secureRandom);
        return cipher.doFinal(bArr);
    }

    private static byte[] descrypt(byte[] bArr) throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        getKey();
        DESKeySpec dESKeySpec = new DESKeySpec(keyData.getBytes());
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey secretKey = secretKeyFactory.generateSecret(dESKeySpec);
        Cipher cipher = Cipher.getInstance(PADDING);
        cipher.init(2, secretKey, secureRandom);
        return cipher.doFinal(bArr);
    }

    private static byte[] getKey() {
        KeyGenerator keyGenerator;
        SecureRandom secureRandom = new SecureRandom();
        try {
            keyGenerator = KeyGenerator.getInstance(DES);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            keyGenerator = null;
        }
        keyGenerator.init(secureRandom);
        return keyGenerator.generateKey().getEncoded();
    }
}
