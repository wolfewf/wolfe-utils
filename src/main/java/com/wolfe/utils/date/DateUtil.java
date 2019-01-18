package com.wolfe.utils.date;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author Wolfe
 * @since 1.0.0
 */
public class DateUtil {
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATEFORMAT = "yyyy年MM月dd日";
    public static final String DATETIMEFORMAT = "yyyy年MM月dd日HH时mm分ss秒";
    public static final String HHMMSS = "HH:mm:ss";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    public static final String YEAR = "year";
    public static final String MONTH = "month";
    public static final String DAY = "day";
    public static final String DATATYPE_YEAR = "Y";
    public static final String DATATYPE_MONTH = "M";
    public static final String DATATYPE_DAY = "D";
    public static final String DATATYPE_HOUR = "H";
    public static final String DATATYPE_MINUTE = "m";
    public static final String DATATYPE_SECOND = "S";
    private static final String DATATYPE_MONTH_DAY = "MD";
    private static final String DATATYPE_YEAR_DAY = "YD";

    /**
     * 获取系统当前日期
     *
     * @return 返回当前日期（Fri Jan 18 09:55:07 CST 2019）
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * 获取当前的日期毫秒
     *
     * @return 返回当前的日期毫秒（1547776507789）
     */
    public static long nowTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 获得当前的时间戳
     *
     * @return 返回当前的时间戳（2019-01-18 09:55:07.789）
     */
    public static Timestamp nowTimeStamp() {
        return new Timestamp(nowTimeMillis());
    }

    /**
     * 获取日期时间的年、月、日、当月的总天数、当年的总天数
     *
     * @param type 类型：Y-年，M-月，D-日，MD-当月的总天数，YD-当年的总天数 不填或null返回日期时间的年份
     * @param date
     * @return
     */
    public static int getAppointDateInfo(String type, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (DATATYPE_MONTH.equals(type)) {
            //获取日期时间的月份
            return calendar.get(Calendar.MONTH) + 1;
        } else if (DATATYPE_DAY.equals(type)) {
            //获取日期时间的第几天
            return calendar.get(Calendar.DATE);
        } else if (DATATYPE_MONTH_DAY.equals(type)) {
            //获取日期时间当月的总天数
            return calendar.getActualMaximum(Calendar.DATE);
        } else if (DATATYPE_YEAR_DAY.equals(type)) {
            //获取日期时间当年的总天数
            return calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
        } else {
            //获取日期时间的年份
            return calendar.get(Calendar.YEAR);
        }
    }

