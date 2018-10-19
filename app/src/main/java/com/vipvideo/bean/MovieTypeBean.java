package com.vipvideo.bean;


import com.lixh.bean.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LIXH on 2018/10/10.
 * email lixhVip9@163.com
 * des
 */

public class MovieTypeBean extends BaseModel {


    /**
     * conds : [{"name":"类型","field":"type","values":[{"title":"动作","term":"动作"},{"title":"恐怖","term":"恐怖"},{"title":"喜剧","term":"喜剧"},{"title":"动画","term":"动画"},{"title":"剧情","term":"剧情"},{"title":"战争","term":"战争"},{"title":"爱情","term":"爱情"},{"title":"科幻","term":"科幻"},{"title":"武侠","term":"武侠"},{"title":"古装","term":"古装"},{"title":"伦理","term":"伦理"},{"title":"家庭","term":"家庭"},{"title":"犯罪","term":"犯罪"},{"title":"惊悚","term":"惊悚"},{"title":"悬疑","term":"悬疑"},{"title":"奇幻","term":"奇幻"},{"title":"历史","term":"历史"},{"title":"青春","term":"青春"},{"title":"冒险","term":"冒险"},{"title":"传记","term":"传记"},{"title":"音乐","term":"音乐"},{"title":"运动","term":"运动"},{"title":"VIP","term":"VIP"},{"title":"网络大电影","term":"网络大电影"}]},{"name":"国家/地区","field":"area","values":[{"title":"内地","term":"内地"},{"title":"美国","term":"美国"},{"title":"香港","term":"香港"},{"title":"韩国","term":"韩国"},{"title":"日本","term":"日本"},{"title":"台湾","term":"台湾"},{"title":"法国","term":"法国"},{"title":"英国","term":"英国"},{"title":"德国","term":"德国"},{"title":"泰国","term":"泰国"},{"title":"欧洲地区","term":"欧洲地区"},{"title":"东南亚地区","term":"东南亚地区"},{"title":"其他地区","term":"其他地区"}]},{"name":"年代","field":"start","values":[{"title":"2018","term":"2018"},{"title":"2017","term":"2017"},{"title":"2016","term":"2016"},{"title":"2015","term":"2015"},{"title":"2014","term":"2014"},{"title":"2013","term":"2013"},{"title":"2012","term":"2012"},{"title":"2011","term":"2011"},{"title":"2010","term":"2010"},{"title":"00年代","term":"00年代"},{"title":"90年代","term":"90年代"},{"title":"80年代","term":"80年代"}]},{"name":"演员","field":"actor","values":[{"title":"吴京","term":"吴京"},{"title":"成龙","term":"成龙"},{"title":"林正英","term":"林正英"},{"title":"黄渤","term":"黄渤"},{"title":"王宝强","term":"王宝强"},{"title":"周星驰","term":"周星驰"},{"title":"李连杰","term":"李连杰"},{"title":"周润发","term":"周润发"},{"title":"刘德华","term":"刘德华"},{"title":"甄子丹","term":"甄子丹"},{"title":"洪金宝","term":"洪金宝"},{"title":"林青霞","term":"林青霞"},{"title":"张家辉","term":"张家辉"},{"title":"梁朝伟","term":"梁朝伟"},{"title":"徐峥","term":"徐峥"},{"title":"范冰冰","term":"范冰冰"},{"title":"陈小春","term":"陈小春"},{"title":"周迅","term":"周迅"},{"title":"周冬雨","term":"周冬雨"},{"title":"古天乐","term":"古天乐"},{"title":"葛优","term":"葛优"},{"title":"林志玲","term":"林志玲"},{"title":"林心如","term":"林心如"},{"title":"周杰伦","term":"周杰伦"},{"title":"王祖贤","term":"王祖贤"}]}]
     * orders : [{"name":"热播榜","field":"hot"},{"name":"新上线","field":"pubtime"},{"name":"好评榜","field":"rating"}]
     * topconds : [{"title":"全部","type":"","layout":"0","url":"http://app.video.baidu.com/adnativemovie/","nsclick_v":"?pid={pid}&tpl_time=1539849667&channel_name=adnativemovie&video_name=%E5%85%A8%E9%83%A8&pos=0&tpl=conds"},{"title":"精选","type":"","layout":"1","url":"http://app.video.baidu.com/wiseselected/?worktype=adnativemovie","nsclick_v":"?pid={pid}&tpl_time=1539849667&channel_name=adnativemovie&video_name=%E7%B2%BE%E9%80%89&pos=1&tpl=conds"},{"title":"最新","start":"最新","order":"hot","layout":"0","url":"http://app.video.baidu.com/adnativemovie/?start=最新&order=hot","nsclick_v":"?pid={pid}&tpl_time=1539849667&channel_name=adnativemovie&video_name=%E6%9C%80%E6%96%B0&pos=2&tpl=conds"},{"title":"动作","type":"动作","order":"hot","layout":"0","url":"http://app.video.baidu.com/adnativemovie/?type=动作&order=hot","nsclick_v":"?pid={pid}&tpl_time=1539849667&channel_name=adnativemovie&video_name=%E5%8A%A8%E4%BD%9C&pos=3&tpl=conds"},{"title":"美国","area":"美国","order":"hot","layout":"0","url":"http://app.video.baidu.com/adnativemovie/?area=美国&order=hot","nsclick_v":"?pid={pid}&tpl_time=1539849667&channel_name=adnativemovie&video_name=%E7%BE%8E%E5%9B%BD&pos=4&tpl=conds"},{"title":"喜剧","type":"喜剧","order":"hot","layout":"0","url":"http://app.video.baidu.com/adnativemovie/?type=喜剧&order=hot","nsclick_v":"?pid={pid}&tpl_time=1539849667&channel_name=adnativemovie&video_name=%E5%96%9C%E5%89%A7&pos=5&tpl=conds"},{"title":"动画","type":"动画","order":"hot","layout":"0","url":"http://app.video.baidu.com/adnativemovie/?type=动画&order=hot","nsclick_v":"?pid={pid}&tpl_time=1539849667&channel_name=adnativemovie&video_name=%E5%8A%A8%E7%94%BB&pos=6&tpl=conds"},{"title":"剧情","type":"剧情","order":"hot","layout":"0","url":"http://app.video.baidu.com/adnativemovie/?type=剧情&order=hot","nsclick_v":"?pid={pid}&tpl_time=1539849667&channel_name=adnativemovie&video_name=%E5%89%A7%E6%83%85&pos=7&tpl=conds"},{"title":"恐怖","type":"恐怖","order":"hot","layout":"0","url":"http://app.video.baidu.com/adnativemovie/?type=恐怖&order=hot","nsclick_v":"?pid={pid}&tpl_time=1539849667&channel_name=adnativemovie&video_name=%E6%81%90%E6%80%96&pos=8&tpl=conds"},{"title":"内地","area":"内地","order":"hot","layout":"0","url":"http://app.video.baidu.com/adnativemovie/?area=内地&order=hot","nsclick_v":"?pid={pid}&tpl_time=1539849667&channel_name=adnativemovie&video_name=%E5%86%85%E5%9C%B0&pos=9&tpl=conds"}]
     * nsclick_p : ?pid={pid}&tpl_time=1539849667&channel_name=adnativemovie&tpl=conds
     */
    private List<CondsBean> conds;
    private List<CondsBean> orders;
    private List<TopcondsBean> topconds;

  
    public List<CondsBean> getConds() {
        return conds;
    }

