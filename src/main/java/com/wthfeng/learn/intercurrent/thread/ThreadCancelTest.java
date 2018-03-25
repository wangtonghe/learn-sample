package com.wthfeng.learn.intercurrent.thread;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程被取消测试
 *
 * @author wangtonghe
 * @date 2017/5/22 09:22
 */
public class ThreadCancelTest {


    @Test
    public void test() {
        Lock lock = new ReentrantLock();
        CountDownLatch countDownLatch = new CountDownLatch(2);

        Thread a1 = new Thread(() -> {
            try {
                lock.lock();
                TimeUnit.MILLISECONDS.sleep(50);
                for (int i = 0; i < 10000; i++) {
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            countDownLatch.countDown();

        }, "Thread1");

        Thread a2 = new Thread(() -> {
            try {
                lock.lock();
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("线程已中断");

                    return;
                }
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } finally {
                lock.unlock();
                countDownLatch.countDown();

            }

        }, "Thread2");

        a1.start();
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        a2.start();
        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            countDownLatch.await();
            System.out.println("执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
