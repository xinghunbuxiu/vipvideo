package com.vipvideo.util.web.mahua;

import android.os.Environment;

import com.vipvideo.R;

import java.io.File;
import java.math.BigDecimal;

public class RHelp {
    public static final int MH_PERMISSIONS_REQUEST = 1;
    public static final int MH_WRITE_SETTINGS = 2;
    public static String SHARE_URL = "/app/share/";
    public static String STORE_NAME = "mhysName";
    public static String aesKey = "DtXZcHh9XJ5SfPAe";
    public static String appKey;
    public static String domain;
    public static String flurryKey;
    public static String miId;
    public static String miKey;
    public static String openKey;
    public static String packageId;
    public static String packageName;
    public static String qqKey;
    public static String trackKey;
    public static String umAppkey;
    public static String umMessagekey;
    public static String version;
    public static String wbKey;
    public static String wxkey;

    public static String getX_Client_IP() {
        return "127.0.0.1";
    }

    public static void setPackageName(String str) {
        packageName = str;
    }

    public static void setPackageId(String str) {
        packageId = str;
    }

    public static void setVersion(String str) {
        version = str;
    }

    public static void setUmAppkey(String str) {
        umAppkey = str;
    }

    public static void setUmMessagekey(String str) {
        umMessagekey = str;
    }

    public static void setWxkey(String str) {
        wxkey = str;
    }

    public static void setAesKey(String str) {
        aesKey = str;
    }

    public static void setFlurryKey(String str) {
        flurryKey = str;
    }

    public static void setMiId(String str) {
        miId = str;
    }

    public static void setMiKey(String str) {
        miKey = str;
    }

    public static void setDomain(String str) {
        domain = str;
    }

    public static void setAppKey(String str) {
        appKey = str;
    }

    public static void setWbKey(String str) {
        wbKey = str;
    }

    public static void setQqKey(String str) {
        qqKey = str;
    }

    public static void setTrackKey(String str) {
        trackKey = str;
    }

    public static void setOpenKey(String str) {
        openKey = str;
    }

    public static String getPackageName() {
        return packageName;
    }

    public static String getPackageId() {
        return packageId;
    }

    public static String getVersion() {
        return version;
    }

    public static String getUmAppkey() {
        return umAppkey;
    }

    public static String getUmMessagekey() {
        return umMessagekey;
    }

    public static String getWxkey() {
        return wxkey;
    }

    public static String getKey() {
        return aesKey;
    }

    public static String getFlurryKey() {
        return flurryKey;
    }

    public static String getMiId() {
        return miId;
    }

    public static String getMikey() {
        return miKey;
    }

    public static String getDomain() {
        return domain;
    }

    public static String getAppKey() {
        return appKey;
    }

    public static String getWbKey() {
        return wbKey;
    }

    public static String getQqKey() {
        return qqKey;
    }

    public static String getTrackKey() {
        return trackKey;
    }

    public static String getOpenKey() {
        return openKey;
    }

    public static void initInfo(PackInfo packInfo) {
        setVersion(packInfo.getVersion());
        setAesKey(packInfo.getAesKey());
        setAppKey(packInfo.getAppKey());
        setDomain(packInfo.getDomain());
        setFlurryKey(packInfo.getFlurryKey());
        setMiId(packInfo.getMiId());
        setMiKey(packInfo.getMiKey());
        setPackageId(packInfo.getPackageId());
        setPackageName(packInfo.getPackageName());
        setUmAppkey(packInfo.getUmAppkey());
        setUmMessagekey(packInfo.getUmMessagekey());
        setWxkey(packInfo.getWxKey());
        setWbKey(packInfo.getWbKey());
        setQqKey(packInfo.getQqKey());
        setTrackKey(packInfo.getTrackKey());
        setOpenKey(packInfo.getOpenKey());
    }


    public static int getAppName() {
        return R.string.app_name;
    }

    public static String getMahuaFolder() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        stringBuilder.append(File.separator);
        stringBuilder.append(getPackageName());
        return stringBuilder.toString();
    }

    public static String getImageCacheFolder(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getMahuaFolder());
        stringBuilder.append(File.separator);
        stringBuilder.append("image");
        String stringBuilder2 = stringBuilder.toString();
        if (str == null || str.isEmpty()) {
            return stringBuilder2;
        }
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(stringBuilder2);
        stringBuilder3.append(File.separator);
        stringBuilder3.append(str);
        return stringBuilder3.toString();
    }

    public static String getApkCacheFolder(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getMahuaFolder());
        stringBuilder.append(File.separator);
        stringBuilder.append("apk");
        String stringBuilder2 = stringBuilder.toString();
        if (str == null || str.isEmpty()) {
            return stringBuilder2;
        }
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(stringBuilder2);
        stringBuilder3.append(File.separator);
        stringBuilder3.append(str);
        return stringBuilder3.toString();
    }

    public static String getVideoCacheFolder() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getMahuaFolder());
        stringBuilder.append(File.separator);
        stringBuilder.append("vedio");
        return stringBuilder.toString();
    }

    public static String getMP4CacheFolder() {
        return getMahuaFolder();
    }

    public static String getFormatSize(double d) {
        double d2 = d / 1024.0d;
        if (d2 < 1.0d) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(d);
            stringBuilder.append("B");
            return stringBuilder.toString();
        }
        d = d2 / 1024.0d;
        BigDecimal bigDecimal;
        StringBuilder stringBuilder2;
        if (d < 1.0d) {
            bigDecimal = new BigDecimal(Double.toString(d2));
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(bigDecimal.setScale(2, 4).toPlainString());
            stringBuilder2.append("KB");
            return stringBuilder2.toString();
        }
        d2 = d / 1024.0d;
        BigDecimal bigDecimal2;
        StringBuilder stringBuilder3;
        if (d2 < 1.0d) {
            bigDecimal2 = new BigDecimal(Double.toString(d));
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append(bigDecimal2.setScale(2, 4).toPlainString());
            stringBuilder3.append("MB");
            return stringBuilder3.toString();
        }
        d = d2 / 1024.0d;
        if (d < 1.0d) {
            bigDecimal = new BigDecimal(Double.toString(d2));
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(bigDecimal.setScale(2, 4).toPlainString());
            stringBuilder2.append("GB");
            return stringBuilder2.toString();
        }
        bigDecimal2 = new BigDecimal(d);
        stringBuilder3 = new StringBuilder();
        stringBuilder3.append(bigDecimal2.setScale(2, 4).toPlainString());
        stringBuilder3.append("TB");
        return stringBuilder3.toString();
    }


}