package com.vipvideo.bean;

import com.lixh.bean.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LIXH on 2018/10/19.
 * email lixhVip9@163.com
 * des
 */

public class GroupVideoInfo extends BaseModel {


    private List<SlicesBean> slices;

    public List<SlicesBean> getSlices() {
        return slices;
    }

    public void setSlices(List<SlicesBean> slices) {
        this.slices = slices;
    }

    public static class SlicesBean implements Serializable {
        /**
         * name : 小编推荐
         * type : kehuan
         * block_type : channel_video
         * tag : movie
         * tag_ext :
         * url_cond : baiduVideoiPhone://adnativemovie?start=2018
         * weight : 0
         * nsclick_v : ?pid={pid}&channel_name=adnativemovie&channel_type=kehuan&block_name=%E5%B0%8F%E7%BC%96%E6%8E%A8%E8%8D%90&tpl=selected&pos=_slices_0
         * block_sign : 23
         * videos : [{"id":"456106","works_id":"36682","other_works_id":"","works_type":"movie","title":"白发魔女传","url":"","rating":"4.7","large_imgh_url":"","imgh_url":"http://b.hiphotos.baidu.com/video/pic/item/14ce36d3d539b600096c273fe250352ac75cb793.jpg","imgv_url":"http://d.hiphotos.baidu.com/video/pic/item/faedab64034f78f03a2ab17b72310a55b3191c10.jpg","ring_short_video_stream":"","img_gif_url":"","label":"","brief":"范爷教主为爱入魔","tag":"","metic_img":"","metic_url":"","version":"","corner_mark":"","is_long_video":"0","is_vip":"0","duration":"","is_deep":"0","come_from":"","tag_new":"","tag_ext":"","play_count":"0","update":"","year_type":"2014 爱情 动作 古装 奇幻 武侠","year":"2014","is_sites":1,"nsclick_v":"?pid={pid}&id=456106&channel_name=adnativemovie&block_type=kehuan&block_name=%E5%B0%8F%E7%BC%96%E6%8E%A8%E8%8D%90&block_sign=23&mod=0&works_type=movie&video_type=long_video&video_id=36682&video_name=%E7%99%BD%E5%8F%91%E9%AD%94%E5%A5%B3%E4%BC%A0&video_url=&version=&item_id=&tag=movie&tpl=movie_selected&pos=_slices_0_videos_0"}]
         */

        private String name;
        private String type;
        private String block_type;
        private String tag;
        private String tag_ext;
        private String url_cond;
        private String weight;
        private String nsclick_v;
        private String block_sign;
        private List<VideosBean> videos;
        private List<HotBean> hot;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getBlock_type() {
            return block_type;
        }