    /**
     * 根据日期格式获取当前日期
     *
     * @param dateFormat 日期格式
     * @return 返回当前日期
     */
    public static String currentDate(String dateFormat) {
        try {
            SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
            return sf.format(getCurrentDate());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 把date 转换成String类型
     *
     * @param date       日期
     * @param dateFormat 日期格式
     * @return 格式化成特定格式的字符串类型日期
     */
    public static String convertDate(Date date, String dateFormat) {
        if (date != null) {
            try {
                SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
                return sf.format(date);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /**
     * 将string类型转换成date类型（dateTimeStr的日期格式必须与dateFormat一致）
     *
     * @param dateStr
     * @param dateFormat
     * @return
     */
    public static Date getDate(String dateStr, String dateFormat) {
        if (dateStr == null || "".equals(dateStr)) {
            return null;
        }
        try {
            DateFormat sdf = new SimpleDateFormat(dateFormat);
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 转换日期格式，把日期字符串从fromDateFormat格式转换成toDateFormat格式
     *
     * @param dateString     日期
     * @param fromDateFormat 原来的日期格式
     * @param toDateFormat   转换的日期格式
     * @return 格式化成特定格式的字符串类型日期
     */
    public static String convertDate(String dateString, String fromDateFormat, String toDateFormat) {
        try {
            SimpleDateFormat sf = new SimpleDateFormat(fromDateFormat);
            Date date = sf.parse(dateString);
            SimpleDateFormat sf1 = new SimpleDateFormat(toDateFormat);
            return sf1.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 把时间戳转化成指定格式的时间
     *
     * @param timestamp    时间戳
     * @param toDateFormat 转化成的时间格式
     * @return 指定格式的日期字符串
     */
    public static String getTimeFromTimestamp(String timestamp, String toDateFormat) {
        try {
            // 时间戳转化为Sting或Date
            SimpleDateFormat format = new SimpleDateFormat(toDateFormat);
            Long time = new Long(timestamp);
            return format.format(new Date(time * 1000));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 把时间戳转化成时间
     *
     * @param timestamp 时间戳
     * @return 日期
     */
    public static Date getTimeFromTimestamp(String timestamp) {
        try {
            // 时间戳转化为Date
            Long time = new Long(timestamp);
            return new Date(time * 1000);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获得指定的日期
     *
     * @param type       类型：Y-年，M-月，D-日，H-小时，m-分钟，S-秒 不填或null返回当前时间
     * @param date       日期
     * @param count      前后的时间（-2：指的是计算日期前的时间）
     * @param dateFormat 返回的时间格式
     * @return
     */
    public static String getAppointDate(String type, Date date, int count, String dateFormat) {
        Calendar calendar = Calendar.getInstance();
        // 设置时间为当前时间
        calendar.setTime(date);
        if (DATATYPE_YEAR.equals(type)) {
            //年
            calendar.add(Calendar.YEAR, count);
        } else if (DATATYPE_MONTH.equals(type)) {
            //月
            calendar.add(Calendar.MONTH, count);
        } else if (DATATYPE_DAY.equals(type)) {
            //日
            calendar.add(Calendar.DAY_OF_YEAR, count);
        } else if (DATATYPE_HOUR.equals(type)) {
            //小时
            calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + count);
        } else if (DATATYPE_MINUTE.equals(type)) {
            //分钟
            calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + count);
        } else if (DATATYPE_SECOND.equals(type)) {
            //秒
            calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + count);
        }
        Date resultDate = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(resultDate);
    }

    /**
     * 计算两个日期的间隔（yyyy MM dd HH mm ss）
     *
     * @param type  计算间隔的类型：Y-年，M-月，D-日，H-小时，m-分钟，S-秒，不填或null默认为日
     * @param date1
     * @param date2
     * @return 间隔的数量。如果日期sdate2在日期sdate1之后，则结果为正数；如果日期sdate2在日期sdate1之前，则结果为负数
     */
    public static int getDateDiff(String type, Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(date1);
        calendar2.setTime(date2);
        int yearDiff = calendar2.get(Calendar.YEAR) - calendar1.get(Calendar.YEAR);
        if (DATATYPE_YEAR.equals(type)) {
            //年
            return yearDiff;
        } else if (DATATYPE_MONTH.equals(type)) {
            //月
            return yearDiff * 12 + calendar2.get(Calendar.MONTH) - calendar1.get(Calendar.MONTH);
        } else {
            long lDate1 = date1.getTime() + calendar1.get(Calendar.ZONE_OFFSET) + calendar1.get(Calendar.DST_OFFSET);
            long lDate2 = date2.getTime() + calendar2.get(Calendar.ZONE_OFFSET) + calendar2.get(Calendar.DST_OFFSET);
            if (DATATYPE_HOUR.equals(type)) {
                //小时
                return (int) ((lDate2 - lDate1) / (3600000));
            } else if (DATATYPE_MINUTE.equals(type)) {
                //分钟
                return (int) ((lDate2 - lDate1) / (60000));
            } else if (DATATYPE_SECOND.equals(type)) {
                //秒
                return (int) ((lDate2 - lDate1) / (1000));
            } else {
                //日
                return (int) ((lDate2 - lDate1) / (3600000 * 24));
            }
        }
    }

    /**
     * 把日期字符串中的“-”去掉
     *
     * @param date
     * @return
     */
    public static String dayNoSeparate(String date) {
        return date.replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println(getCurrentDate());
        System.out.println(nowTimeMillis());
        System.out.println(nowTimeStamp());
        System.out.println(currentDate(DateUtil.YMDHMS));
        System.out.println(getAppointDateInfo("D", getCurrentDate()));
        System.out.println(convertDate(getCurrentDate(), DateUtil.DATEFORMAT));
        System.out.println(getAppointDate("D", getCurrentDate(), -2, DateUtil.YYYYMMDDHHMMSSSSS));
        System.out.println(getDateDiff("1", getCurrentDate(), getCurrentDate()));
        String str = getAppointDate("D", getCurrentDate(), 0, DateUtil.DATETIMEFORMAT);
        String str1 = getAppointDate("D", getCurrentDate(), 0, DateUtil.DATETIMEFORMAT);
        System.out.println(getDateDiff("D", getDate(str, DateUtil.DATETIMEFORMAT), getDate(str1, DateUtil.DATETIMEFORMAT)));
    }
}
