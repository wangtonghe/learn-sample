package com.wthfeng.learn.intercurrent.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author wangtonghe
 * @since 2019/2/22 19:02
 */
public class CountDownTest {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(2);


        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        }, "t2");

        t1.start();
        t2.start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");


    }
}