        public void setBlock_type(String block_type) {
            this.block_type = block_type;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getTag_ext() {
            return tag_ext;
        }

        public void setTag_ext(String tag_ext) {
            this.tag_ext = tag_ext;
        }

        public String getUrl_cond() {
            return url_cond;
        }

        public void setUrl_cond(String url_cond) {
            this.url_cond = url_cond;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getNsclick_v() {
            return nsclick_v;
        }

        public void setNsclick_v(String nsclick_v) {
            this.nsclick_v = nsclick_v;
        }

        public String getBlock_sign() {
            return block_sign;
        }

        public void setBlock_sign(String block_sign) {
            this.block_sign = block_sign;
        }

        public List<VideosBean> getVideos() {
            return videos;
        }

        public void setVideos(List<VideosBean> videos) {
            this.videos = videos;
        }

        public List<HotBean> getHot() {
            return hot;
        }

        public void setHot(List<HotBean> hot) {
            this.hot = hot;
        }

        public static class VideosBean implements Serializable {
            /**
             * id : 456106
             * works_id : 36682
             * other_works_id :
             * works_type : movie
             * title : 白发魔女传
             * url :
             * rating : 4.7
             * large_imgh_url :
             * imgh_url : http://b.hiphotos.baidu.com/video/pic/item/14ce36d3d539b600096c273fe250352ac75cb793.jpg
             * imgv_url : http://d.hiphotos.baidu.com/video/pic/item/faedab64034f78f03a2ab17b72310a55b3191c10.jpg
             * ring_short_video_stream :
             * img_gif_url :
             * label :
             * brief : 范爷教主为爱入魔
             * tag :
             * metic_img :
             * metic_url :
             * version :
             * corner_mark :
             * is_long_video : 0
             * is_vip : 0
             * duration :
             * is_deep : 0
             * come_from :
             * tag_new :
             * tag_ext :
             * play_count : 0
             * update :
             * year_type : 2014 爱情 动作 古装 奇幻 武侠
             * year : 2014
             * is_sites : 1
             * nsclick_v : ?pid={pid}&id=456106&channel_name=adnativemovie&block_type=kehuan&block_name=%E5%B0%8F%E7%BC%96%E6%8E%A8%E8%8D%90&block_sign=23&mod=0&works_type=movie&video_type=long_video&video_id=36682&video_name=%E7%99%BD%E5%8F%91%E9%AD%94%E5%A5%B3%E4%BC%A0&video_url=&version=&item_id=&tag=movie&tpl=movie_selected&pos=_slices_0_videos_0
             */

            private String id;
            private String works_id;
            private String other_works_id;
            private String works_type;
            private String title;
            private String url;
            private String rating;
            private String large_imgh_url;
            private String imgh_url;
            private String imgv_url;
            private String ring_short_video_stream;
            private String img_gif_url;
            private String label;
            private String brief;
            private String tag;
            private String metic_img;
            private String metic_url;
            private String version;
            private String corner_mark;
            private String is_long_video;
            private String is_vip;
            private String duration;
            private String is_deep;
            private String come_from;
            private String tag_new;
            private String tag_ext;
            private String play_count;
            private String update;
            private String year_type;
            private String year;
            private int is_sites;
            private String nsclick_v;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getWorks_id() {
                return works_id;
            }

            public void setWorks_id(String works_id) {
                this.works_id = works_id;
            }

            public String getOther_works_id() {
                return other_works_id;
            }

            public void setOther_works_id(String other_works_id) {
                this.other_works_id = other_works_id;
            }

            public String getWorks_type() {
                return works_type;
            }

            public void setWorks_type(String works_type) {
                this.works_type = works_type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getRating() {
                return rating;
            }

            public void setRating(String rating) {
                this.rating = rating;
            }

            public String getLarge_imgh_url() {
                return large_imgh_url;
            }

            public void setLarge_imgh_url(String large_imgh_url) {
                this.large_imgh_url = large_imgh_url;
            }

            public String getImgh_url() {
                return imgh_url;
            }

            public void setImgh_url(String imgh_url) {
                this.imgh_url = imgh_url;
            }

            public String getImgv_url() {
                return imgv_url;
            }

            public void setImgv_url(String imgv_url) {
                this.imgv_url = imgv_url;
            }

            public String getRing_short_video_stream() {
                return ring_short_video_stream;
            }

            public void setRing_short_video_stream(String ring_short_video_stream) {
                this.ring_short_video_stream = ring_short_video_stream;
            }

            public String getImg_gif_url() {
                return img_gif_url;
            }

            public void setImg_gif_url(String img_gif_url) {
                this.img_gif_url = img_gif_url;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getBrief() {
                return brief;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getMetic_img() {
                return metic_img;
            }

            public void setMetic_img(String metic_img) {
                this.metic_img = metic_img;
            }

            public String getMetic_url() {
                return metic_url;
            }

            public void setMetic_url(String metic_url) {
                this.metic_url = metic_url;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public String getCorner_mark() {
                return corner_mark;
            }

            public void setCorner_mark(String corner_mark) {
                this.corner_mark = corner_mark;
            }

            public String getIs_long_video() {
                return is_long_video;
            }

            public void setIs_long_video(String is_long_video) {
                this.is_long_video = is_long_video;
            }

            public String getIs_vip() {
                return is_vip;
            }

            public void setIs_vip(String is_vip) {
                this.is_vip = is_vip;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getIs_deep() {
                return is_deep;
            }

            public void setIs_deep(String is_deep) {
                this.is_deep = is_deep;
            }

            public String getCome_from() {
                return come_from;
            }

            public void setCome_from(String come_from) {
                this.come_from = come_from;
            }

            public String getTag_new() {
                return tag_new;
            }

            public void setTag_new(String tag_new) {
                this.tag_new = tag_new;
            }

            public String getTag_ext() {
                return tag_ext;
            }

            public void setTag_ext(String tag_ext) {
                this.tag_ext = tag_ext;
            }

            public String getPlay_count() {
                return play_count;
            }

            public void setPlay_count(String play_count) {
                this.play_count = play_count;
            }

            public String getUpdate() {
                return update;
            }

            public void setUpdate(String update) {
                this.update = update;
            }

            public String getYear_type() {
                return year_type;
            }

            public void setYear_type(String year_type) {
                this.year_type = year_type;
            }

            public String getYear() {
                return year;
            }

            public void setYear(String year) {
                this.year = year;
            }

            public int getIs_sites() {
                return is_sites;
            }

            public void setIs_sites(int is_sites) {
                this.is_sites = is_sites;
            }

            public String getNsclick_v() {
                return nsclick_v;
            }

            public void setNsclick_v(String nsclick_v) {
                this.nsclick_v = nsclick_v;
            }
        }

        public static class HotBean implements Serializable {
            /**
             * id : 456293
             * works_id :
             * other_works_id :
             * works_type : movie
             * title : 《宝贝儿》杨幂演技到底如何？
             * url : http://baishi.baidu.com/watch/04209202404846842977.html
             * rating : 0
             * large_imgh_url : http://a.hiphotos.baidu.com/video/pic/item/a5c27d1ed21b0ef4f4777538d0c451da81cb3e3d.jpg
             * imgh_url : http://c.hiphotos.baidu.com/video/pic/item/a71ea8d3fd1f413458ade818281f95cad1c85e3a.jpg
             * imgv_url : http://c.hiphotos.baidu.com/video/pic/item/a686c9177f3e67091e36a99936c79f3df8dc55a6.jpg
             * ring_short_video_stream : J@KapFiBV2vhR@s0mp9tXd792g9TyQ9m2vrG2FfNxtDWR6QR52YN4R7Lrf81ZzggndrqV@f%rJK1rR6m5djBfbBhRg1wDVDO5q9BO31nsvpWTzKxT@9ktV7FpIwwDbKt5q9u#bBx86ghfJg2ybpTTX9QMQTamFpd2v8226imVtgGoFKnpZr%XzE58vY1f%2KnQF9#3fYDgf7XawxJFvqKJKVyQAGofuKJqKW%9snfV8VV6Y2Tfi1vZ7n0pgbXasVMg07v4BIX2JPoXExyKvI4ZLBj2iuoV6%nJvK5#TUxQrWR6QTcdg%XGQLytQSjRUpn329R3258JTCX4D5562H%a7YDQUwp31p5X9o2@1G02IPTag5TFLNXOunpQDwrGKvcq79#b6Zm91ZyQ6VmGp24Vuq02Yd23FZyZJ6OFll
             * img_gif_url :
             * label :
             * brief : 《宝贝儿》杨幂演技到底如何？
             * tag : shortvideo
             * metic_img :
             * metic_url :
             * version :
             * corner_mark :
             * is_long_video : 0
             * is_vip : 0
             * duration :
             * is_deep : 0
             * come_from :
             * tag_new :
             * tag_ext :
             * play_count : 0
             * update :
             * year_type :
             * year :
             * is_sites : 0
             * nsclick_v : ?pid={pid}&id=456293&channel_name=adnativemovie&block_type=index_flash&block_tag=index_flash&block_name=index_flash&block_sign=100&mod=0&video_name=%E3%80%8A%E5%AE%9D%E8%B4%9D%E5%84%BF%E3%80%8B%E6%9D%A8%E5%B9%82%E6%BC%94%E6%8A%80%E5%88%B0%E5%BA%95%E5%A6%82%E4%BD%95%EF%BC%9F&version=&tag=movie&tpl=movie_selected&pos=_slices_6_hot_0
             */

            private String id;
            private String works_id;
            private String other_works_id;
            private String works_type;
            private String title;
            private String url;
            private String rating;
            private String large_imgh_url;
            private String imgh_url;
            private String imgv_url;
            private String ring_short_video_stream;
            private String img_gif_url;
            private String label;
            private String brief;
            private String tag;
            private String metic_img;
            private String metic_url;
            private String version;
            private String corner_mark;
            private String is_long_video;
            private String is_vip;
            private String duration;
            private String is_deep;
            private String come_from;
            private String tag_new;
            private String tag_ext;
            private String play_count;
            private String update;
            private String year_type;
            private String year;
            private String is_sites;
            private String nsclick_v;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getWorks_id() {
                return works_id;
            }

            public void setWorks_id(String works_id) {
                this.works_id = works_id;
            }

            public String getOther_works_id() {
                return other_works_id;
            }

            public void setOther_works_id(String other_works_id) {
                this.other_works_id = other_works_id;
            }

            public String getWorks_type() {
                return works_type;
            }

            public void setWorks_type(String works_type) {
                this.works_type = works_type;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getRating() {
                return rating;
            }

            public void setRating(String rating) {
                this.rating = rating;
            }

            public String getLarge_imgh_url() {
                return large_imgh_url;
            }

            public void setLarge_imgh_url(String large_imgh_url) {
                this.large_imgh_url = large_imgh_url;
            }

            public String getImgh_url() {
                return imgh_url;
            }

            public void setImgh_url(String imgh_url) {
                this.imgh_url = imgh_url;
            }

            public String getImgv_url() {
                return imgv_url;
            }

            public void setImgv_url(String imgv_url) {
                this.imgv_url = imgv_url;
            }

            public String getRing_short_video_stream() {
                return ring_short_video_stream;
            }

            public void setRing_short_video_stream(String ring_short_video_stream) {
                this.ring_short_video_stream = ring_short_video_stream;
            }

            public String getImg_gif_url() {
                return img_gif_url;
            }

            public void setImg_gif_url(String img_gif_url) {
                this.img_gif_url = img_gif_url;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getBrief() {
                return brief;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getMetic_img() {
                return metic_img;
            }

            public void setMetic_img(String metic_img) {
                this.metic_img = metic_img;
            }

            public String getMetic_url() {
                return metic_url;
            }

            public void setMetic_url(String metic_url) {
                this.metic_url = metic_url;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public String getCorner_mark() {
                return corner_mark;
            }

            public void setCorner_mark(String corner_mark) {
                this.corner_mark = corner_mark;
            }

            public String getIs_long_video() {
                return is_long_video;
            }

            public void setIs_long_video(String is_long_video) {
                this.is_long_video = is_long_video;
            }

            public String getIs_vip() {
                return is_vip;
            }

            public void setIs_vip(String is_vip) {
                this.is_vip = is_vip;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getIs_deep() {
                return is_deep;
            }

            public void setIs_deep(String is_deep) {
                this.is_deep = is_deep;
            }

            public String getCome_from() {
                return come_from;
            }

            public void setCome_from(String come_from) {
                this.come_from = come_from;
            }

            public String getTag_new() {
                return tag_new;
            }

            public void setTag_new(String tag_new) {
                this.tag_new = tag_new;
            }

            public String getTag_ext() {
                return tag_ext;
            }

            public void setTag_ext(String tag_ext) {
                this.tag_ext = tag_ext;
            }

            public String getPlay_count() {
                return play_count;
            }

            public void setPlay_count(String play_count) {
                this.play_count = play_count;
            }

            public String getUpdate() {
                return update;
            }

            public void setUpdate(String update) {
                this.update = update;
            }

            public String getYear_type() {
                return year_type;
            }

            public void setYear_type(String year_type) {
                this.year_type = year_type;
            }

            public String getYear() {
                return year;
            }

            public void setYear(String year) {
                this.year = year;
            }

            public String getIs_sites() {
                return is_sites;
            }

            public void setIs_sites(String is_sites) {
                this.is_sites = is_sites;
            }

            public String getNsclick_v() {
                return nsclick_v;
            }

            public void setNsclick_v(String nsclick_v) {
                this.nsclick_v = nsclick_v;
            }
        }
    }
}
