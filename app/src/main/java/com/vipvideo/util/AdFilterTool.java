package com.vipvideo.util;

import java.util.regex.Pattern;

public class AdFilterTool {
    public static String reg = ".*(.hm.baidu.com|.(jpg|JPG|jpeg|JPEG|gif|GIF|bmp|bnp|png|css)).*";

    public static boolean isAd(String baseUrl, String url) {
        String finalReg = reg;
        switch (baseUrl) {
            case "www.1717yun.com":
                break;
        }

        return Pattern.matches (finalReg, url);
    }
}
