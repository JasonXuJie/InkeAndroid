package com.jason.module_my.model.bean;

/**
 * Created by jason on 2018/12/26.
 */

public class PairingBean {


    /**
     * result : {"xingzuo1":"白羊座","xingzuo2":"射手座","title":"白羊座：射手座","content1":"友情：★★★★★\r\n爱情：★★★★★\r\n婚姻：★★★★\r\n亲情：★★★★","content2":"大家都是属于火象星座，非常合拍的一对，往往会一见钟情一眼就被对方吸引住，继而开始恋爱。而且相处下来，越来越合拍。你们是热情无比的组合，在公众场合也会肆无忌惮地表现出来，真是好得让旁人嫉妒！一生中各自为自己所追求的幸福而努力，大家都给予对方适度的自由，不会太主观将自己的意愿加在伴侣身上，堪称天生一对。"}
     * error_code : 0
     * reason : Succes
     */

    private ResultBean result;
    private int error_code;
    private String reason;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public static class ResultBean {
        /**
         * xingzuo1 : 白羊座
         * xingzuo2 : 射手座
         * title : 白羊座：射手座
         * content1 : 友情：★★★★★
         爱情：★★★★★
         婚姻：★★★★
         亲情：★★★★
         * content2 : 大家都是属于火象星座，非常合拍的一对，往往会一见钟情一眼就被对方吸引住，继而开始恋爱。而且相处下来，越来越合拍。你们是热情无比的组合，在公众场合也会肆无忌惮地表现出来，真是好得让旁人嫉妒！一生中各自为自己所追求的幸福而努力，大家都给予对方适度的自由，不会太主观将自己的意愿加在伴侣身上，堪称天生一对。
         */

        private String xingzuo1;
        private String xingzuo2;
        private String title;
        private String content1;
        private String content2;

        public String getXingzuo1() {
            return xingzuo1;
        }

        public void setXingzuo1(String xingzuo1) {
            this.xingzuo1 = xingzuo1;
        }

        public String getXingzuo2() {
            return xingzuo2;
        }

        public void setXingzuo2(String xingzuo2) {
            this.xingzuo2 = xingzuo2;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent1() {
            return content1;
        }

        public void setContent1(String content1) {
            this.content1 = content1;
        }

        public String getContent2() {
            return content2;
        }

        public void setContent2(String content2) {
            this.content2 = content2;
        }
    }
}
