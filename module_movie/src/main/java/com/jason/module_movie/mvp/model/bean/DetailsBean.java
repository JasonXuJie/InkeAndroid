package com.jason.module_movie.mvp.model.bean;

import java.util.List;

/**
 * Created by jason on 2018/12/14.
 */

public class DetailsBean {


    /**
     * rating : {"max":10,"average":0,"stars":"00","min":0}
     * reviews_count : 0
     * wish_count : 449
     * douban_site :
     * year : 2017
     * images : {"small":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2541098388.jpg","large":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2541098388.jpg","medium":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2541098388.jpg"}
     * alt : https://movie.douban.com/subject/30387535/
     * id : 30387535
     * mobile_url : https://movie.douban.com/subject/30387535/mobile
     * title : 最萌警探
     * do_count : null
     * share_url : https://m.douban.com/movie/subject/30387535
     * seasons_count : null
     * schedule_url : https://movie.douban.com/subject/30387535/cinema/
     * episodes_count : null
     * countries : ["俄罗斯"]
     * genres : ["喜剧","动作","冒险"]
     * collect_count : 10
     * casts : [{"alt":"https://movie.douban.com/celebrity/1101813/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1406299075.37.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1406299075.37.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1406299075.37.webp"},"name":"谢尔盖·加尔马什","id":"1101813"},{"alt":"https://movie.douban.com/celebrity/1405412/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1544522001.24.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1544522001.24.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1544522001.24.webp"},"name":"安德烈·纳兹莫夫 ","id":"1405412"},{"alt":"https://movie.douban.com/celebrity/1192229/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p20506.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p20506.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p20506.webp"},"name":"莉莎·阿尔扎马索娃","id":"1192229"},{"alt":"https://movie.douban.com/celebrity/1394564/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1544522048.49.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1544522048.49.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1544522048.49.webp"},"name":"杨歌","id":"1394564"}]
     * current_season : null
     * original_title : Напарник
     * summary : 头发花白的特种作战少校赫洛莫在一次缉毒行动中负伤，在救治过程中，居然阴差阳错地与环境保护科警察奥列格新出生的婴儿实现了灵魂转移。案件迫在眉睫，新生儿幼小的身体里装着刑警少校的老灵魂，当蠢蛋老爹遭遇暴走婴儿，警匪对峙过程中上演了一幕幕啼笑皆非的破案囧事。
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1405411/","avatars":{"small":"https://img1.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"https://img3.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"https://img1.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"亚历山大·安德里尤先科","id":"1405411"}]
     * comments_count : 2
     * ratings_count : 9
     * aka : ["Naparnik","Partner"]
     */

    private RatingBean rating;
    private int reviews_count;
    private int wish_count;
    private String douban_site;
    private String year;
    private ImagesBean images;
    private String alt;
    private String id;
    private String mobile_url;
    private String title;
    private Object do_count;
    private String share_url;
    private Object seasons_count;
    private String schedule_url;
    private Object episodes_count;
    private int collect_count;
    private Object current_season;
    private String original_title;
    private String summary;
    private String subtype;
    private int comments_count;
    private int ratings_count;
    private List<String> countries;
    private List<String> genres;
    private List<CastsBean> casts;
    private List<DirectorsBean> directors;
    private List<String> aka;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public String getDouban_site() {
        return douban_site;
    }

    public void setDouban_site(String douban_site) {
        this.douban_site = douban_site;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getDo_count() {
        return do_count;
    }

    public void setDo_count(Object do_count) {
        this.do_count = do_count;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public Object getSeasons_count() {
        return seasons_count;
    }

    public void setSeasons_count(Object seasons_count) {
        this.seasons_count = seasons_count;
    }

    public String getSchedule_url() {
        return schedule_url;
    }

    public void setSchedule_url(String schedule_url) {
        this.schedule_url = schedule_url;
    }

    public Object getEpisodes_count() {
        return episodes_count;
    }

    public void setEpisodes_count(Object episodes_count) {
        this.episodes_count = episodes_count;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public Object getCurrent_season() {
        return current_season;
    }

    public void setCurrent_season(Object current_season) {
        this.current_season = current_season;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<CastsBean> getCasts() {
        return casts;
    }

    public void setCasts(List<CastsBean> casts) {
        this.casts = casts;
    }

    public List<DirectorsBean> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorsBean> directors) {
        this.directors = directors;
    }

    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
    }

    public static class RatingBean {
        /**
         * max : 10
         * average : 0
         * stars : 00
         * min : 0
         */

        private int max;
        private double average;
        private String stars;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public double getAverage() {
            return average;
        }

        public void setAverage(int average) {
            this.average = average;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class ImagesBean {
        /**
         * small : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2541098388.jpg
         * large : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2541098388.jpg
         * medium : https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2541098388.jpg
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public static class CastsBean {
        /**
         * alt : https://movie.douban.com/celebrity/1101813/
         * avatars : {"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1406299075.37.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1406299075.37.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1406299075.37.webp"}
         * name : 谢尔盖·加尔马什
         * id : 1101813
         */

        private String alt;
        private AvatarsBean avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBean getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBean avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBean {
            /**
             * small : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1406299075.37.webp
             * large : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1406299075.37.webp
             * medium : https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1406299075.37.webp
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }

    public static class DirectorsBean {
        /**
         * alt : https://movie.douban.com/celebrity/1405411/
         * avatars : {"small":"https://img1.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"https://img3.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"https://img1.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"}
         * name : 亚历山大·安德里尤先科
         * id : 1405411
         */

        private String alt;
        private AvatarsBeanX avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBeanX getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBeanX avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBeanX {
            /**
             * small : https://img1.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png
             * large : https://img3.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png
             * medium : https://img1.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }
}
