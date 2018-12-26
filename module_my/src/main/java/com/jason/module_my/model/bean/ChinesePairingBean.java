package com.jason.module_my.model.bean;

/**
 * Created by jason on 2018/12/26.
 */

public class ChinesePairingBean {


    /**
     * result : {"shengxiao1":"马","shengxiao2":"猪","title":"马：猪","content1":"男马＋女猪：你不应占她便宜，不然爱情无法继续。","content2":"男猪＋女马：两人的性格不同，相处不会快乐。"}
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
         * shengxiao1 : 马
         * shengxiao2 : 猪
         * title : 马：猪
         * content1 : 男马＋女猪：你不应占她便宜，不然爱情无法继续。
         * content2 : 男猪＋女马：两人的性格不同，相处不会快乐。
         */

        private String shengxiao1;
        private String shengxiao2;
        private String title;
        private String content1;
        private String content2;

        public String getShengxiao1() {
            return shengxiao1;
        }

        public void setShengxiao1(String shengxiao1) {
            this.shengxiao1 = shengxiao1;
        }

        public String getShengxiao2() {
            return shengxiao2;
        }

        public void setShengxiao2(String shengxiao2) {
            this.shengxiao2 = shengxiao2;
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
