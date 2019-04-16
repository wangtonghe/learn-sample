package com.wthfeng.learn.bottom.jvm;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author wangtonghe
 * @since 2018/9/27 14:50
 */
public class TestStack {

    static Object lock = new Object();
    static Object lock2 = new Object();
    static CountDownLatch countDownLatch = new CountDownLatch(2);

    static boolean flag = true;


    @Test
    public void testStack() throws Exception {
        Thread thread1 = new Thread(new Task());
        Thread thread2 = new Thread(new Task2());
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

    }

    static class Task implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "进入lock");
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + "进入lock2");
                }

            }
        }
    }

    static class Task2 implements Runnable {

        @Override
        public void run() {
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + "进入lock2");
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + "进入lock");
                }

            }
        }
    }
}
