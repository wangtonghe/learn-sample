package com.wthfeng.learn.intercurrent.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author wangtonghe
 * @since 2019/2/21 14:59
 */
public class InterruptTest {

    static class SleepTask implements Runnable {

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println("发生中断");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new SleepTask());
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t1.interrupt();
        TimeUnit.SECONDS.sleep(5);


    }
}
