package org.easyarch.myutils.format;

import java.text.SimpleDateFormat;
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

    public static String getNowTime(){
        return timeFormat.format(new Date());
    }

    public static String getNowDate(){
        return dateFormat.format(new Date());
    }
}
