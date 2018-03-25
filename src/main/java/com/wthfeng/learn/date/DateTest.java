package com.wthfeng.learn.date;

import org.junit.Test;
import org.springframework.util.StringUtils;

import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * @author wangtonghe
 * @date 2017/10/27 17:06
 */
public class DateTest {

    @Test
    public void test() {
        LocalDate localDate = LocalDate.of(2015, 2, 28);

        System.out.println(localDate);
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonthValue());
        System.out.println(localDate.getDayOfMonth());

        System.out.println(localDate.plus(2, ChronoUnit.DAYS));

        MonthDay monthDay = MonthDay.from(localDate);

        MonthDay pre = MonthDay.of(localDate.getMonth(), localDate.getDayOfMonth());
        if (pre.equals(monthDay)) {
            System.out.println("same");
        }

        LocalTime curTime = LocalTime.now();
        LocalTime after = curTime.plusHours(2);
        System.out.println(after);
    }

    @Test
    public void test2(){
        Clock clock = Clock.systemUTC();
        System.out.println(clock);

    }
    @Test
    public void test3(){
        LocalDate localDate = LocalDate.of(1992,2,24);
        LocalDate today = LocalDate.now();
        Period period = Period.between(localDate,today);
        System.out.println(period);
        System.out.println();

    }

    @Test
    public void test4(){
        String date = "2017-12-28 12:00:00";
        System.out.println(getDateFromDateTime(date));
    }

    private String getDateFromDateTime(String datetime){
        String[] date = null;
        if(StringUtils.isEmpty(datetime)||(date=datetime.split(" ")).length==0){
            return "";
        }
        return date[0];
    }












}
