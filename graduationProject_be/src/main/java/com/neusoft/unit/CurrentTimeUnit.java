package com.neusoft.unit;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lzz
 * 时间获取工具类
 */
@Component
public class CurrentTimeUnit {

    /**
     * 获取当前系统时间
     * @return
     */
    public static String getCurrentTime(){
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = df.format(date);

        return currentTime;
    }
}
