package com.vipvideo.util.web.mahua;

public class PackInfo {
    String aesKey;
    String appKey;
    String domain;
    String flurryKey;
    String miId;
    String miKey;
    String openKey;
    String packageId;
    String packageName;
    String qqKey;
    String trackKey;
    String umAppkey;
    String umMessagekey;
    String version;
    String wbKey;
    String wxKey;

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public String getPackageId() {
        return this.packageId;
    }

    public void setPackageId(String str) {
        this.packageId = str;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getUmAppkey() {
        return this.umAppkey;
    }

    public void setUmAppkey(String str) {
        this.umAppkey = str;
    }

    public String getUmMessagekey() {
        return this.umMessagekey;
    }

    public void setUmMessagekey(String str) {
        this.umMessagekey = str;
    }

    public String getWxKey() {
        return this.wxKey;
    }

    public void setWxKey(String str) {
        this.wxKey = str;
    }

    public String getAesKey() {
        return this.aesKey;
    }

    public void setAesKey(String str) {
        this.aesKey = str;
    }

    public String getFlurryKey() {
        return this.flurryKey;
    }

    public void setFlurryKey(String str) {
        this.flurryKey = str;
    }

    public String getMiId() {
        return this.miId;
    }

    public void setMiId(String str) {
        this.miId = str;
    }

    public String getMiKey() {
        return this.miKey;
    }

    public void setMiKey(String str) {
        this.miKey = str;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String str) {
        this.domain = str;
    }

    public String getAppKey() {
        return this.appKey;
    }

    public void setAppKey(String str) {
        this.appKey = str;
    }

    public String getWbKey() {
        return this.wbKey;
    }

    public void setWbKey(String str) {
        this.wbKey = str;
    }

    public String getQqKey() {
        return this.qqKey;
    }

    public void setQqKey(String str) {
        this.qqKey = str;
    }

    public String getTrackKey() {
        return this.trackKey;
    }

    public void setTrackKey(String str) {
        this.trackKey = str;
    }

    public String getOpenKey() {
        return this.openKey;
    }

    public void setOpenKey(String str) {
        this.openKey = str;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PackInfo{packageName='");
        stringBuilder.append(this.packageName);
        stringBuilder.append('\'');
        stringBuilder.append(", packageId='");
        stringBuilder.append(this.packageId);
        stringBuilder.append('\'');
        stringBuilder.append(", version='");
        stringBuilder.append(this.version);
        stringBuilder.append('\'');
        stringBuilder.append(", umAppkey='");
        stringBuilder.append(this.umAppkey);
        stringBuilder.append('\'');
        stringBuilder.append(", umMessagekey='");
        stringBuilder.append(this.umMessagekey);
        stringBuilder.append('\'');
        stringBuilder.append(", wxKey='");
        stringBuilder.append(this.wxKey);
        stringBuilder.append('\'');
        stringBuilder.append(", aesKey='");
        stringBuilder.append(this.aesKey);
        stringBuilder.append('\'');
        stringBuilder.append(", flurryKey='");
        stringBuilder.append(this.flurryKey);
        stringBuilder.append('\'');
        stringBuilder.append(", miId='");
        stringBuilder.append(this.miId);
        stringBuilder.append('\'');
        stringBuilder.append(", miKey='");
        stringBuilder.append(this.miKey);
        stringBuilder.append('\'');
        stringBuilder.append(", domain='");
        stringBuilder.append(this.domain);
        stringBuilder.append('\'');
        stringBuilder.append(", appKey='");
        stringBuilder.append(this.appKey);
        stringBuilder.append('\'');
        stringBuilder.append(", wbKey='");
        stringBuilder.append(this.wbKey);
        stringBuilder.append('\'');
        stringBuilder.append(", qqKey='");
        stringBuilder.append(this.qqKey);
        stringBuilder.append('\'');
        stringBuilder.append(", trackKey='");
        stringBuilder.append(this.trackKey);
        stringBuilder.append('\'');
        stringBuilder.append(", openKey='");
        stringBuilder.append(this.openKey);
        stringBuilder.append('\'');
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}