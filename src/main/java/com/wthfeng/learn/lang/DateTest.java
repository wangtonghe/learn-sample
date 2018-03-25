package com.wthfeng.learn.lang;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @author wangtonghe
 * @date 2018/1/12 17:51
 */
public class DateTest {


    public static void main(String[] args) {
        String date1 = "2018-1-12";
        String date2 = "2018-1-13";
        long day = getCalculateDayOfDay(date2, date1, "yyyy-MM-dd");
        System.out.println(day);
    }

    public static long getCalculateDayOfDay(String d1, String d2, String format) {
        long day = 0;
        if (vaildateDateIs(d1) && vaildateDateIs(d2)) {
            try {
                Date date1 = new SimpleDateFormat(format).parse(d1);
                Date date2 = new SimpleDateFormat(format).parse(d2);
                long ltime = date1.getTime() - date2.getTime() > 0 ? date1.getTime() - date2.getTime() : 0;
                day = ltime / (24 * 60 * 60 * 1000);
            } catch (Exception x) {
                x.printStackTrace();
                System.out.println(x.getMessage());
            }
        }
        return day;
    }

    public static boolean vaildateDateIs(String dateStr) {
        String ruler = "^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$";
        Pattern p = Pattern.compile(ruler);
        System.out.println(dateStr);
        return p.matcher(dateStr).matches();
    }
}
