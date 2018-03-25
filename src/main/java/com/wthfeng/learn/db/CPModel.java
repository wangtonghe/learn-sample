package com.wthfeng.learn.db;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangtonghe
 * @date 2018/1/2 09:21
 */
public class CPModel {

    static final Lock lock = new ReentrantLock();

    static final Condition condition = lock.newCondition();

    static private int cap = 200;

//    static final MyLinkedBlockingQueue<Task> queue = new MyLinkedBlockingQueue<>(cap);

    static final Queue<Task> queue = new LinkedList<>();

    static final AtomicInteger number = new AtomicInteger(0);

    static int total = 0;

    static volatile boolean flag = true;


    static int threadNum = 20;


    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100; i++) {
            Task task = new Task(i);
            queue.add(task);
            number.getAndIncrement();
        }

        for (int i = 0; i < threadNum; i++) {
            new Thread(new Produce(), "produce-" + i).start();
            new Thread(new Consume(), "consume-" + i).start();
        }
        Thread.sleep(60 * 1000);
        shundown();
        System.out.println("共生产消费任务数：" + total);
    }

    // 生产者线程
    static class Produce implements Runnable {

        @Override
        public void run() {
            lock.lock();  //加锁
            try {
                while (queue.size() == cap) {  //若达到边界值则等待
                    condition.await();
                }
                Task task = new Task(number.incrementAndGet());  //生产任务
                queue.add(task);
                condition.signal();  //通知消费者已生产
                System.out.println("生产任务数：" + (number.get() - 100));
                total++;

            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                lock.unlock();      //解锁
            }
            try {
                TimeUnit.MILLISECONDS.sleep(500);  //模拟生产流程，等待200毫秒生产一个
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //消费者线程
    static class Consume implements Runnable {

        @Override
        public void run() {
            lock.lock();  //加锁
            try {
                while (queue.size() == 0) {  //若任务队列为空则等待
                    condition.await();
                }
                Task task = queue.poll();   //取出任务消费
                System.out.println("模拟消费：" + task.no);
                condition.signal();  //通知生产者已消费
                total++;
            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                lock.unlock();
            }
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Task {
        int no;

        Task(int no) {
            this.no = no;
        }
    }

    static void shundown() {
        flag = false;
    }
}
