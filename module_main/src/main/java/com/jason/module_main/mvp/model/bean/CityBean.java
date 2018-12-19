package com.jason.module_main.mvp.model.bean;

import java.util.List;

/**
 * Created by jason on 2018/12/18.
 */

public class CityBean {


    /**
     * count : 20
     * start : 0
     * total : 1366
     * locs : [{"parent":"china","habitable":"yes","id":"108288","name":"北京","uid":"beijing"},{"parent":"china","habitable":"yes","id":"108296","name":"上海","uid":"shanghai"},{"parent":"guangdong","habitable":"yes","id":"118281","name":"广州","uid":"guangzhou"},{"parent":"guangdong","habitable":"yes","id":"118282","name":"深圳","uid":"shenzhen"},{"parent":"sichuan","habitable":"yes","id":"118318","name":"成都","uid":"chengdu"},{"parent":"hubei","habitable":"yes","id":"118254","name":"武汉","uid":"wuhan"},{"parent":"zhejiang","habitable":"yes","id":"118172","name":"杭州","uid":"hangzhou"},{"parent":"china","habitable":"yes","id":"108309","name":"重庆","uid":"chongqing"},{"parent":"henan","habitable":"yes","id":"118237","name":"郑州","uid":"zhengzhou"},{"parent":"jiangsu","habitable":"yes","id":"118159","name":"南京","uid":"nanjing"},{"parent":"shaanxi","habitable":"yes","id":"118371","name":"西安","uid":"xian"},{"parent":"jiangsu","habitable":"yes","id":"118163","name":"苏州","uid":"suzhou"},{"parent":"hunan","habitable":"yes","id":"118267","name":"长沙","uid":"changsha"},{"parent":"china","habitable":"yes","id":"108289","name":"天津","uid":"tianjin"},{"parent":"fujian","habitable":"yes","id":"118200","name":"福州","uid":"fuzhou"},{"parent":"shandong","habitable":"yes","id":"118220","name":"济南","uid":"jinan"},{"parent":"liaoning","habitable":"yes","id":"118123","name":"沈阳","uid":"shenyang"},{"parent":"anhui","habitable":"yes","id":"118183","name":"合肥","uid":"hefei"},{"parent":"shandong","habitable":"yes","id":"118221","name":"青岛","uid":"qingdao"},{"parent":"heilongjiang","habitable":"yes","id":"118146","name":"哈尔滨","uid":"haerbin"}]
     */

    private int count;
    private int start;
    private int total;
    private List<City> locs;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<City> getLocs() {
        return locs;
    }

    public void setLocs(List<City> locs) {
        this.locs = locs;
    }

    public static class City {
        /**
         * parent : china
         * habitable : yes
         * id : 108288
         * name : 北京
         * uid : beijing
         */

        private String parent;
        private String habitable;
        private String id;
        private String name;
        private String uid;

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }

        public String getHabitable() {
            return habitable;
        }

        public void setHabitable(String habitable) {
            this.habitable = habitable;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }
}
