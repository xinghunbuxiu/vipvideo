package com.vipvideo.util.web.mahua;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesUtil {
    private static String hexStr = "0123456789abcdef";

    public static String getKey(boolean z) {
        String key = RHelp.getKey();
        if (z) {
            return key;
        }
        String packageId = RHelp.getPackageId();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(packageId);
        stringBuilder.append(key);
        stringBuilder.append(packageId);
        packageId = Md5Util.getMd5(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append(packageId.substring(0, 8));
        stringBuilder.append(key.substring(0, 8));
        return stringBuilder.toString();
    }

    public static String encryptToHex(String str, String str2) {
        try {
            return bytesToHexString(aesEncryptToBytes(str, str2));
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public static String decryptHex(String str, String str2) {
        try {
            return aesDecryptByBytes(hexStringToBytes(str), str2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String bytesToHexString(byte[] bArr) {
        String str = "";
        for (int i = 0; i < bArr.length; i++) {
            String valueOf = String.valueOf(hexStr.charAt((bArr[i] & 240) >> 4));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(valueOf);
            stringBuilder.append(String.valueOf(hexStr.charAt(bArr[i] & 15)));
            valueOf = stringBuilder.toString();
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(valueOf);
            str = stringBuilder.toString();
        }
        return str;
    }

    public static byte[] hexStringToBytes(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (((byte) (hexStr.indexOf(str.charAt(i2)) << 4)) | ((byte) hexStr.indexOf(str.charAt(i2 + 1))));
        }
        return bArr;
    }

    private static byte[] aesEncryptToBytes(String str, String str2) throws Exception {
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
        byte[] bytes = str2.getBytes();
        instance.init(1, new SecretKeySpec(bytes, "AES"), new IvParameterSpec(bytes));
        return instance.doFinal(str.getBytes());
    }

    private static String aesDecryptByBytes(byte[] bArr, String str) throws Exception {
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
        byte[] bytes = str.getBytes();
        instance.init(2, new SecretKeySpec(bytes, "AES"), new IvParameterSpec(bytes));
        return new String(instance.doFinal(bArr));
    }

    public static List<String> getSubUtil(String str, String str2) {
        List<String> arrayList = new ArrayList();
        Matcher matcher = Pattern.compile(str2).matcher(str);
        while (matcher.find()) {
            arrayList.add(matcher.group(1));
        }
        return arrayList;
    }

    public static String getSubUtilSimple(String str, String str2) {
        Matcher matcher = Pattern.compile(str2).matcher(str);
        return matcher.find() ? matcher.group(1) : "";
    }

}