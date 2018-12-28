package com.vipvideo.util.web.mahua;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.Random;
import java.util.UUID;

public class AppInfo {
    public static final String SOURCE = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private Context mContext;
    private String terminal;
    private String xAuthToken = "";

    public AppInfo(Context context) {
        this.mContext = context;
        this.terminal = isEmulator() ? "5" : "2";
    }


    public void setXAuthToken(String xAuthToken) {
        this.xAuthToken = xAuthToken;
    }

    public String getSystemName() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Android ");
        stringBuilder.append(VERSION.RELEASE);
        return stringBuilder.toString();
    }

    public String getDevice() {
        return Build.MODEL;
    }

    public String getUuid() {
        return getUUID();
    }

    public String getPackageId() {
        return RHelp.getPackageId();
    }

    public String getVersion() {
        return RHelp.getVersion();
    }

    public String getWXKey() {
        return RHelp.getWxkey();
    }


    public String getTerminal() {
        return this.terminal;
    }

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

    public String getEncryptPackageId() {
        String packageId = getPackageId();
        int length = packageId.length();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(length);
        stringBuilder.append(packageId);
        packageId = stringBuilder.toString();
        length = 15 - length;
        if (length >= SOURCE.length()) {
            return SOURCE;
        }
        String str = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int nextInt = random.nextInt(SOURCE.length());
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str);
            stringBuilder2.append(SOURCE.substring(nextInt, nextInt + 1));
            str = stringBuilder2.toString();
        }
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(packageId);
        stringBuilder3.append(str);
        return stringBuilder3.toString();
    }


    public String getSign(String str, long j, String str2, String str3) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("&");
        stringBuilder.append(j);
        stringBuilder.append("&");
        stringBuilder.append(str2);
        stringBuilder.append("&");
        stringBuilder.append(str3);
        stringBuilder.append("&");
        stringBuilder.append(this.terminal);
        stringBuilder.append("&");
        stringBuilder.append(RHelp.getPackageId());
        stringBuilder.append("&");
        stringBuilder.append(RHelp.getAppKey());
        return Md5Util.getMd5256(stringBuilder.toString());
    }

    public String generateString(Random random, String str, int i) {
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = str.charAt(random.nextInt(str.length()));
        }
        return new String(cArr);
    }

    public HeaderInfo createHeaderInfo(String str) {
        HeaderInfo headerInfo = new HeaderInfo();
        long currentTimeMillis = System.currentTimeMillis();
        String generateString = generateString(new Random(), SOURCE, 10);
        String version = RHelp.getVersion();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(currentTimeMillis);
        stringBuilder.append("");
        headerInfo.setXClientTimeStamp(stringBuilder.toString());
        headerInfo.setXClientNonceStr(generateString);
        headerInfo.setXClientVersion(version);
        headerInfo.setXClientIP(RHelp.getX_Client_IP());
        headerInfo.setXClientSign(getSign(str, currentTimeMillis, generateString, version));
        headerInfo.setXAuthToken(xAuthToken);
        return headerInfo;
    }

    @SuppressLint("WrongConstant")
    public boolean isEmulator() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Build.FINGERPRINT);
        stringBuilder.append(Build.DEVICE);
        stringBuilder.append(Build.MODEL);
        stringBuilder.append(Build.BRAND);
        stringBuilder.append(Build.PRODUCT);
        stringBuilder.append(Build.MANUFACTURER);
        stringBuilder.append(Build.HARDWARE);
        String toLowerCase = stringBuilder.toString().toLowerCase();
        return Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.toLowerCase().contains("vbox") || Build.FINGERPRINT.toLowerCase().contains("test-keys") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.SERIAL.equalsIgnoreCase("unknown") || Build.SERIAL.equalsIgnoreCase("android") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || ((Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT) || ((TelephonyManager) this.mContext.getSystemService("phone")).getNetworkOperatorName().toLowerCase().equals("android") || toLowerCase.contains("keyshlteatt") || toLowerCase.contains("kot49h"));
    }

    public static double getFreeDiskStorage() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
            return (((double) statFs.getAvailableBlocks()) * 1.0d) * ((double) statFs.getBlockSize());
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0d;
        }
    }

    public static long getFreeDiskStrageLong() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
            return (long) (statFs.getAvailableBlocks() * statFs.getBlockSize());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public double getTotalDiskCapacity() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
            return (((double) statFs.getBlockCount()) * 1.0d) * ((double) statFs.getBlockSize());
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0d;
        }
    }

    @SuppressLint({"MissingPermission", "WrongConstant"})
    private String getUUID() {
        String deviceId;
        SharedPreferences sharedPreferences;
        Editor edit;
        String str = null;
        try {
            deviceId = ((TelephonyManager) this.mContext.getSystemService("phone")).getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
            deviceId = null;
        }
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = Secure.getString(this.mContext.getContentResolver(), "android_id");
            if (deviceId != null) {
                deviceId = deviceId.toLowerCase();
                if (deviceId.equals("9774d56d682e549c")) {
                    deviceId = null;
                }
            }
        }
        if (TextUtils.isEmpty(deviceId)) {
            WifiInfo connectionInfo = ((WifiManager) this.mContext.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo != null) {
                deviceId = connectionInfo.getMacAddress();
                if (!deviceId.equals("02:00:00:00:00:00")) {
                    str = deviceId.replace(":", "");
                }
                if (str == null && !str.isEmpty()) {
                    return str;
                }
                sharedPreferences = this.mContext.getSharedPreferences(RHelp.STORE_NAME, 0);
                deviceId = sharedPreferences.getString("mhuuid", "");
                if (deviceId == "") {
                    deviceId = UUID.randomUUID().toString().replace("-", "");
                    edit = sharedPreferences.edit();
                    edit.putString("mhuuid", deviceId);
                    edit.commit();
                }
                return deviceId;
            }
        }
        if (TextUtils.isEmpty(deviceId)) {
            sharedPreferences = this.mContext.getSharedPreferences(RHelp.STORE_NAME, 0);
            deviceId = sharedPreferences.getString("mhuuid", "");
            if (TextUtils.isEmpty(deviceId)) {
                deviceId = UUID.randomUUID().toString().replace("-", "");
                edit = sharedPreferences.edit();
                edit.putString("mhuuid", deviceId);
                edit.commit();
            }
        }
        return deviceId;
    }
}