package com.vipvideo.util;

import java.util.regex.Pattern;

public class AdFilterTool {
    public static String reg = ".*(.1717yun.com|.vivcms.com|.iqiyi.com|.js).*";
    public static String ignoreReg = ".*(.(jpg|JPG|jpeg|JPEG|gif|GIF|bmp|bnp|png|css)).*";

    public static boolean isAd(String baseUrl, String url) {
        String finalReg = reg;
        switch (baseUrl) {
            case "www.1717yun.com":
                break;
        }
        if (Pattern.matches(ignoreReg, url)) {
            return true;
        }
        return !Pattern.matches(finalReg, url);
    }
}
