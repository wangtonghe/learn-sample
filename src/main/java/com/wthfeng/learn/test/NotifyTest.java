package com.wthfeng.learn.test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author wangtonghe
 * @since 2019/3/28 22:36
 */
public class NotifyTest {

    private final static Object lock = new Object();

    private final static Queue<Integer> queue = new ArrayDeque<>();

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                new Thread(new Product()).start();
            } else {
                new Thread(new Consumer()).start();
            }

        }

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Product implements Runnable {

        @Override
        public void run() {

            synchronized (lock) {
                queue.add(100);
                System.out.println("p:" + queue);
                lock.notify();
            }

        }
    }

    static class Consumer implements Runnable {

        @Override
        public void run() {

            synchronized (lock) {
                while (queue.isEmpty()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(queue.poll());
                System.out.println("c" + queue);
            }

        }
    }
}
