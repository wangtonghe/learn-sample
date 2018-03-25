package com.wthfeng.learn.intercurrent.thread;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangtonghe
 * @date 2017/5/19 10:05
 */
public class ReentrantLockTest {

    Lock lock = new ReentrantLock();

    private volatile int share;


    @Test
    public void test(){

        for (int i = 0; i < 100 ; i++) {
            lock.lock();
            try {
                share++;
            } finally {
                lock.unlock();
            }
        }
    }
}
