package org.easyarch.myutils.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Description :
 * Created by xingtianyu on 16-12-8
 * 下午4:27
 */

public class TimeUtil {

    private static final String TIMESTAMP = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE = "yyyy-MM-dd";

    private static SimpleDateFormat timeFormat = new SimpleDateFormat(TIMESTAMP);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat(DATE);

    public static String parse(Date date,String format){
        SimpleDateFormat f = new SimpleDateFormat(format);
        return f.format(date);
    }

    public static Date parse(String date, String format){
        SimpleDateFormat f = new SimpleDateFormat(format);
        try {
            return f.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getTimeFormatString(Date date){
        return timeFormat.format(date);
    }

    public static String getDateFormatString(Date date){
        return dateFormat.format(date);
    }

    public static Date now() {
        return new Date();
    }

    public static String getNowTime() {
        return timeFormat.format(new Date());
    }

    public static String getNowDate() {
        return dateFormat.format(new Date());
    }

    /**
     * 根据偏移量获取Date对象
     *
     * @param offset
     * @return
     */
    public static Date getDateByNow(int offset) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + offset);
        return c.getTime();
    }

    /**
     * 获取昨天
     *
     * @return
     */
    public static Date yesterday() {
        return getDateByNow(-1);
    }

    /**
     * 获取明天
     *
     * @return
     */
    public static Date tomorrow() {
        return getDateByNow(1);
    }

    public static Date plus(long time) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int millisecond = c.get(Calendar.MILLISECOND);
        c.set(Calendar.MILLISECOND, (int) time + millisecond);
        return c.getTime();
    }

    public static Date plusSeconds(int second) {
        return plus(second * 1000);
    }

    public static Date plusHours(int hours) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int h = c.get(Calendar.HOUR_OF_DAY);
        c.set(Calendar.HOUR_OF_DAY, h + hours);
        return c.getTime();
    }

    public static Date plusDay(int day){
        return plusHours(24);
    }

    public static Date plusWeek(int week){
        return plusDay(week * 7);
    }

    public static Date plusMonth(int month){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int m = c.get(Calendar.MONTH);
        c.set(Calendar.MONTH, m + month);
        return c.getTime();
    }

    public static Date plusYear(int year){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int y = c.get(Calendar.YEAR);
        c.set(Calendar.YEAR, y + year);
        return c.getTime();
    }

    public static long milliseconds(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.MILLISECOND);
    }

    public static int seconds(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.SECOND);
    }

    public static int minutes(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.MINUTE);
    }

    public static int hours(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.HOUR);
    }

    public static int dayOfYear(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.DAY_OF_YEAR);
    }

    public static int dayOfWeek(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.DAY_OF_WEEK);
    }

    public static int dayOfMonth(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static int month(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.MONTH);
    }

    public static int year(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.YEAR);
    }

}
