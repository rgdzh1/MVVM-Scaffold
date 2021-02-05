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

public class DesUtilsCopy {
    private static final String DEFAULT_INCODING = "UTF-8";
    private static final String DES = "DES";
    private static final String PADDING = "DES/ECB/PKCS5Padding";
    private static final String keyData = "duskei_dueu7/32_2333239_4838363ess293w74jsuw";

    public static String encrypt(String str) throws UnsupportedEncodingException {
        return encrypt(str, "UTF-8");
    }

    public static String decrypt(String str) throws UnsupportedEncodingException {
        return decrypt(str, "UTF-8");
    }

    public static String encrypt(String str, String str2) throws UnsupportedEncodingException {
        return new String(Base64Utils.encode(encrypt(str.getBytes(str2))));
    }

    public static String decrypt(String str, String str2) throws UnsupportedEncodingException {
        return new String(descrypt(Base64Utils.decode(str.toCharArray())), str2);
    }

    private static byte[] encrypt(byte[] bArr) {
        DESKeySpec dESKeySpec;
        SecretKeyFactory secretKeyFactory;
        SecretKey secretKey;
        Cipher cipher;
        getKey();
        SecureRandom secureRandom = new SecureRandom();
        try {
            dESKeySpec = new DESKeySpec(keyData.getBytes());
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            dESKeySpec = null;
        }
        try {
            secretKeyFactory = SecretKeyFactory.getInstance(DES);
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            secretKeyFactory = null;
        }
        try {
            secretKey = secretKeyFactory.generateSecret(dESKeySpec);
        } catch (InvalidKeySpecException e3) {
            e3.printStackTrace();
            secretKey = null;
        }
        try {
            cipher = Cipher.getInstance(PADDING);
        } catch (NoSuchAlgorithmException e4) {
            e4.printStackTrace();
        } catch (NoSuchPaddingException e5) {
            e5.printStackTrace();
        }
        try {
            cipher.init(1, secretKey, secureRandom);
        } catch (InvalidKeyException e6) {
            e6.printStackTrace();
        }
        try {
            return cipher.doFinal(bArr);
        } catch (IllegalStateException e7) {
            e7.printStackTrace();
            return null;
        } catch (IllegalBlockSizeException e8) {
            e8.printStackTrace();
            return null;
        } catch (BadPaddingException e9) {
            e9.printStackTrace();
            return null;
        }
        cipher = null;
        cipher.init(1, secretKey, secureRandom);
        return cipher.doFinal(bArr);
    }

    private static byte[] descrypt(byte[] bArr) {
        DESKeySpec dESKeySpec;
        SecretKeyFactory secretKeyFactory;
        SecretKey secretKey;
        Cipher cipher;
        SecureRandom secureRandom = new SecureRandom();
        getKey();
        try {
            dESKeySpec = new DESKeySpec(keyData.getBytes());
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            dESKeySpec = null;
        }
        try {
            secretKeyFactory = SecretKeyFactory.getInstance(DES);
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            secretKeyFactory = null;
        }
        try {
            secretKey = secretKeyFactory.generateSecret(dESKeySpec);
        } catch (InvalidKeySpecException e3) {
            e3.printStackTrace();
            secretKey = null;
        }
        try {
            cipher = Cipher.getInstance(PADDING);
        } catch (NoSuchAlgorithmException e4) {
            e4.printStackTrace();
        } catch (NoSuchPaddingException e5) {
            e5.printStackTrace();
        }
        try {
            cipher.init(2, secretKey, secureRandom);
        } catch (InvalidKeyException e6) {
            e6.printStackTrace();
        }
        try {
            return cipher.doFinal(bArr);
        } catch (IllegalStateException e7) {
            e7.printStackTrace();
            return null;
        } catch (IllegalBlockSizeException e8) {
            e8.printStackTrace();
            return null;
        } catch (BadPaddingException e9) {
            e9.printStackTrace();
            return null;
        }
        cipher = null;
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
