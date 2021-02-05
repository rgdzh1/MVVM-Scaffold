package com.student.drop.util;

import com.google.gson.Gson;
import com.student.drop.BuildConfig;
import com.student.drop.hefanjiami.utils.DesUtils;
import com.student.drop.hefanjiami.utils.MyStringCallBack;

import org.json.JSONObject;

import java.io.File;
import java.security.MessageDigest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 请求参数处理
 */
public class HttpRequest {
    /**
     * @param mUrl
     * @param mParames
     * @param myStringCallBack
     */
    public static void requestAPI(String mUrl, Map<String, Object> mParames, MyStringCallBack myStringCallBack) {
        HashMap hashMap = new HashMap();
        hashMap.put("reqNo", getRandomString(11));
        hashMap.put("data", new Gson().toJson(mParames));
        okHttpReApi(mUrl, mParames, "text/x-markdown; charset=utf-8").enqueue(myStringCallBack);
    }

    public static Call okHttpReApi(String url, Map<String, Object> map, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("paramsString", toSign(map));
        String str3 = "http://192.168.31.130:8080/springboot_v2_war_exploded/" + url; // 拼接成一个URL
        MultipartBody.Builder type = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (Object str4 : hashMap.keySet()) {
            if (hashMap.get(str4) instanceof Collection) {
                for (Object file : (Collection) hashMap.get(str4)) {
                    type.addFormDataPart((String) str4, ((File) file).getName(), RequestBody.create(MediaType.parse(str2), ((File) file)));
                }
            } else if (hashMap.get(str4) instanceof File) {
                File file2 = (File) hashMap.get(str4);
                type.addFormDataPart((String) str4, file2.getName(), RequestBody.create(MediaType.parse(str2), file2));
            } else {
                type.addFormDataPart((String) str4, hashMap.get(str4).toString());
            }
        }
        return new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build().newCall(new Request.Builder().addHeader("language", "2").url(str3).post(type.build()).build());
    }

    private static String toSign(Map<String, Object> map) {
        String str = null;
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject(map);
            jSONObject.put("key", MD5(jSONObject2.toString()));
            jSONObject.put("body", jSONObject2.toString());
            str = DesUtils.encrypt(jSONObject.toString());
            return Base64Utils.encode(str);
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }


    /**
     * @param i 随机数种子
     * @return
     */
    public static String getRandomString(int i) {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append("abcdefghijklmnopqrstuvwxyz0123456789_".charAt(random.nextInt(37)));
        }
        return stringBuffer.toString();
    }

    public static final String MD5(String str) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] bytes = str.getBytes();
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bytes);
            byte[] digest = instance.digest();
            int length = digest.length;
            char[] cArr2 = new char[(length * 2)];
            int i = 0;
            for (byte b : digest) {
                int i2 = i + 1;
                cArr2[i] = cArr[(b >>> 4) & 15];
                i = i2 + 1;
                cArr2[i2] = cArr[b & cl.m];
            }
            return new String(cArr2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
