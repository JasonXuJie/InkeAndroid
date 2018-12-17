package com.jason.tools.http.bean;

import java.util.List;

/**
 * Created by jason on 2018/11/26.
 */

public class NewsResult {



    private String stat;
    private List<News> data;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public List<News> getData() {
        return data;
    }

    public void setData(List<News> data) {
        this.data = data;
    }

    public static class News {
        /**
         * uniquekey : 89ebe352d401df7b8f99fefbcedd6f82
         * title : 中国女子围棋名人战在中国棋院决出四强
         * date : 2018-11-26 14:35
         * category : 头条
         * author_name : 国家体育总局
         * url : http://mini.eastday.com/mobile/181126143526749.html
         * thumbnail_pic_s : http://01imgmini.eastday.com/mobile/20181126/20181126143526_368278faae9ea8aa428d1d08cc2a4cac_1_mwpm_03200403.jpg
         * thumbnail_pic_s02 : http://01imgmini.eastday.com/mobile/20181126/20181126143503_8ad1ca98fe16dcca4fe07d708ff1339b_6_mwpm_03200403.jpg
         * thumbnail_pic_s03 : http://01imgmini.eastday.com/mobile/20181126/20181126143503_8ad1ca98fe16dcca4fe07d708ff1339b_1_mwpm_03200403.jpg
         */

        private String uniquekey;
        private String title;
        private String date;
        private String category;
        private String author_name;
        private String url;
        private String thumbnail_pic_s;
        private String thumbnail_pic_s02;
        private String thumbnail_pic_s03;

        public String getUniquekey() {
            return uniquekey;
        }

        public void setUniquekey(String uniquekey) {
            this.uniquekey = uniquekey;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getAuthor_name() {
            return author_name;
        }

        public void setAuthor_name(String author_name) {
            this.author_name = author_name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumbnail_pic_s() {
            return thumbnail_pic_s;
        }

        public void setThumbnail_pic_s(String thumbnail_pic_s) {
            this.thumbnail_pic_s = thumbnail_pic_s;
        }

        public String getThumbnail_pic_s02() {
            return thumbnail_pic_s02;
        }

        public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
            this.thumbnail_pic_s02 = thumbnail_pic_s02;
        }

        public String getThumbnail_pic_s03() {
            return thumbnail_pic_s03;
        }

        public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
            this.thumbnail_pic_s03 = thumbnail_pic_s03;
        }
    }

}
