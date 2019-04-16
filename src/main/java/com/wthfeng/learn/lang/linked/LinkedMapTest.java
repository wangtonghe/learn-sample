package com.wthfeng.learn.lang.linked;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wangtonghe
 * @since 2019/3/8 14:45
 */
public class LinkedMapTest {


    @Test
    public void testLinkedMap() {

        LinkedHashMap<Integer, String> map = new LinkedHashMap<>(3, 0.75f, true);

        map.put(3, "dd");

        map.put(5, "cc");

        map.put(1, "ff");

        map.put(4, "kk");

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());

        }

        map.get(1);
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());

        }


    }
}
