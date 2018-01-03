package com.wthfeng.learn.intercurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangtonghe
 * @date 2017/10/1 16:18
 */
public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            System.out.println("Hello world! from thread");
        });
        t.start();
        Thread.yield();  //让出CPU
        System.out.println("hello world from main");
        t.join();
    }

    public void test()throws InterruptedException{
        Lock lock = new ReentrantLock();
        lock.lockInterruptibly();
    }

}
