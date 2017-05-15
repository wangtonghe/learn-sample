package com.wthfeng.learn.thread;

/**
 * @author wangtonghe
 * @date 2017/5/11 12:39
 */
public class VolatileTest {

    private static volatile int num=0;

    public static void main(String[] args) {
        for (int i = 0; i <20 ; i++) {
            new Thread(()->{
                for (int j = 0; j < 100; j++) {
                    num++;
                }

            }).start();

        }
    }
}
