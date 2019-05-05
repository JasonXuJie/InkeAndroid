package com.jason.module_my.model.bean;

import java.util.List;

/**
 * Created by jason on 2018/12/20.
 */

public class DetailsBean {


    /**
     * e_id : 12994
     * title : 美国飞机设计师A.利皮施诞生
     * content :     在122年前的，1894年11月20日 (农十月廿三)，美国飞机设计师A.利皮施诞生。
     * 美国飞机设计师A.利皮施1894年11月20日（距122年）生于德国慕尼黑，1976年卒于美国衣阿华州锡达拉皮兹。早年就读于柏林技术高等学校和海德堡大学。利皮施以研究和发展无尾三角翼飞机而著名，仅1921～1945年间就做过84种非常规布局设计和29种常规布局设计。30年代在德国滑翔研究院(DFS)设计了一系列无尾研究机，最著名的是DFS-194。1939年在梅塞施米特公司工作，是世界上首批实用的火箭飞机Me-163的主要设计者。1943年任奥地利维也纳航空研究院院长，开始进行超音速飞机的基础研究。第二次世界大战后去美国，在康维尔公司参与了XF-92三角翼研究机和第一架实用三角翼战斗机 F-102的研制工作，在气动力设计方面起了主要作用。利皮施曾获李林达尔奖和迪塞尔金质奖。
     * A.利皮施是世界上首批实用的火箭飞机Me-163的主要设计者。[]
     * <p>
     * <p>
     * picNo : 1
     * picUrl : [{"pic_title":"","id":1,"url":"http://images.juheapi.com/history/12994_1.jpg"}]
     */

    private String e_id;
    private String title;
    private String content;
    private String picNo;
    private List<PicUrlBean> picUrl;

    public String getE_id() {
        return e_id;
    }

    public void setE_id(String e_id) {
        this.e_id = e_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicNo() {
        return picNo;
    }

    public void setPicNo(String picNo) {
        this.picNo = picNo;
    }

    public List<PicUrlBean> getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(List<PicUrlBean> picUrl) {
        this.picUrl = picUrl;
    }

    public static class PicUrlBean {
        /**
         * pic_title :
         * id : 1
         * url : http://images.juheapi.com/history/12994_1.jpg
         */

        private String pic_title;
        private int id;
        private String url;

        public String getPic_title() {
            return pic_title;
        }

        public void setPic_title(String pic_title) {
            this.pic_title = pic_title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

}
