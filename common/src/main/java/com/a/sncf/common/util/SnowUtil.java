package com.a.sncf.common.util;

import cn.hutool.core.util.IdUtil;

public class SnowUtil {
    private static long datacenterId = 1L; // 数据中心ID
    private static long workerId = 1L; // 机器ID

    public static long getSnowflakeNextId() {
        return IdUtil.getSnowflake(workerId, datacenterId).nextId();
    }

    public static String getSnowflakeNextIdStr() {
        return String.valueOf(getSnowflakeNextId());
    }
}
