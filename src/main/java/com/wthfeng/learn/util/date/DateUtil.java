package com.wthfeng.learn.util.date;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

        LocalDate localDate = LocalDate.of(2018, 2, 12);
        LocalDate now = LocalDate.now();

        long days = Period.between(localDate, now).getDays();
        System.out.println(days);


    }

    @Test
    public void test() {
        Instant instant = Instant.now();
        System.out.println(instant.getNano());
        System.out.println(System.currentTimeMillis());

    }

    @Test
    public void instantTest() throws Exception {
        Instant instant = Instant.now();
        TimeUnit.SECONDS.sleep(2);
        long diff = Duration.between(instant, Instant.now()).toMillis();

        System.out.println(diff);


    }

    @Test
    public void localDateTest() {

        LocalDate now = LocalDate.now();
        LocalDate localDate = now.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(localDate);
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonthValue());
        System.out.println(localDate.getDayOfMonth());

        LocalDate localDate1 = LocalDate.of(2018, 3, 25);
        System.out.println(localDate1.equals(now));

        MonthDay monthDay = MonthDay.of(now.getMonthValue(), now.getDayOfMonth());

        MonthDay today = MonthDay.from(now);

        System.out.println(monthDay.toString());

    }

    @Test
    public void testLocalDate() {
        LocalDate now = LocalDate.now();
        //输出：2018-04-22 ,表示其默认格式为yyyy-MM-dd
        System.out.println(now);

        LocalDate lastDay = LocalDate.of(2018, 4, 21);
        //输出：2018-04-21
        System.out.println(lastDay);

        // 输出：false,不是闰年
        System.out.println(now.isLeapYear());

        // 输出 2018-04-21,格式化字符串
        System.out.println(now.format(DateTimeFormatter.ISO_LOCAL_DATE));

        // 注意，会报错，LocalDate类只有日期没有时间信息，不能格式化
        // System.out.println(now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        // 获取某天的年月日信息
        System.out.println(now.getYear());
        System.out.println(now.getMonthValue());
        System.out.println(now.getDayOfMonth());

        // 比较两日期是否相等
        System.out.println(now.equals(lastDay));

        // 日期加减
        // 明天
        LocalDate tomorrow = now.plusDays(1);
        System.out.println(tomorrow);
        // 一周前
        LocalDate monthDay = now.minusWeeks(1);
        System.out.println(monthDay);
        // 一年后
        LocalDate afterDay = now.plus(1, ChronoUnit.YEARS);
        System.out.println(afterDay);


    }

    @Test
    public void testInstant() throws Exception {

        //获取当前时间戳
        Instant now = Instant.now();
        System.out.println(now);

        Thread.sleep(1000);

        // Date转Instant
        Date today = new Date();
        Instant cur = today.toInstant();

        // 计算两Instant示例差值
        long diff = Duration.between(now, cur).toMillis();
        System.out.println(diff);

        // Instant 转Date
        Date date = Date.from(now);
        System.out.println(date);

    }

    @Test
    public void testTime() {
        //获取当前时间
        LocalTime curTime = LocalTime.now();
        // 输出：10:52:12.108
        System.out.println(curTime);

        // 构造时间
        LocalTime localTime = LocalTime.of(14, 34, 12);
        // 输出：14:34:12
        System.out.println(localTime);

        // 时间加减，1小时后
        LocalTime nextHour = curTime.plus(1, ChronoUnit.HOURS);
        //  11:52:12.108
        System.out.println(nextHour);

        // 两时间差值
        long minuteDiff = Duration.between(curTime, localTime).toMinutes();
        // 输出：221
        System.out.println(minuteDiff);
    }

    @Test
    public void testDateTime() {
        // 获取当前日期时间
        LocalDateTime now = LocalDateTime.now();
        // 输出：2018-05-05T10:58:17.119
        System.out.println(now);
        // 2018
        System.out.println(now.getYear());
        //5
        System.out.println(now.getMonthValue());
        //5
        System.out.println(now.getDayOfMonth());
        // 10
        System.out.println(now.getHour());
        //58
        System.out.println(now.getMinute());
    }

    @Test
    public void formatDate() {
        LocalDateTime now = LocalDateTime.now();

        // 默认输出：2018-05-05T11:11:59.221
        System.out.println(now);
        // iso日期时间格式，输出：2018-05-05T11:11:59.221
        System.out.println(now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        // iso 日期格式，输出：2018-05-05
        System.out.println(now.format(DateTimeFormatter.ISO_LOCAL_DATE));
        // 自定义格式：2018/05/05 11:11:59
        System.out.println(now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));

        // 字符串转日期，默认字符串格式需为ISO_LOCAL_DATE_TIME风格
        LocalDateTime localDateTime = LocalDateTime.parse("2018-05-01T11:00:00");
        System.out.println(localDateTime);

        // 字符串转日期，字符串格式需与模式相匹配
        LocalDateTime localDateTime1 = LocalDateTime.parse("2018/05/05 12:00", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        System.out.println(localDateTime1);
    }

    @Test
    public void dataDiff() throws Exception {

        // 计算距22年冬奥会还有多久
        LocalDate now = LocalDate.now();
        // 输出：当前日期：2018年05月06日
        System.out.println("当前日期：" + now.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")));
        LocalDate olympicDay = LocalDate.of(2022, 2, 4);
        Period period = Period.between(now, olympicDay);
        // 输出：距冬奥会还有:3年8月29天
        System.out.println("距冬奥会还有:" + period.getYears() + "年" + period.getMonths() + "月" + period.getDays() + "天");


        // 计算两个LocalTime相差的毫秒数
        LocalTime start = LocalTime.now();
        TimeUnit.SECONDS.sleep(1);
        Duration duration = Duration.between(start, LocalTime.now());
        // 输出:1004
        System.out.println("1秒后相差毫秒数：" + duration.toMillis());

        System.out.println("1秒后相差毫秒数：" + ChronoUnit.MILLIS.between(start, LocalTime.now()));


        // 距冬奥会天数
        LocalDateTime curTime = LocalDateTime.now();
        LocalDateTime nextTime = LocalDateTime.of(2022, 2, 4, 0, 0);
        System.out.println("距冬奥会相差：" + Duration.between(curTime, nextTime).toDays() + "天");

        // 距冬奥会天数，使用ChronoUnit
        long days = ChronoUnit.DAYS.between(now, olympicDay);
        System.out.println("距冬奥会相差：" + days + "天");

        // 1秒钟后
        long diffOneMinute = ChronoUnit.MILLIS.between(start, LocalTime.now());
        System.out.println("1秒钟后" + diffOneMinute);


    }

    @Test
    public void transDate() {
        // Date转LocalDateTime
        LocalDateTime now = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
        // 输出：2018-05-06T18:29:52.184
        System.out.println(now);
        LocalDate today = now.toLocalDate();
        //输出：2018-05-06
        System.out.println(today);

        // LocalDateTime 转Date
        Date date = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());

        // LocalDate 转Date
        Date date1 = Date.from(today.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        System.out.println(date);
        System.out.println(date1);


    }


}
