package test.util;

import org.checkerframework.dataflow.qual.TerminatesExecution;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangtonghe
 * @since 2018/11/17 15:33
 */
public class ArrayUtil {


    @Test
    public void testArr() {

        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list.addAll(list2);

        System.out.println(list);


    }

    @Test
    public void testReplace() {
        String str = "http://c.gdt.qq.com/gdt_mclick.fcgviewid=NK0H43_aaqJaIj1wwJrg4kwnH_rQ3hKF8igdEaX9004P8ZbShS8TAsmeXk7MXHyk9hVbTNwS2FVkoIWqXba6_4xw5XO4_VzDTbk3ihpl4CJrLRa9DK5McJYHxRQ2EkJvfePHhoZc4RlxpBGI64Oyjg79LFaWcQl6fjceEN8kNOqe6o2TAM0NvmojJi__2Vyg4uCQdmiMHo&jtype=0&i=1&os=1&acttype=0&s=%7B%22down_x%22%3A-999%2C%22down_y%22%3A-999%2C%22up_x%22%3A-999%2C%22up_y%22%3A-999%7D";
        str = str.replaceAll("acttype=.+&", "acttype=1&");
        System.out.println(str);

    }

}
