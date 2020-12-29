package com.channelsoft.date;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类测试
 *
 * @author Ma_wei
 * @since 2020/7/27 15:05
 */
@Slf4j
public class DateUtilsTest {

    @Test
    public void test1(){
        Date configDate = DateUtils.setDays(new Date(), 25);
        log.info(configDate.toString());
        System.out.println(new Date().toLocaleString());
        System.out.println(configDate.toLocaleString());

        // 7天后
        Date sevenDate = DateUtils.addDays(configDate, 7);
        System.out.println(sevenDate.toLocaleString());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        Calendar start = Calendar.getInstance();
        start.setTime(configDate);

        Calendar end = Calendar.getInstance();
        end.setTime(sevenDate);
        Assert.assertTrue(calendar.after(start) && calendar.before(end));
    }
}
