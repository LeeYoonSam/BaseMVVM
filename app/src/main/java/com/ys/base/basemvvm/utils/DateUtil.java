package com.ys.base.basemvvm.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {

    public static String getCurrentDay() {
        // 현재 시간을 msec으로 구한다.
        long now = System.currentTimeMillis();

        // 현재 시간을 저장 한다.
        java.sql.Date date = new java.sql.Date(now);

        // 시간 포맷 지정
        SimpleDateFormat CurDateFormat = new SimpleDateFormat("yyyyMMdd");
        String strCurTime = CurDateFormat.format(date);

        return strCurTime;
    }

    public static String getCurrentSimpleDay() {
        // 현재 시간을 msec으로 구한다.
        long now = System.currentTimeMillis();

        // 현재 시간을 저장 한다.
        Date date = new Date(now);

        // 시간 포맷 지정
        SimpleDateFormat CurDateFormat = new SimpleDateFormat("MMdd");
        String strCurTime = CurDateFormat.format(date);

        return strCurTime;
    }


    public static int getCurrentHour() {
        // 현재 시간을 msec으로 구한다.
        long now = System.currentTimeMillis();

        // 현재 시간을 저장 한다.
        Date date = new Date(now);

        // 시간 포맷 지정
        SimpleDateFormat CurDateFormat = new SimpleDateFormat("HH");
        int strCurTime = Integer.parseInt(CurDateFormat.format(date));

        return strCurTime;
    }

    /**
     * @param Date1
     * @param Date2
     * @return PRE : day1 > day2  | PRE : day1 < day2  | EQUAL : day1 = day2
     */
    public static String getDateCompare(String Date1, String Date2) {
        String val = "PRE";
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        java.util.Date day1 = null;
        java.util.Date day2 = null;
        try {
            day1 = format.parse(Date1);
            day2 = format.parse(Date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int compare = day1.compareTo(day2);
        if (compare > 0) {
            val = "POST";
        } else if (compare < 0) {
            val = "PRE";
        } else {
            val = "EQUAL";
        }
        return val;
    }


    public static String getCurrentDate() {
//        return getCurrentDate("yyyy-MM-dd HH:mm:ss");
        return String.valueOf(System.currentTimeMillis());
    }




    public static String getActivityBoardCurrentDate(String data) {


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date day = null;
        try {
            if(data.contains(":")){
                day = format.parse(data);
            }else{
                day = new Date(Long.parseLong(data));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 시간 포맷 지정
        SimpleDateFormat CurDateFormat = new SimpleDateFormat("MMM.dd HH:mm a");
        String strCurTime = CurDateFormat.format(day);

        return strCurTime;
    }



    public static String getCurrentDate(String format) {
        // 현재 시간을 msec으로 구한다.
        long now = System.currentTimeMillis();

        // 현재 시간을 저장 한다.
        Date date = new Date(now);

        // 시간 포맷 지정
        SimpleDateFormat CurDateFormat = new SimpleDateFormat(
                format);
        String strCurTime = CurDateFormat.format(date);

        return strCurTime;
    }
}
