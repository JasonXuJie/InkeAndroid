package com.jason.module_event.http;

/**
 * Created by jason on 2018/12/18.
 */

public class HttpConfig {

    /**豆瓣同城
     * @param loc 城市id
     * @param day_type  时间类型 future, week, weekend, today, tomorrow
     * @param type   all,music, film, drama, commonweal, salon, exhibition, party, sports, travel, others
     * */
    public static final String DOUBAN_EVENTS_URL = "https://api.douban.com/v2/event/list";
}
