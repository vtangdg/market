package com.example.market.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Created by degang on 2018/9/9
 */
public class TimeUtil {

    // return minute unit time
    private Integer minuteTransfer(Integer day, String shortTime) {
        LocalDate date = LocalDate.parse(day.toString(), DateTimeFormatter.BASIC_ISO_DATE);
        LocalTime time = LocalTime.parse(shortTime, DateTimeFormatter.ofPattern("HHmm"));
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        return (int)(dateTime.toEpochSecond(ZoneOffset.of("+8")) / 60);
    }

    public static Integer minuteTransfer(Integer day, String beforeTime, String currentTime) {

        LocalDate date = LocalDate.parse(day.toString(), DateTimeFormatter.BASIC_ISO_DATE);
        // 是否跨天
        date = Integer.valueOf(beforeTime) <= Integer.valueOf(currentTime) ? date : date.plusDays(1L);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        LocalTime time = LocalTime.parse(currentTime, formatter);
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        return (int)(dateTime.toEpochSecond(ZoneOffset.of("+8")) / 60);
    }

    private static int getIntegerLength(Integer i) {
        return i.toString().length();
    }
}
