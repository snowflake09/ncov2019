package com.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil {

    /**
     * 格式：年－月－日  时:分钟:秒
     */
    public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * 格式：年－月－日  时:分钟
     */
    public static final String FORMAT_DATE_TIME_MINI = "yyyy-MM-dd HH:mm";
    
    /**
     * 格式：月-日   时:分钟
     */
    public static final String FORMAT_DATE_TIME_MINI_NOYEAR = "MM-dd HH:mm";

    /**
     * 格式：年－月－日
     */
    public static final String FORMAT_DATE = "yyyy-MM-dd";
    
    /**
     * 格式：年－月
     */
    public static final String FORMAT_DATE_MONTH = "yyyy-MM";

    /**
     * 格式：时:分钟
     */
    public static final String FORMAT_TIME_MINI = "HH:mm";
    
    /**
     * 格式：时:分钟:秒
     */
    public static final String FORMAT_TIME = "HH:mm:ss";

    public static Logger log = LoggerFactory.getLogger(DateUtil.class);

    /**
     * 获取当前时间的指定格式
     */
    public static String getCurrentDateStr() {
        return dateToString(Calendar.getInstance().getTime(), FORMAT_DATE_TIME);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static Date getCurrentDate() {
        Calendar c = Calendar.getInstance(Locale.getDefault());

        return c.getTime();
    }

    /**
     * 把日期转换为字符串
     */
    public static String dateToString(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat formater = new SimpleDateFormat(format);
        String dateStr = "";
        try {
            dateStr = formater.format(date);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return dateStr;
    }

    /**
     * 把日期转换为字符串 如果为空返回当前时间
     */
    public static String dateToString(Date date) {
        return dateToString(date, FORMAT_DATE_TIME);
    }

    /**
     * 把日期转换为字符串 如果为空返回当前时间
     */
    public static String dateToShortString(Date date) {
        return dateToString(date, FORMAT_DATE);
    }

    /**
     * 把字符串转换为日期 如果为空返回当前时间
     */
    public static Date stringToDate(String dateStr, String format) {
        if (dateStr == null) {
            return null;
        }
        SimpleDateFormat formater = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = formater.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;

    }

    /**
     *
     * @param dateStr
     * @return
     */
    public static Date stringToDate(String dateStr) {
        return stringToDate(dateStr, FORMAT_DATE_TIME);
    }

    public static int getBetweenDay(Date date1, Date date2) {
        Calendar d1 = new GregorianCalendar();
        d1.setTime(date1);
        Calendar d2 = new GregorianCalendar();
        d2.setTime(date2);
        int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2) {
            do {
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
                d1.add(Calendar.YEAR, 1);
            } while (d1.get(Calendar.YEAR) != y2);
        }
        return days;
    }
    
    /**
     * 获取两个时间相差的天数
     * @param smdate 开始时间
     * @param bdate	结束时间
     * @return
     * @author LiQiang
     * @date 2018年4月14日
     */
    public static int daysBetween(Date smdate,Date bdate)   
    {    
    	java.util.Calendar calst = java.util.Calendar.getInstance();   
        java.util.Calendar caled = java.util.Calendar.getInstance();   
        calst.setTime(smdate);   
         caled.setTime(bdate);   
         //设置时间为0时   
         calst.set(java.util.Calendar.HOUR_OF_DAY, 0);   
         calst.set(java.util.Calendar.MINUTE, 0);   
         calst.set(java.util.Calendar.SECOND, 0);   
         caled.set(java.util.Calendar.HOUR_OF_DAY, 0);   
         caled.set(java.util.Calendar.MINUTE, 0);   
         caled.set(java.util.Calendar.SECOND, 0);   
        //得到两个日期相差的天数   
         int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst   
                .getTime().getTime() / 1000)) / 3600 / 24;   
         
        return days;          
    }

    public static int getBetweenYear(Date date) {
        Calendar d1 = new GregorianCalendar();
        d1.setTime(date);
        Calendar d2 = new GregorianCalendar();
        d2.setTime(new Date());
        int nowYear = d2.get(Calendar.YEAR);
        int dateYear = d1.get(Calendar.YEAR);
        return nowYear - dateYear;
    }
    
    public static void main(String[] args) {
    	System.out.println(daysBetween(DateUtil.stringToDate("2018-04-28", FORMAT_DATE),DateUtil.stringToDate("2018-04-28", FORMAT_DATE)));
	}
}
