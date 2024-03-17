package com.example.library.global.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String getDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(new Date());
        return date;
    }

    public static String getTime(){
        DateFormat timeFormat = new SimpleDateFormat("HHmmss");
        String time = timeFormat.format(new Date());
        return time;
    }
}
