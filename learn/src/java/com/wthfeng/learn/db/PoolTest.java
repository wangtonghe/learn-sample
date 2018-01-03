package com.wthfeng.learn.db;

import java.sql.Connection;
import java.sql.Statement;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangtonghe
 * @date 2017/12/20 08:59
 */
public class PoolTest {

    static ConnectionPool pool = new ConnectionPool(20);


    static int threadNum = 5;

    static CountDownLatch end = new CountDownLatch(threadNum);

    public static void main(String[] args) {
        int count = 20;

        AtomicInteger got = new AtomicInteger(0);
        AtomicInteger notGot = new AtomicInteger(0);
        long start = System.currentTimeMillis();
        for (int i = 0; i < threadNum; i++) {
            Thread thread = new Thread(new ConnRunner(count, got, notGot));
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread.start();
        }
        try {
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("time:" + (System.currentTimeMillis() - start));
        System.out.println("got:" + got.get());
        System.out.println("notgot:" + notGot.get());


    }


    static class ConnRunner implements Runnable {

        private int count;

        private AtomicInteger got;

        private AtomicInteger notgot;

        public ConnRunner(int count, AtomicInteger got, AtomicInteger notgot) {
            this.count = count;
            this.got = got;
            this.notgot = notgot;
        }

        @Override
        public void run() {
            while (count > 0) {
                try {
                    Connection connection = pool.getConnFromPool(1000);
                    if (connection != null) {
                        got.incrementAndGet();
                        Statement statement = connection.createStatement();
                        Thread.sleep(500);
                        pool.release(connection);
                    } else {
                        notgot.incrementAndGet();
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
}