    public void setConds(List<CondsBean> conds) {
        this.conds = conds;
    }

    public List<CondsBean> getOrders() {
        return orders;
    }

    public void setOrders(List<CondsBean> orders) {
        this.orders = orders;
    }

    public List<TopcondsBean> getTopconds() {
        return topconds;
    }

    public void setTopconds(List<TopcondsBean> topconds) {
        this.topconds = topconds;
    }

    public static class CondsBean implements Serializable {
        /**
         * name : 类型
         * field : type
         * values : [{"title":"动作","term":"动作"},{"title":"恐怖","term":"恐怖"},{"title":"喜剧","term":"喜剧"},{"title":"动画","term":"动画"},{"title":"剧情","term":"剧情"},{"title":"战争","term":"战争"},{"title":"爱情","term":"爱情"},{"title":"科幻","term":"科幻"},{"title":"武侠","term":"武侠"},{"title":"古装","term":"古装"},{"title":"伦理","term":"伦理"},{"title":"家庭","term":"家庭"},{"title":"犯罪","term":"犯罪"},{"title":"惊悚","term":"惊悚"},{"title":"悬疑","term":"悬疑"},{"title":"奇幻","term":"奇幻"},{"title":"历史","term":"历史"},{"title":"青春","term":"青春"},{"title":"冒险","term":"冒险"},{"title":"传记","term":"传记"},{"title":"音乐","term":"音乐"},{"title":"运动","term":"运动"},{"title":"VIP","term":"VIP"},{"title":"网络大电影","term":"网络大电影"}]
         */

        private String name;
        private String field;
        private List<ValuesBean> values;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public List<ValuesBean> getValues() {
            return values;
        }

        public void setValues(List<ValuesBean> values) {
            this.values = values;
        }

        public static class ValuesBean implements Serializable {
            /**
             * title : 动作
             * term : 动作
             */

            private String title;
            private String term;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTerm() {
                return term;
            }

            public void setTerm(String term) {
                this.term = term;
            }
        }
    }

    public static class TopcondsBean implements Serializable{
        /**
         * title : 全部
         * type :
         * layout : 0
         * url : http://app.video.baidu.com/adnativemovie/
         * nsclick_v : ?pid={pid}&tpl_time=1539849667&channel_name=adnativemovie&video_name=%E5%85%A8%E9%83%A8&pos=0&tpl=conds
         * start : 最新
         * order : hot
         * area : 美国
         */

        private String title;
        private String type;
        private String layout;
        private String url;
        private String nsclick_v;
        private String start;
        private String order;
        private String area;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLayout() {
            return layout;
        }

        public void setLayout(String layout) {
            this.layout = layout;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getNsclick_v() {
            return nsclick_v;
        }

        public void setNsclick_v(String nsclick_v) {
            this.nsclick_v = nsclick_v;
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }
    }
}
