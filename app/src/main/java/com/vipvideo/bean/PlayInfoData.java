package com.vipvideo.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by LIXH on 2019/1/2.
 * email lixhVip9@163.com
 * des
 */
public class PlayInfoData implements Serializable {

    /**
     * success : true
     * code : null
     * description : null
     * imgAddr : null
     * videoAddr : null
     * data : {"m3u8PlayUrl":"http://v.tian1886.com/mahua/1966/19rqpl2i8w/","m3u8Format":{"720P":"19rqpl2i8w_1_fa14c42f94b841898d17e26169de30b6.m3u8?token=cz05YzQ4MmI1YmI1YzIyNzczZTg3NTU2NWI1ZmY0OTAyYyZpPTI1Mzc2MzI3JnA9NyZ0PTE1NDY0MTQ5ODYmbj0xOXJxcGwyaTh3XzFfZmExNGM0MmY5NGI4NDE4OThkMTdlMjYxNjlkZTMwYjYubTN1OCZyPTEmdj0yLjQuMCZjPTImZT03MjA=","360P":"19rqpl2i8w_1_428d62c2fe9c4e75acad4cdd5feafe0c.m3u8?token=cz0yYTc4N2NjYjcyN2U2Njg3NGE1MzIwNDQ0ZDJkZmU1YSZpPTI1Mzc2MzI3JnA9NyZ0PTE1NDY0MTQ5ODYmbj0xOXJxcGwyaTh3XzFfNDI4ZDYyYzJmZTljNGU3NWFjYWQ0Y2RkNWZlYWZlMGMubTN1OCZyPTEmdj0yLjQuMCZjPTImZT03MjA=","480P":"19rqpl2i8w_1_7c05eb3059f74f22b9a15daec269b1b4.m3u8?token=cz0xMWIyYzk1ZDdkMmUzOGJjNjYwNGI0YWE2NWVhODVmZiZpPTI1Mzc2MzI3JnA9NyZ0PTE1NDY0MTQ5ODYmbj0xOXJxcGwyaTh3XzFfN2MwNWViMzA1OWY3NGYyMmI5YTE1ZGFlYzI2OWIxYjQubTN1OCZyPTEmdj0yLjQuMCZjPTImZT03MjA=","free":"19rqpl2i8w_1_8501d098d7b7446eae65909a23460539.m3u8?token=cz0yNGRhZWMzYmVkOWNmMWIwYzIyYmRjOTE5NDA0YmY0MiZpPTI1Mzc2MzI3JnA9NyZ0PTE1NDY0MTQ5ODYmbj0xOXJxcGwyaTh3XzFfODUwMWQwOThkN2I3NDQ2ZWFlNjU5MDlhMjM0NjA1MzkubTN1OCZyPTEmdj0yLjQuMCZjPTImZT03MjA="}}
     */

    private boolean success;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * m3u8PlayUrl : http://v.tian1886.com/mahua/1966/19rqpl2i8w/
         * m3u8Format : {"720P":"19rqpl2i8w_1_fa14c42f94b841898d17e26169de30b6.m3u8?token=cz05YzQ4MmI1YmI1YzIyNzczZTg3NTU2NWI1ZmY0OTAyYyZpPTI1Mzc2MzI3JnA9NyZ0PTE1NDY0MTQ5ODYmbj0xOXJxcGwyaTh3XzFfZmExNGM0MmY5NGI4NDE4OThkMTdlMjYxNjlkZTMwYjYubTN1OCZyPTEmdj0yLjQuMCZjPTImZT03MjA=","360P":"19rqpl2i8w_1_428d62c2fe9c4e75acad4cdd5feafe0c.m3u8?token=cz0yYTc4N2NjYjcyN2U2Njg3NGE1MzIwNDQ0ZDJkZmU1YSZpPTI1Mzc2MzI3JnA9NyZ0PTE1NDY0MTQ5ODYmbj0xOXJxcGwyaTh3XzFfNDI4ZDYyYzJmZTljNGU3NWFjYWQ0Y2RkNWZlYWZlMGMubTN1OCZyPTEmdj0yLjQuMCZjPTImZT03MjA=","480P":"19rqpl2i8w_1_7c05eb3059f74f22b9a15daec269b1b4.m3u8?token=cz0xMWIyYzk1ZDdkMmUzOGJjNjYwNGI0YWE2NWVhODVmZiZpPTI1Mzc2MzI3JnA9NyZ0PTE1NDY0MTQ5ODYmbj0xOXJxcGwyaTh3XzFfN2MwNWViMzA1OWY3NGYyMmI5YTE1ZGFlYzI2OWIxYjQubTN1OCZyPTEmdj0yLjQuMCZjPTImZT03MjA=","free":"19rqpl2i8w_1_8501d098d7b7446eae65909a23460539.m3u8?token=cz0yNGRhZWMzYmVkOWNmMWIwYzIyYmRjOTE5NDA0YmY0MiZpPTI1Mzc2MzI3JnA9NyZ0PTE1NDY0MTQ5ODYmbj0xOXJxcGwyaTh3XzFfODUwMWQwOThkN2I3NDQ2ZWFlNjU5MDlhMjM0NjA1MzkubTN1OCZyPTEmdj0yLjQuMCZjPTImZT03MjA="}
         */

