package cn.itoak.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 * DateUtil
 *
 * <p>
 * Java8之前处理时间用到的基本是Date、Calendar、SimpleDateFormat，
 * 但他们是非线程安全的，在Java8应用中推荐使用：
 * Instant 代替 Date
 * LocalDateTime 代替 Calendar
 * DateTimeFormatter 代替 SimpleDateFormat
 *
 * @author itoak
 */
public class DateUtil {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 根据DateTimeFormatter，获取指定格式日期、时间字符串
     *
     * @param formatter 日期、时间格式
     * @return 日期、时间字符串
     */
    public static String current(DateTimeFormatter formatter) {
        return LocalDateTime.now().format(formatter);
    }

    /**
     * 返回默认格式的当前日期时间字符串
     * 格式：yyyy-MM-dd HH:mm:ss
     *
     * @return 日期时间字符串
     */
    public static String currentDateTime() {
        return current(DATE_TIME_FORMATTER);
    }

    /**
     * 返回模式格式的当前日期字符串
     * 格式：yyyy-MM-dd
     *
     * @return 日期字符串
     */
    public static String currentDate() {
        return current(DATE_FORMATTER);
    }

    /**
     * 返回模式格式的当前时间字符串
     * 格式：HH:mm:ss
     *
     * @return 时间字符串
     */
    public static String currentTime() {
        return current(TIME_FORMATTER);
    }

    /**
     * 根据DateTimeFormatter，格式化日期
     *
     * @param date      待格式化日期
     * @param formatter 格式化
     * @return 给定日期、时间的格式化输出
     */
    public static String format(Date date, DateTimeFormatter formatter) {
        Objects.requireNonNull(date, "date");
        return LocalDateTime
                .ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault())
                .format(formatter);
    }

    /**
     * 返回默认格式的指定日期时间的字符串
     * 格式：yyyy-MM-dd HH:mm:ss
     *
     * @param date 指定日期
     * @return 日期时间字符串
     */
    public static String formatDateTime(Date date) {
        return format(date, DATE_TIME_FORMATTER);
    }

    /**
     * 返回默认格式的指定日期的字符串
     * 格式：yyyy-MM-dd
     *
     * @param date 指定日期
     * @return 日期字符串
     */
    public static String formatDate(Date date) {
        return format(date, DATE_FORMATTER);
    }

    /**
     * 返回默认格式的指定时间的字符串
     * 格式：HH:mm:ss
     *
     * @param date 指定时间
     * @return 时间字符串
     */
    public static String formatTime(Date date) {
        return format(date, TIME_FORMATTER);
    }

    /**
     * 解析日期时间
     *
     * @param date      待解析日期时间字符串
     * @param formatter 格式化
     * @return 日期
     */
    public static Date parseDateTime(String date, DateTimeFormatter formatter) {
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
        Instant instant = localDateTime.toInstant(ZoneOffset.of("+8"));
        return Date.from(instant);
    }

    /**
     * 按默认格式解析日期时间
     * 格式：yyyy-MM-dd HH:mm:ss
     *
     * @param date 待解析日期时间字符串
     * @return 日期
     */
    public static Date parseDateTime(String date) {
        return parseDateTime(date, DATE_TIME_FORMATTER);
    }

    /**
     * 解析日期
     *
     * @param date      待解析日期字符串
     * @param formatter 格式化
     * @return 日期
     */
    public static Date parseDate(String date, DateTimeFormatter formatter) {
        LocalDate localDate = LocalDate.parse(date, formatter);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.MIN);
        Instant instant = localDateTime.toInstant(ZoneOffset.of("+8"));
        return Date.from(instant);
    }

    /**
     * 按默认格式解析日期
     * 格式：yyyy-MM-dd
     *
     * @param date 待解析日期字符串
     * @return 日期
     */
    public static Date parseDate(String date) {
        return parseDate(date, DATE_FORMATTER);
    }


    public static void main(String[] args) {
        // 当前时间字符串
//        System.out.println(current(DATE_TIME_FORMATTER));
//        System.out.println(current(DATE_FORMATTER));
//        System.out.println(current(TIME_FORMATTER));
//        System.out.println(currentDateTime());
//        System.out.println(currentDate());
//        System.out.println(currentTime());

        // 指定时间字符串
//        System.out.println(format(new Date(), DATE_FORMATTER));
//        System.out.println(format(new Date(), TIME_FORMATTER));
//        System.out.println(format(new Date(), DATE_TIME_FORMATTER));
//        System.out.println(formatDate(new Date()));
//        System.out.println(formatTime(new Date()));
//        System.out.println(formatDateTime(new Date()));

        // 解析时间字符串 -> 时间
//        System.out.println(parseDateTime("2019-12-19 23:05:18", DATE_TIME_FORMATTER));
//        System.out.println(parseDate("2019-12-19", DATE_FORMATTER));
//        System.out.println(parseDateTime("2019-12-19 23:05:18"));
//        System.out.println(parseDate("2019-12-19"));


    }
}
