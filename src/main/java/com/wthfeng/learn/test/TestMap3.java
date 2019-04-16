package com.wthfeng.learn.test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangtonghe
 * @since 2019/4/2 12:40
 */
public class TestMap3 {

    @Test
    public void testThread() {

        final Map<Integer, Integer> map = new HashMap<>();

        Runnable getRunnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    map.get(i);
                }
            }
        };


        Runnable putRunnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    map.put(i, i);
                }

            }
        };


        Thread t1 = new Thread(putRunnable);
        Thread t2 = new Thread(putRunnable);
        Thread t3 = new Thread(putRunnable);
        Thread t4 = new Thread(putRunnable);
        Thread t5 = new Thread(putRunnable);

        Thread t6 = new Thread(getRunnable);
        Thread t7 = new Thread(getRunnable);
        Thread t8 = new Thread(getRunnable);
        Thread t9 = new Thread(getRunnable);
        Thread t10 = new Thread(getRunnable);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {

        }


    }


}
