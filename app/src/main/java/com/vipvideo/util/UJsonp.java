package com.vipvideo.util;

import android.content.res.AssetManager;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vipvideo.app.UApplication;
import com.vipvideo.bean.BannerBean;
import com.vipvideo.model.HomeModel;
import com.vipvideo.util.web.jscrawler.JsCrawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UJsonp {

    private static final String TAG = "UJsonp";
    private static UJsonp instance = null;

    private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36";

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
    public void getLine1(String url) {
        JsCrawler jsCrawler = JsCrawler.getInstance();
        final String js = loadJs();
//        jsCrawler.callFunction(js, new JsCallback() {
//
//            @Override
//            public void onResult(String result) {
//                Log.d("UJsonp", "onResult: " + result);
//            }
//
//            @Override
//            public void onError(String errorMessage) {
//                Log.d(TAG, "onError: " + errorMessage);
//            }
//        }, "getBlogList");
    }

    public String loadJs() {
        try {
            final AssetManager am = UApplication.getAppContext().getAssets();
            final InputStream inputStream = am.open("crawler.js");
            Scanner scanner = new Scanner(inputStream, "UTF-8");
            return scanner.useDelimiter("\\A").next();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
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


    public HomeModel jiexi(String result) {
        List<BannerBean> bannerBeans = new ArrayList<>();
        List<HomeModel.HotBean> hotBeans = new ArrayList<>();
        List<HomeModel.HotTvBean> hotTipBeans = new ArrayList<>();
        List<HomeModel.HotTvBean> hotTvBeans = new ArrayList<>();
        List<HomeModel.HotTvBean> hotMovieBeans = new ArrayList<>();
        List<HomeModel.HotTvBean> hotSearchBeans = new ArrayList<>();
        List<HomeModel.HotTvBean> hotVarietys = new ArrayList<>();
        List<HomeModel.HotTvBean> hothotComicBeans = new ArrayList<>();
        HomeModel model = new HomeModel();
        try {
            Document document = Jsoup.parse(result);
            //轮播图
            Elements scrollImg = document.getElementsByClass("yl-video-scrollimg-wrap");
            for (Element labelA : scrollImg) {
                Element aEle = labelA.getElementsByTag("a").first();
                BannerBean bannerBean = new BannerBean();
                bannerBean.setLink(aEle.attr("href"));
                bannerBean.setPic_url(aEle.select("img").attr("src"));
                bannerBean.setTvLabel(aEle.select(".yl-video-scrollimg-mark div:nth-child(1)").text());
                bannerBean.setTvDes(aEle.select(".yl-video-scrollimg-mark div:nth-child(2)").text());
                bannerBeans.add(bannerBean);
            }
            model.setBannerBeans(bannerBeans);
            Elements elements = document.select(".yl-video-gap-top41");
            //热门话题
            Elements hotTips = elements.get(0).select(".yl-video-hottip-box");
            for (Element hotTip : hotTips) {
                HomeModel.HotTvBean hotTvBean = new HomeModel.HotTvBean();
                hotTvBean.setLink(hotTip.attr("href"));
                String label = hotTip.select(".c-font-medium").text();
                hotTvBean.setHotTv_label(label);
                String hot_image_src = hotTip.select(".yl-video-hottip-img img").attr("src");
                hotTvBean.setHotTv_img(hot_image_src);
                hotTvBean.setHotTv_name(hotTip.getElementsByTag("span").text());
                hotTipBeans.add(hotTvBean);
            }
            hotBeans.add(new HomeModel.HotBean("热门话题", 0, hotTipBeans));
            //热门电视剧
            Elements hotTvs = elements.get(1).select(".c-scroll-item a");
            int i = 0;
            for (Element hotTv : hotTvs) {
                HomeModel.HotTvBean tvBean = new HomeModel.HotTvBean();
                tvBean.setLink(hotTv.attr("href"));
                tvBean.setHotTv_label(hotTv.select("span:first-child").text());
                tvBean.setHotTv_name(hotTv.select(".c-line-clamp1").text());
                tvBean.setHotTv_star(hotTv.select("span").last().text());
                String hot_image_src = hotTv.select("img").attr("src");
                tvBean.setHotTv_img(hot_image_src);
                hotTvBeans.add(tvBean);
            }
            hotBeans.add(new HomeModel.HotBean("热门电视剧", 1, hotTvBeans));


            //热门电影
            Elements hotMovie = elements.get(2).select(".c-scroll-item a");
            for (Element hotTv : hotMovie) {
                HomeModel.HotTvBean tvBean = new HomeModel.HotTvBean();
                tvBean.setLink(hotTv.attr("href"));
                tvBean.setHotTv_label(hotTv.select("span:first-child").text());
                tvBean.setHotTv_name(hotTv.select(".c-line-clamp1").text());
                tvBean.setHotTv_star(hotTv.select("span").last().text());
                String hot_image_src = hotTv.select("img").attr("src");
                tvBean.setHotTv_img(hot_image_src);
                hotMovieBeans.add(tvBean);
            }
            hotBeans.add(new HomeModel.HotBean("热门电影", 2, hotMovieBeans));
            //大家都在搜
            Elements loveSearchs = elements.get(3).select("a");
            for (Element love : loveSearchs) {
                HomeModel.HotTvBean tipBean = new HomeModel.HotTvBean();
                tipBean.setLink(love.attr("href"));
                tipBean.setHotTv_label(love.text());
                hotSearchBeans.add(tipBean);
            }
            hotBeans.add(new HomeModel.HotBean("大家都在搜", 3, hotSearchBeans));
            //热门综艺
            Elements hotVariety = elements.get(4).select(".c-scroll-item a");
            for (Element hotTv : hotVariety) {
                HomeModel.HotTvBean tvBean = new HomeModel.HotTvBean();
                tvBean.setLink(hotTv.attr("href"));
                tvBean.setHotTv_label(hotTv.select("span:first-child").text());
                tvBean.setHotTv_name(hotTv.select(".c-line-clamp1").text());
                tvBean.setHotTv_star(hotTv.select("span").last().text());
                String hot_image_src = hotTv.select("img").attr("src");
                tvBean.setHotTv_img(hot_image_src);
                hotVarietys.add(tvBean);
            }
            hotBeans.add(new HomeModel.HotBean("热门综艺", 4, hotVarietys));

            //热门动漫
            Elements hotComic = elements.get(5).select(".c-scroll-item a");
            for (Element hotTv : hotComic) {
                HomeModel.HotTvBean tvBean = new HomeModel.HotTvBean();
                tvBean.setLink(hotTv.attr("href"));
                tvBean.setHotTv_label(hotTv.select("span:first-child").text());
                tvBean.setHotTv_name(hotTv.select(".c-line-clamp1").text());
                tvBean.setHotTv_star(hotTv.select("span").last().text());
                String hot_image_src = hotTv.select("img").attr("src");
                tvBean.setHotTv_img(hot_image_src);
                hothotComicBeans.add(tvBean);
            }
            hotBeans.add(new HomeModel.HotBean("热门动漫", 5, hothotComicBeans));
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.setHotBeans(hotBeans);
        return model;
    }

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
