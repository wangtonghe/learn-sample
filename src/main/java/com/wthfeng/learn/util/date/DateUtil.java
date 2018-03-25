package com.wthfeng.learn.util.date;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author wangtonghe
 * @date 2017/10/27 17:06
 */
public class DateUtil {

    /**
     * 今天距某一指定日期的相距天数
     *
     * @param date 指定日期
     * @return long ,为正表示指定日期在未来，为负表示在过去
     */
    public static long diffDaysFromToday(LocalDate date) {
        return diffDays(LocalDate.now(), date);
    }

    public static long diffDays(LocalDate from, LocalDate end) {
        return ChronoUnit.DAYS.between(from, end);
    }

    public static LocalDate addDays(LocalDate date, int days) {
        return date.plusDays(days);
    }


    public static void main(String[] args) throws Exception {


        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String date1Str = simpleDateFormat.format(now);
        System.out.println(date1Str);
        Date date1 = simpleDateFormat.parse(date1Str);
        System.out.println(date1);

        LocalDate localDate = LocalDate.of(2017, 1, 21);
        String date2Str = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(date2Str);
        Date date2 = simpleDateFormat.parse(date2Str);
        System.out.println(date2);
        System.out.println(date1.getTime() / 1000 / 3600 / 24);
        System.out.println(date2.getTime() / 1000 / 3600 / 24);

        int days = (int) ((date1.getTime() - date2.getTime()) / (1000 * 3600 * 24));
        System.out.println(days);

//      long days =   DateUtil.diffDaysFromToday(LocalDate.of(2018,4,20));
//        System.out.println(days);
//        Temporal temporal = new Date().toInstant();
        LocalDate localDate1 = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()).toLocalDate();


        long diff = ChronoUnit.DAYS.between(localDate1, localDate);
        System.out.println(diff);


    }

    @Test
    public void test() {
        Instant instant = Instant.now();
        System.out.println(instant.getNano());
        System.out.println(System.currentTimeMillis());

    }


}
