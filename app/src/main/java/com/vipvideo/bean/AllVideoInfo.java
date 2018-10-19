package com.vipvideo.bean;

import com.lixh.bean.BaseModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LIXH on 2018/10/19.
 * email lixhVip9@163.com
 * des
 */

public class AllVideoInfo extends BaseModel {

    /**
     * video_list : {"video_num":2184,"video_count_display":"共2184部电影","beg":"0","end":"18","videos":[{"title":"战狼","works_id":"128526","img_url":"http://t2.baidu.com/it/u=1575294170,2889367559&fm=20","url":"http://app.video.baidu.com/movieintro/?page=1&id=128526","update":0,"duration":"120:0","rating":"8.0","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D128526&video_name=%E6%88%98%E7%8B%BC&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=128526&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"24小时(末路重生) ","works_id":"132886","img_url":"http://vorcdn.xiaodutv.com/297856756d597fad58fbbbc786c7a52c","url":"http://app.video.baidu.com/movieintro/?page=1&id=132886","update":0,"duration":"90:0","rating":"6.7","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D132886&video_name=24%E5%B0%8F%E6%97%B6%28%E6%9C%AB%E8%B7%AF%E9%87%8D%E7%94%9F%29+&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=132886&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"密战","works_id":"131579","img_url":"http://t3.baidu.com/it/u=3762833772,1363581167&fm=20","url":"http://app.video.baidu.com/movieintro/?page=1&id=131579","update":0,"duration":"100:0","rating":"5.1","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D131579&video_name=%E5%AF%86%E6%88%98&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=131579&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"追龙 ","works_id":"131151","img_url":"http://t3.baidu.com/it/u=778935104,2795489376&fm=20","url":"http://app.video.baidu.com/movieintro/?page=1&id=131151","update":0,"duration":"100:0","rating":"8.1","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D131151&video_name=%E8%BF%BD%E9%BE%99+&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=131151&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"战神纪","works_id":"132343","img_url":"http://vorcdn.xiaodutv.com/27f8649a59b55f45b82e762543f418ed","url":"http://app.video.baidu.com/movieintro/?page=1&id=132343","update":0,"duration":"90:0","rating":"4.5","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D132343&video_name=%E6%88%98%E7%A5%9E%E7%BA%AA&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=132343&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"御宠娇妃","works_id":"132438","img_url":"http://t1.baidu.com/it/u=332146208,2873567734&fm=20","url":"http://app.video.baidu.com/movieintro/?page=1&id=132438","update":0,"duration":"0:0","rating":"0","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D132438&video_name=%E5%BE%A1%E5%AE%A0%E5%A8%87%E5%A6%83&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=132438&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"寒战","works_id":"123193","img_url":"http://t3.baidu.com/it/u=1091601618,1886697737&fm=20","url":"http://app.video.baidu.com/movieintro/?page=1&id=123193","update":0,"duration":"90:0","rating":"7.9","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D123193&video_name=%E5%AF%92%E6%88%98&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=123193&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"羞羞的铁拳","works_id":"129765","img_url":"http://t1.baidu.com/it/u=2079389346,2281979844&fm=20","url":"http://app.video.baidu.com/movieintro/?page=1&id=129765","update":0,"duration":"90:0","rating":"7.9","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D129765&video_name=%E7%BE%9E%E7%BE%9E%E7%9A%84%E9%93%81%E6%8B%B3&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=129765&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"古墓入侵者 英语","works_id":"135535","img_url":"http://tvpic.bbtv.cn/20180904/16/1CMHRLQHH63T.jpg","url":"http://app.video.baidu.com/movieintro/?page=1&id=135535","update":0,"duration":"0:0","rating":"0","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D135535&video_name=%E5%8F%A4%E5%A2%93%E5%85%A5%E4%BE%B5%E8%80%85+%E8%8B%B1%E8%AF%AD&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=135535&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"湄公河行动","works_id":"124965","img_url":"http://t2.baidu.com/it/u=4121361450,49249405&fm=20","url":"http://app.video.baidu.com/movieintro/?page=1&id=124965","update":0,"duration":"90:0","rating":"8.6","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D124965&video_name=%E6%B9%84%E5%85%AC%E6%B2%B3%E8%A1%8C%E5%8A%A8&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=124965&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"爱上调音师","works_id":"129919","img_url":"http://vorcdn.xiaodutv.com/0ed9a93e94fc141bd1e50cb8913e524f","url":"http://app.video.baidu.com/movieintro/?page=1&id=129919","update":0,"duration":"0:0","rating":"0","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D129919&video_name=%E7%88%B1%E4%B8%8A%E8%B0%83%E9%9F%B3%E5%B8%88&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=129919&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"识色幸也","works_id":"131824","img_url":"http://t2.baidu.com/it/u=1943861119,3222743841&fm=20","url":"http://app.video.baidu.com/movieintro/?page=1&id=131824","update":0,"duration":"105:0","rating":"4.3","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D131824&video_name=%E8%AF%86%E8%89%B2%E5%B9%B8%E4%B9%9F&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=131824&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"欲爱","works_id":"125174","img_url":"http://t1.baidu.com/it/u=3839196237,2676619227&fm=20","url":"http://app.video.baidu.com/movieintro/?page=1&id=125174","update":0,"duration":"0:0","rating":"0","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D125174&video_name=%E6%AC%B2%E7%88%B1&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=125174&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"灵魂摆渡黄泉","works_id":"133280","img_url":"http://t3.baidu.com/it/u=188329016,2046225546&fm=20","url":"http://app.video.baidu.com/movieintro/?page=1&id=133280","update":0,"duration":"110:0","rating":"0","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D133280&video_name=%E7%81%B5%E9%AD%82%E6%91%86%E6%B8%A1%E9%BB%84%E6%B3%89&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=133280&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"情况不妙","works_id":"124463","img_url":"http://t2.baidu.com/it/u=4269850943,3450531494&fm=20","url":"http://app.video.baidu.com/movieintro/?page=1&id=124463","update":0,"duration":"90:0","rating":"7.6","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D124463&video_name=%E6%83%85%E5%86%B5%E4%B8%8D%E5%A6%99&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=124463&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"熊出没(变形记)","works_id":"133124","img_url":"http://t3.baidu.com/it/u=1069856494,943323658&fm=20","url":"http://app.video.baidu.com/movieintro/?page=1&id=133124","update":0,"duration":"0:0","rating":"7.8","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D133124&video_name=%E7%86%8A%E5%87%BA%E6%B2%A1%28%E5%8F%98%E5%BD%A2%E8%AE%B0%29&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=133124&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"我要再来一次","works_id":"124021","img_url":"http://vorcdn.xiaodutv.com/a4093ed710a8a0fd92f0ce0674d5429d","url":"http://app.video.baidu.com/movieintro/?page=1&id=124021","update":0,"duration":"60:0","rating":"0","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D124021&video_name=%E6%88%91%E8%A6%81%E5%86%8D%E6%9D%A5%E4%B8%80%E6%AC%A1&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=124021&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"机器之血","works_id":"129712","img_url":"http://t2.baidu.com/it/u=3139004182,2902934868&fm=20","url":"http://app.video.baidu.com/movieintro/?page=1&id=129712","update":0,"duration":"100:0","rating":"5.6","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D129712&video_name=%E6%9C%BA%E5%99%A8%E4%B9%8B%E8%A1%80&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=129712&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"}],"nsclick_p":"?pid={pid}&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410&pn=0_18&city=1&ins=128526,132886,131579,131151,132343,132438,123193,129765,135535,124965,129919,131824,125174,133280,124463,133124,124021,129712"}
     * cur_conds : [{"key":"type","value":""},{"key":"area","value":""},{"key":"actor","value":""},{"key":"start","value":""},{"key":"order","value":"hot"}]
     */

