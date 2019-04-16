package com.wthfeng.learn.lang;


import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author wangtonghe
 * @date 2017/12/23 22:49
 */
public class StringTest {


    @Test
    public void test1() {
        String str = "";
        int index = str.indexOf(" ");
        String first = "";
        String second = "";
        if (index > -1) {
            first = str.substring(0, index);
            second = str.substring(index + 1);
        } else {
            first = str;
        }
        System.out.println(first);
        System.out.println(second);
        String[] arr = new String[1];
        arr[0] = second;
        test2("wthfeng", arr);
    }


    public void test2(String name, String... email) {
        System.out.println(name);
        if (email.length > 0) {
            System.out.println(email[0]);
        }
    }

    @Test
    public void test3() {
        String str = "3.1";
        int num = (int) Double.parseDouble(str);
        System.out.println(num);

    }

    @Test
    public void test4() {
        String str = "12.30000";


        String pr = getPositionPrecision(str);
        System.out.println(pr);

    }

    @Test
    public void test5() {
        String abc = "dkkfff";

        String f = "dkkfff";
        System.out.println(abc.equals(f));


    }

    private String getPositionPrecision(String longitude) {
        String precision = "10000";
        if (StringUtils.isEmpty(longitude)) {
            return precision;
        }
        try {
            String suffix = longitude.substring(longitude.indexOf(".") + 1);
            int num = suffix.length();
            if (num == longitude.length()) {
                return precision;
            }
            switch (num) {
                case 1: {
                    precision = "10000";
                    break;
                }
                case 2: {
                    precision = "1000";
                    break;
                }
                case 3: {
                    precision = "100";
                    break;
                }
                case 4: {
                    precision = "10";
                    break;
                }
                default: {
                    precision = "1";
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return precision;
    }

    @Test
    public void test() {
        int n = 0;
        int a = 1;
        int b = 3;
        if (n == 0) {
            System.out.println(n);
        } else if (a == 1) {
            System.out.println(a);
        } else if (b == 3) {
            System.out.println(b);
        }
    }

    public void testList() {
        List<String> list = null;
        System.out.println(list);


    }


    @Test
    public void testMath() {

        System.out.println((int) Math.abs(-7.6));
        System.out.println((int) Math.abs(7.6));

    }

    @Test
    public void testI() {

        int a = 10 >> 1;
        int b = a++;
        int c = ++a;
        int d = b * a++;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);

    }


}
