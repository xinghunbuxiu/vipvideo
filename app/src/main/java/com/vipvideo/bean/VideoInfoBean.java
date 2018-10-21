package com.vipvideo.bean;

import com.lixh.bean.BaseModel;

import java.io.Serializable;
import java.util.List;

public class VideoInfoBean extends BaseModel {

    /**
     * id : 131579
     * title : 密战国语
     * trunk : 密战
     * img_url : http://t3.baidu.com/it/u=3762833772,1363581167&fm=20
     * horizontal_poster :
     * intro : 淞沪会战后上海沦陷，地下工作者林翔（郭富城饰）受命来到危机四伏的上海，重建惨遭敌人破坏的地下抗日战线。在这里他遇到单纯却很有正义感的兰芳（赵丽颖饰），这对临时组成的“地下党夫妇”将在战火纷飞中，携手与日本侵略者及伪政府特务展开惊险刺激的生死较量……
     * rating : 5.1
     * duration : 100:0
     * pubtime : 2017
     * director : ["钟少雄"]
     * actor : ["郭富城","赵丽颖","张翰"]
     * area : ["内地"]
     * type : ["战争","动作","剧情"]
     * site_type : 16
     * play_filter : 2
     * tags : [""]
     * bdhd :
     * default_pos : recommend
     * sites : [{"site_name":"爱奇艺","site_logo":"http://c.hiphotos.baidu.com/video/pic/item/c2cec3fdfc039245fbec49718494a4c27d1e2576.png","site_url":"http://www.iqiyi.com/v_19rrebyuec.html?src=frbdaldjunest","subsitute_url":"","video_stream":"","site_domain":"iqiyi.com","tvid":"0","download":"1","download_filter":"2","value":"0","type":"movie","bcs_url":"","width":"0","height":"0","coprctl_play_mode":0,"coprctl_full_screen":0}]
     * dsites : []
     * foreign_ip : 0
     * buy_ticket : false
     * default_tab : 0
     * is_show_intro : 0
     */

    private String id;
    private String title;
    private String trunk;
    private String img_url;
    private String horizontal_poster;
    private String intro;
    private String rating;
    private String duration;
    private String pubtime;
    private String site_type;
    private String play_filter;
    private String bdhd;
    private String default_pos;
    private String foreign_ip;
    private boolean buy_ticket;
    private String default_tab;
    private String is_show_intro;
    private List<String> director;
    private List<String> actor;
    private List<String> area;
    private List<String> type;
    private List<String> tags;
    private List<SitesBean> sites;
    private List<?> dsites;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrunk() {
        return trunk;
    }

    public void setTrunk(String trunk) {
        this.trunk = trunk;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getHorizontal_poster() {
        return horizontal_poster;
    }

    public void setHorizontal_poster(String horizontal_poster) {
        this.horizontal_poster = horizontal_poster;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPubtime() {
        return pubtime;
    }

    public void setPubtime(String pubtime) {
        this.pubtime = pubtime;
    }

    public String getSite_type() {
        return site_type;
    }

    public void setSite_type(String site_type) {
        this.site_type = site_type;
    }

    public String getPlay_filter() {
        return play_filter;
    }

    public void setPlay_filter(String play_filter) {
        this.play_filter = play_filter;
    }

    public String getBdhd() {
        return bdhd;
    }

    public void setBdhd(String bdhd) {
        this.bdhd = bdhd;
    }

    public String getDefault_pos() {
        return default_pos;
    }

    public void setDefault_pos(String default_pos) {
        this.default_pos = default_pos;
    }

    public String getForeign_ip() {
        return foreign_ip;
    }

    public void setForeign_ip(String foreign_ip) {
        this.foreign_ip = foreign_ip;
    }

    public boolean isBuy_ticket() {
        return buy_ticket;
    }

    public void setBuy_ticket(boolean buy_ticket) {
        this.buy_ticket = buy_ticket;
    }

    public String getDefault_tab() {
        return default_tab;
    }

    public void setDefault_tab(String default_tab) {
        this.default_tab = default_tab;
    }

    public String getIs_show_intro() {
        return is_show_intro;
    }

    public void setIs_show_intro(String is_show_intro) {
        this.is_show_intro = is_show_intro;
    }

    public List<String> getDirector() {
        return director;
    }

    public void setDirector(List<String> director) {
        this.director = director;
    }

    public List<String> getActor() {
        return actor;
    }

    public void setActor(List<String> actor) {
        this.actor = actor;
    }

    public List<String> getArea() {
        return area;
    }

    public void setArea(List<String> area) {
        this.area = area;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<SitesBean> getSites() {
        return sites;
    }

    public void setSites(List<SitesBean> sites) {
        this.sites = sites;
    }

    public List<?> getDsites() {
        return dsites;
    }

    public void setDsites(List<?> dsites) {
        this.dsites = dsites;
    }

    public static class SitesBean implements Serializable {
        /**
         * site_name : 爱奇艺
         * site_logo : http://c.hiphotos.baidu.com/video/pic/item/c2cec3fdfc039245fbec49718494a4c27d1e2576.png
         * site_url : http://www.iqiyi.com/v_19rrebyuec.html?src=frbdaldjunest
         * subsitute_url :
         * video_stream :
         * site_domain : iqiyi.com
         * tvid : 0
         * download : 1
         * download_filter : 2
         * value : 0
         * type : movie
         * bcs_url :
         * width : 0
         * height : 0
         * coprctl_play_mode : 0
         * coprctl_full_screen : 0
         */

        private String site_name;
        private String site_logo;
        private String site_url;
        private String subsitute_url;
        private String video_stream;
        private String site_domain;
        private String tvid;
        private String download;
        private String download_filter;
        private String value;
        private String type;
        private String bcs_url;
        private String width;
        private String height;
        private int coprctl_play_mode;
        private int coprctl_full_screen;

        public String getSite_name() {
            return site_name;
        }

        public void setSite_name(String site_name) {
            this.site_name = site_name;
        }

        public String getSite_logo() {
            return site_logo;
        }

        public void setSite_logo(String site_logo) {
            this.site_logo = site_logo;
        }

        public String getSite_url() {
            return site_url;
        }

        public void setSite_url(String site_url) {
            this.site_url = site_url;
        }

        public String getSubsitute_url() {
            return subsitute_url;
        }

        public void setSubsitute_url(String subsitute_url) {
            this.subsitute_url = subsitute_url;
        }

        public String getVideo_stream() {
            return video_stream;
        }

        public void setVideo_stream(String video_stream) {
            this.video_stream = video_stream;
        }

        public String getSite_domain() {
            return site_domain;
        }

        public void setSite_domain(String site_domain) {
            this.site_domain = site_domain;
        }

        public String getTvid() {
            return tvid;
        }

        public void setTvid(String tvid) {
            this.tvid = tvid;
        }

        public String getDownload() {
            return download;
        }

        public void setDownload(String download) {
            this.download = download;
        }

        public String getDownload_filter() {
            return download_filter;
        }

        public void setDownload_filter(String download_filter) {
            this.download_filter = download_filter;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getBcs_url() {
            return bcs_url;
        }

        public void setBcs_url(String bcs_url) {
            this.bcs_url = bcs_url;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public int getCoprctl_play_mode() {
            return coprctl_play_mode;
        }

        public void setCoprctl_play_mode(int coprctl_play_mode) {
            this.coprctl_play_mode = coprctl_play_mode;
        }

        public int getCoprctl_full_screen() {
            return coprctl_full_screen;
        }

        public void setCoprctl_full_screen(int coprctl_full_screen) {
            this.coprctl_full_screen = coprctl_full_screen;
        }
    }
}
