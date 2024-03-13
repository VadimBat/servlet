package com.goit;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static java.util.Calendar.HOUR;

public class App {
    public static void main(String[] args) {
        Duration duration = Duration.ofHours(2);
        Date currentDateTimeUTC = Date.from(Instant.now().plus(duration));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        System.out.println(dateFormat.format(currentDateTimeUTC));
        String s = "UTC+3";
        String [] ss = s.split("\\+");
        System.out.println(Integer.parseInt(ss[ss.length - 1]));

    }
}