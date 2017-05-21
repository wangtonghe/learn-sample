package com.wthfeng.learn.thread;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangtonghe
 * @date 2017/5/18 23:30
 */
public class ConditionTest {


    @Test
    public void test() {

        Lock lock = new ReentrantLock();

        Condition condition = lock.newCondition();

        CountDownLatch countDownLatch = new CountDownLatch(2);

        /**
         * 线程A遍历到20后自我阻塞，等待线程B唤醒
         */
        new Thread(() -> {
            try {
                lock.lock();
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + ":" + i);
                    if (i == 20) {
                        condition.await();
                    }
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

            countDownLatch.countDown();

        }, "ThreadA").start();

        /**
         * 线程B休眠2秒后唤醒A
         */
        new Thread(() -> {
            try {
                lock.lock();
                TimeUnit.SECONDS.sleep(2);
                System.out.println("唤醒线程");
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                lock.unlock();
            }
            countDownLatch.countDown();

        },"ThreadB").start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
