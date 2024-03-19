package com.example.library.global.utils;

import com.example.library.exception.ErrorCode;
import com.example.library.exception.exceptions.DateParseException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    public static String getDateAfter7Days(String targetDate) {
        Date date ;

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        try{
            date = dateFormat.parse(targetDate);

        }catch (ParseException e){
            throw new DateParseException(ErrorCode.DATE_PARSE_ERROR);
        }

        Calendar cal = Calendar.getInstance(); // 날짜 계산을 위해 Calendar 추상클래스 선언 및 getInstance() 메서드 사용
        cal.setTime(date);

        cal.add(Calendar.DATE,6);

        return dateFormat.format(cal.getTime());

    }
}
