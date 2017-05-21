package com.wthfeng.learn.thread;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangtonghe
 * @date 2017/5/18 22:16
 */
public class LockTest {

    private volatile int flag;

    Lock lock = new ReentrantLock();

    @Test
    public void test() {
        diffLock(0);
//        diffLock(1);

    }


    public void diffLock(int type) {

        long start = System.currentTimeMillis();
        CountDownLatch inner = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    addUseSync();
                    if (type == 0) {
                        addUseSync();
                    } else {
                        addUseLock();
                    }
                }
                inner.countDown();
            }).start();
        }
        try {
            inner.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(flag);
        System.out.println(System.currentTimeMillis() - start);

    }

    private synchronized void addUseSync() {
        flag++;
    }

    private void addUseLock() {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            flag++;
        } finally {
            lock.unlock();
        }

    }


    public void test2(){
        Lock lock = new ReentrantLock();

        Lock lock2 = new ReentrantLock(true);
    }
}
