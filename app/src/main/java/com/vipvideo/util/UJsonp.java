package com.vipvideo.util;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UJsonp {

    private static UJsonp instance = null;

    private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36";

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 返回单例
     *
     * @return
     */
    public static UJsonp getInstance() {
        if (instance == null)
            instance = new UJsonp();
        return instance;
    }

    /**
     * 将抓取到的html信息转为公司实体
     *
     * @param url
     * @throws Exception
     */
    public void getLine1(String url) throws Exception {
        Document doc = Jsoup.connect(url).userAgent(USER_AGENT).get();
    }

    /**
     *
     * http://jx.arpps.com/  pps解析器
     * http://rrjiexi.com/
     * http://jx.arpps.com/380k/api.php
     * {"play":"m3zy","url":"https:\/\/cdn1.chlpdq.com\/20180906\/hFiWZoDR\/index.m3u8","success":1,"ext":"dp","msg":null}
     *
     * @param finalSite_url
     * @return
     * @throws IOException
     */
    public String getLinexByJArpps(String finalSite_url) throws IOException {
        Connection conn = Jsoup.connect("http://jx.arpps.com/380k/api.php");
        conn.data("id", finalSite_url);
        conn.data("vkey", "就是没有key");
        conn.data("md5", "ab59e87a298ab5d6766a0d13cc6aloij");
        conn.header("Accept", "application/json, text/javascript, */*; q=0.01");
        conn.header("Referer", "http://jx.arpps.com/380k/?url=" + finalSite_url);
        Document doc = conn.post();
        String body = doc.getElementsByTag("body").text();
        if (TextUtils.isEmpty(body)) {
            JSONObject object = JSON.parseObject(body);
            return object.getString("url");
        }
        return "";
    }

    /**
     * @param finalSite_url
     * @return
     * @throws IOException
     */
    public String getLineByJx618(String finalSite_url) throws IOException {
        String realUrl = "https://jx.618g.com/jx.php?url=" + finalSite_url;
        Connection conn = Jsoup.connect(realUrl);
        conn.header("referer", "https://jx.618g.com/?url=" + finalSite_url);
        Document doc = conn.get();
        Element player = doc.getElementById("player");
        String src = player.attr("src");
        String url = src.replace("/m3u8.php?url=", "");
        return url;
    }

    /**
     * http://120.27.155.106/ yun Parse
     * http://jx.api.163ren.com/api.php
     * http://jx.api.163ren.com/vod.php?url=https://www.iqiyi.com/v_19rr7qhp7c.html
     *
     * @param finalSite_url
     * @return {"url":"http%3A%2F%2Fm3u8.play.bugxx.com%2F2018%2F06%2F07%2F6810e949%2Fm3u8.m3u8","ext":"m3u8","type":"qiyis","msg":"ok"}
     * @throws IOException
     */
    public String getLineBy163ren(String finalSite_url) throws IOException {
        String getH5 = "http://jx.api.163ren.com/vod.php?url=" + finalSite_url;
        Connection h5 = Jsoup.connect(getH5).header("Accept", "text/html");
        Document h5Con = h5.get();
        String h5body = h5Con.getElementsByTag("body").toString().replaceAll("\r|\n|\\s*", "").trim();
        Pattern p = Pattern.compile("url\\s*:\\s*'(.*?)'");
        Matcher m = p.matcher(h5body);
        boolean find = m.find();
        if (find) {
            String group = m.group();
            Log.e("dddddddddddddd", find + "---" + group);
            String key = group.replaceAll("url\\s*:\\s*'|'", "");
            String realUrl = "http://jx.api.163ren.com/api.php";
            Connection conn = Jsoup.connect(realUrl);
            conn.data("url", key);
            conn.data("up", "0");
            conn.header("Accept", "application/json, text/javascript, */*; q=0.01");
            conn.header("User-Agent", "Android");
            conn.header("referer", "http://jx.api.163ren.com/vod.php?url=" + finalSite_url);
            conn.header("Accept-Language", "zh-CN");
            Document doc = conn.post();
            String body = doc.getElementsByTag("body").text();
            if (!TextUtils.isEmpty(body)) {
                JSONObject object = JSON.parseObject(body);
                String ext = object.getString("ext");
                String url = object.getString("url");
                if (ext.equals("link")) {
                    String linkUrl = "http://jx.api.163ren.com" + url;
                    Connection link = Jsoup.connect(linkUrl).header("Accept", "text/html");
                    Document linkCon = link.get();
                    String linkBody = linkCon.getElementsByTag("body").toString().replaceAll("\r|\n|\\s*", "").trim();
                    Pattern p1 = Pattern.compile("url\\s*:\\s*'(.*?)'");
                    Matcher m1 = p.matcher(linkBody);
                    boolean linkfind = m.find();
                    if (linkfind) {
                        String linkGroup = m.group();
                        Log.e("dddddddddddddd", find + "---" + linkGroup);
                        String finalUrl = linkGroup.replaceAll("url\\s*:\\s*'|'", "");
                        return finalUrl;
                    }
                }
                return url;
            }
        }
        return "";
    }

    ;

    /**
     * https://www.1717yun.com/1717yun/url.php
     *
     * @param finalSite_url
     * @return
     * @throws IOException
     */
    public String getLineBy1717yun(String finalSite_url) throws IOException {
        Connection conn = Jsoup.connect("https://www.1717yun.com/1717yun/url.php");
        conn.data("id", finalSite_url);
        conn.data("type", "auto");
        conn.data("md5", "ab594276948ab5d633facd90ca26loij");
        conn.header("Accept", "application/json, text/javascript, */*; q=0.01");
        conn.header("Referer", "https://www.1717yun.com/1717yun/?url=" + finalSite_url);
        Document doc = conn.post();
        String url = doc.getElementsByTag("body").text();
        return url;
    }

}