    private VideoListBean video_list;
    private List<CurCondsBean> cur_conds;

    public VideoListBean getVideo_list() {
        return video_list;
    }

    public void setVideo_list(VideoListBean video_list) {
        this.video_list = video_list;
    }

    public List<CurCondsBean> getCur_conds() {
        return cur_conds;
    }

    public void setCur_conds(List<CurCondsBean> cur_conds) {
        this.cur_conds = cur_conds;
    }

    public static class VideoListBean implements Serializable{
        /**
         * video_num : 2184
         * video_count_display : 共2184部电影
         * beg : 0
         * end : 18
         * videos : [{"title":"战狼","works_id":"128526","img_url":"http://t2.baidu.com/it/u=1575294170,2889367559&fm=20","url":"http://app.video.baidu.com/movieintro/?page=1&id=128526","update":0,"duration":"120:0","rating":"8.0","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D128526&video_name=%E6%88%98%E7%8B%BC&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=128526&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"24小时(末路重生) ","works_id":"132886","img_url":"http://vorcdn.xiaodutv.com/297856756d597fad58fbbbc786c7a52c","url":"http://app.video.baidu.com/movieintro/?page=1&id=132886","update":0,"duration":"90:0","rating":"6.7","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D132886&video_name=24%E5%B0%8F%E6%97%B6%28%E6%9C%AB%E8%B7%AF%E9%87%8D%E7%94%9F%29+&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=132886&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"密战","works_id":"131579","img_url":"http://t3.baidu.com/it/u=3762833772,1363581167&fm=20","url":"http://app.video.baidu.com/movieintro/?page=1&id=131579","update":0,"duration":"100:0","rating":"5.1","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D131579&video_name=%E5%AF%86%E6%88%98&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=131579&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"追龙 ","works_id":"131151","img_url":"http://t3.baidu.com/it/u=778935104,2795489376&fm=20","url":"http://app.video.baidu.com/movieintro/?page=1&id=131151","update":0,"duration":"100:0","rating":"8.1","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D131151&video_name=%E8%BF%BD%E9%BE%99+&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=131151&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"战神纪","works_id":"132343","img_url":"http://vorcdn.xiaodutv.com/27f8649a59b55f45b82e762543f418ed","url":"http://app.video.baidu.com/movieintro/?page=1&id=132343","update":0,"duration":"90:0","rating":"4.5","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D132343&video_name=%E6%88%98%E7%A5%9E%E7%BA%AA&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=132343&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"御宠娇妃","works_id":"132438","img_url":"http://t1.baidu.com/it/u=332146208,2873567734&fm=20","url":"http://app.video.baidu.com/movieintro/?page=1&id=132438","update":0,"duration":"0:0","rating":"0","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D132438&video_name=%E5%BE%A1%E5%AE%A0%E5%A8%87%E5%A6%83&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=132438&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"寒战","works_id":"123193","img_url":"http://t3.baidu.com/it/u=1091601618,1886697737&fm=20","url":"http://app.video.baidu.com/movieintro/?page=1&id=123193","update":0,"duration":"90:0","rating":"7.9","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D123193&video_name=%E5%AF%92%E6%88%98&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=123193&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"羞羞的铁拳","works_id":"129765","img_url":"http://t1.baidu.com/it/u=2079389346,2281979844&fm=20","url":"http://app.video.baidu.com/movieintro/?page=1&id=129765","update":0,"duration":"90:0","rating":"7.9","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D129765&video_name=%E7%BE%9E%E7%BE%9E%E7%9A%84%E9%93%81%E6%8B%B3&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=129765&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"古墓入侵者 英语","works_id":"135535","img_url":"http://tvpic.bbtv.cn/20180904/16/1CMHRLQHH63T.jpg","url":"http://app.video.baidu.com/movieintro/?page=1&id=135535","update":0,"duration":"0:0","rating":"0","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D135535&video_name=%E5%8F%A4%E5%A2%93%E5%85%A5%E4%BE%B5%E8%80%85+%E8%8B%B1%E8%AF%AD&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=135535&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"湄公河行动","works_id":"124965","img_url":"http://t2.baidu.com/it/u=4121361450,49249405&fm=20","url":"http://app.video.baidu.com/movieintro/?page=1&id=124965","update":0,"duration":"90:0","rating":"8.6","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D124965&video_name=%E6%B9%84%E5%85%AC%E6%B2%B3%E8%A1%8C%E5%8A%A8&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=124965&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"爱上调音师","works_id":"129919","img_url":"http://vorcdn.xiaodutv.com/0ed9a93e94fc141bd1e50cb8913e524f","url":"http://app.video.baidu.com/movieintro/?page=1&id=129919","update":0,"duration":"0:0","rating":"0","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D129919&video_name=%E7%88%B1%E4%B8%8A%E8%B0%83%E9%9F%B3%E5%B8%88&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=129919&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"识色幸也","works_id":"131824","img_url":"http://t2.baidu.com/it/u=1943861119,3222743841&fm=20","url":"http://app.video.baidu.com/movieintro/?page=1&id=131824","update":0,"duration":"105:0","rating":"4.3","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D131824&video_name=%E8%AF%86%E8%89%B2%E5%B9%B8%E4%B9%9F&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=131824&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"欲爱","works_id":"125174","img_url":"http://t1.baidu.com/it/u=3839196237,2676619227&fm=20","url":"http://app.video.baidu.com/movieintro/?page=1&id=125174","update":0,"duration":"0:0","rating":"0","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D125174&video_name=%E6%AC%B2%E7%88%B1&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=125174&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"灵魂摆渡黄泉","works_id":"133280","img_url":"http://t3.baidu.com/it/u=188329016,2046225546&fm=20","url":"http://app.video.baidu.com/movieintro/?page=1&id=133280","update":0,"duration":"110:0","rating":"0","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D133280&video_name=%E7%81%B5%E9%AD%82%E6%91%86%E6%B8%A1%E9%BB%84%E6%B3%89&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=133280&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"情况不妙","works_id":"124463","img_url":"http://t2.baidu.com/it/u=4269850943,3450531494&fm=20","url":"http://app.video.baidu.com/movieintro/?page=1&id=124463","update":0,"duration":"90:0","rating":"7.6","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D124463&video_name=%E6%83%85%E5%86%B5%E4%B8%8D%E5%A6%99&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=124463&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"熊出没(变形记)","works_id":"133124","img_url":"http://t3.baidu.com/it/u=1069856494,943323658&fm=20","url":"http://app.video.baidu.com/movieintro/?page=1&id=133124","update":0,"duration":"0:0","rating":"7.8","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D133124&video_name=%E7%86%8A%E5%87%BA%E6%B2%A1%28%E5%8F%98%E5%BD%A2%E8%AE%B0%29&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=133124&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"我要再来一次","works_id":"124021","img_url":"http://vorcdn.xiaodutv.com/a4093ed710a8a0fd92f0ce0674d5429d","url":"http://app.video.baidu.com/movieintro/?page=1&id=124021","update":0,"duration":"60:0","rating":"0","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D124021&video_name=%E6%88%91%E8%A6%81%E5%86%8D%E6%9D%A5%E4%B8%80%E6%AC%A1&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=124021&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"},{"title":"机器之血","works_id":"129712","img_url":"http://t2.baidu.com/it/u=3139004182,2902934868&fm=20","url":"http://app.video.baidu.com/movieintro/?page=1&id=129712","update":0,"duration":"100:0","rating":"5.6","is_yingyin":0,"play_filter":"0","works_type":"movie","nsclick_v":"?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D129712&video_name=%E6%9C%BA%E5%99%A8%E4%B9%8B%E8%A1%80&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=129712&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410"}]
         * nsclick_p : ?pid={pid}&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410&pn=0_18&city=1&ins=128526,132886,131579,131151,132343,132438,123193,129765,135535,124965,129919,131824,125174,133280,124463,133124,124021,129712
         */

