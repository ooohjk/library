package com.example.library.global.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilTest {

    @Test
    void 날짜더하기(){
        String plusDate= DateUtil.getDateAfter7Days("20230320");
        Assertions.assertEquals("20230327",plusDate);
    }

}