        private String m3u8PlayUrl;
        private M3u8FormatBean m3u8Format;

        public String getM3u8PlayUrl() {
            return m3u8PlayUrl;
        }

        public void setM3u8PlayUrl(String m3u8PlayUrl) {
            this.m3u8PlayUrl = m3u8PlayUrl;
        }

        public M3u8FormatBean getM3u8Format() {
            return m3u8Format;
        }

        public void setM3u8Format(M3u8FormatBean m3u8Format) {
            this.m3u8Format = m3u8Format;
        }

        public static class M3u8FormatBean {
            /**
             * 720P : 19rqpl2i8w_1_fa14c42f94b841898d17e26169de30b6.m3u8?token=cz05YzQ4MmI1YmI1YzIyNzczZTg3NTU2NWI1ZmY0OTAyYyZpPTI1Mzc2MzI3JnA9NyZ0PTE1NDY0MTQ5ODYmbj0xOXJxcGwyaTh3XzFfZmExNGM0MmY5NGI4NDE4OThkMTdlMjYxNjlkZTMwYjYubTN1OCZyPTEmdj0yLjQuMCZjPTImZT03MjA=
             * 360P : 19rqpl2i8w_1_428d62c2fe9c4e75acad4cdd5feafe0c.m3u8?token=cz0yYTc4N2NjYjcyN2U2Njg3NGE1MzIwNDQ0ZDJkZmU1YSZpPTI1Mzc2MzI3JnA9NyZ0PTE1NDY0MTQ5ODYmbj0xOXJxcGwyaTh3XzFfNDI4ZDYyYzJmZTljNGU3NWFjYWQ0Y2RkNWZlYWZlMGMubTN1OCZyPTEmdj0yLjQuMCZjPTImZT03MjA=
             * 480P : 19rqpl2i8w_1_7c05eb3059f74f22b9a15daec269b1b4.m3u8?token=cz0xMWIyYzk1ZDdkMmUzOGJjNjYwNGI0YWE2NWVhODVmZiZpPTI1Mzc2MzI3JnA9NyZ0PTE1NDY0MTQ5ODYmbj0xOXJxcGwyaTh3XzFfN2MwNWViMzA1OWY3NGYyMmI5YTE1ZGFlYzI2OWIxYjQubTN1OCZyPTEmdj0yLjQuMCZjPTImZT03MjA=
             * free : 19rqpl2i8w_1_8501d098d7b7446eae65909a23460539.m3u8?token=cz0yNGRhZWMzYmVkOWNmMWIwYzIyYmRjOTE5NDA0YmY0MiZpPTI1Mzc2MzI3JnA9NyZ0PTE1NDY0MTQ5ODYmbj0xOXJxcGwyaTh3XzFfODUwMWQwOThkN2I3NDQ2ZWFlNjU5MDlhMjM0NjA1MzkubTN1OCZyPTEmdj0yLjQuMCZjPTImZT03MjA=
             */

            @JSONField(name = "720P")
            private String _$720P;
            @JSONField(name = "360P")
            private String _$360P;
            @JSONField(name = "480P")
            private String _$480P;
            private String free;

            public String get_$720P() {
                return _$720P;
            }

            public void set_$720P(String _$720P) {
                this._$720P = _$720P;
            }

            public String get_$360P() {
                return _$360P;
            }

            public void set_$360P(String _$360P) {
                this._$360P = _$360P;
            }

            public String get_$480P() {
                return _$480P;
            }

            public void set_$480P(String _$480P) {
                this._$480P = _$480P;
            }

            public String getFree() {
                return free;
            }

            public void setFree(String free) {
                this.free = free;
            }
        }
    }
}