        private int video_num;
        private String video_count_display;
        private String beg;
        private String end;
        private String nsclick_p;
        private List<VideosBean> videos;

        public int getVideo_num() {
            return video_num;
        }

        public void setVideo_num(int video_num) {
            this.video_num = video_num;
        }

        public String getVideo_count_display() {
            return video_count_display;
        }

        public void setVideo_count_display(String video_count_display) {
            this.video_count_display = video_count_display;
        }

        public String getBeg() {
            return beg;
        }

        public void setBeg(String beg) {
            this.beg = beg;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public String getNsclick_p() {
            return nsclick_p;
        }

        public void setNsclick_p(String nsclick_p) {
            this.nsclick_p = nsclick_p;
        }

        public List<VideosBean> getVideos() {
            return videos;
        }

        public void setVideos(List<VideosBean> videos) {
            this.videos = videos;
        }

        public static class VideosBean implements Serializable{
            /**
             * title : 战狼
             * works_id : 128526
             * img_url : http://t2.baidu.com/it/u=1575294170,2889367559&fm=20
             * url : http://app.video.baidu.com/movieintro/?page=1&id=128526
             * update : 0
             * duration : 120:0
             * rating : 8.0
             * is_yingyin : 0
             * play_filter : 0
             * works_type : movie
             * nsclick_v : ?pid={pid}&block_type=hot&block_name=all_all_all_all_all&video_url=http%3A%2F%2Fapp.video.baidu.com%2Fmovieintro%2F%3Fpage%3D1%26id%3D128526&video_name=%E6%88%98%E7%8B%BC&to=xq&pn=0_18&pos=videos_&video_type=long_video&works_type=movie&video_id=128526&tpl=long_channel&channel_name=channel_movie&tpl_time=1539938410
             */

            private String title;
            private String works_id;
            private String img_url;
            private String url;
            private int update;
            private String duration;
            private String rating;
            private int is_yingyin;
            private String play_filter;
            private String works_type;
            private String nsclick_v;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getWorks_id() {
                return works_id;
            }

            public void setWorks_id(String works_id) {
                this.works_id = works_id;
            }

            public String getImg_url() {
                return img_url;
            }

            public void setImg_url(String img_url) {
                this.img_url = img_url;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getUpdate() {
                return update;
            }

            public void setUpdate(int update) {
                this.update = update;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getRating() {
                return rating;
            }

            public void setRating(String rating) {
                this.rating = rating;
            }

            public int getIs_yingyin() {
                return is_yingyin;
            }

            public void setIs_yingyin(int is_yingyin) {
                this.is_yingyin = is_yingyin;
            }

            public String getPlay_filter() {
                return play_filter;
            }

            public void setPlay_filter(String play_filter) {
                this.play_filter = play_filter;
            }

            public String getWorks_type() {
                return works_type;
            }

            public void setWorks_type(String works_type) {
                this.works_type = works_type;
            }

            public String getNsclick_v() {
                return nsclick_v;
            }

            public void setNsclick_v(String nsclick_v) {
                this.nsclick_v = nsclick_v;
            }
        }
    }

    public static class CurCondsBean implements Serializable{
        /**
         * key : type
         * value :
         */

        private String key;
        private String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
