package org.easyarch.myutils.test.format;

import org.easyarch.myutils.format.TimeUtils;
import org.junit.Test;

import java.util.Date;

/**
 * Description :
 * Created by xingtianyu on 16-12-29
 * 下午11:49
 * description:
 */

public class TestTime {

    @Test
    public void test(){
        System.out.println("getNowDate:"+TimeUtils.getNowDate());
        System.out.println("getNowTime:"+TimeUtils.getNowTime());
        System.out.println("dayOfYear:"+TimeUtils.dayOfYear());
        System.out.println("dayOfMonth:"+TimeUtils.dayOfMonth());
        System.out.println("dayOfWeek:"+TimeUtils.dayOfWeek());
        System.out.println("getDateFormatString:"+TimeUtils.getDateFormatString(new Date()));
        System.out.println("getDateOffsetByNow:"+TimeUtils.getDateOffsetByNow(1));
        System.out.println("getDateTimeOffsetByNow:"+TimeUtils.getDateTimeOffsetByNow(1,23,59,59));
        System.out.println("year:"+TimeUtils.year());
        System.out.println("month:"+TimeUtils.month());
        System.out.println("week:"+TimeUtils.week());
        System.out.println("hours:"+TimeUtils.hours());
        System.out.println("minutes:"+TimeUtils.minutes());
        System.out.println("seconds:"+TimeUtils.seconds());
        System.out.println("milliseconds:"+TimeUtils.milliseconds());
        System.out.println("plus:"+TimeUtils.plus(1000*60*60*24));
        System.out.println("plusSeconds:"+TimeUtils.plusSeconds(3600*24));
        System.out.println("plusMinutes:"+TimeUtils.plusMinutes(60*24));
        System.out.println("plusHours:"+TimeUtils.plusHours(24));
        System.out.println("plusDay:"+TimeUtils.plusDay(1));
        System.out.println("plusMonth:"+TimeUtils.plusMonth(1));
        System.out.println("plusYear:"+TimeUtils.plusYear(1));

    }
}
