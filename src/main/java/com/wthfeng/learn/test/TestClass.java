package com.wthfeng.learn.test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wangtonghe
 * @since 2019/3/28 18:15
 */
public class TestClass {

    public int str2Num(String str) {

        boolean isNegative = false;
        int i = 0;
        int length = str.length();
        int result = 0;

        char c = str.charAt(0);
        if (c == '-') {
            isNegative = true;
            i++;
        }
        while (i < length) {
            char s = str.charAt(i);
            int n = (int) s - 48;
            result += n * Math.pow(10, length - 1 - i);
            i++;
        }
        return isNegative ? -result : result;

    }

    public static Character getFirst(String str) {

        Map<Character, Integer> map = new LinkedHashMap<>();

        int len = str.length();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);

        }
        Character c = null;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }

        }
        return null;


    }

    public static void main(String[] args) {
        TestClass test = new TestClass();

//        int num = test.str2Num("+23499999");
//        System.out.println(num);

        char c = getFirst("ssdddddadc");
        System.out.println((char) c);
    }


}
