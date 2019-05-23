package com.jason.tools.utils;

import java.util.UUID;

public class UuidUtil {

    /**
     * 随机生成UUID
     * **/
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
