package com.wthfeng.learn.db;

import java.util.LinkedList;
import java.util.Queue;
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


    static final Queue<Task> queue = new LinkedList<>();

    static final AtomicInteger number = new AtomicInteger(0);

    static int total =0;

    static volatile boolean flag = true;


    static private int cap = 200;


    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100; i++) {
            Task task = new Task(i);
            queue.add(task);
            number.getAndIncrement();
        }

        Thread produce = new Thread(new Produce());

        Thread consume = new Thread(new Consume());

        consume.start();
        produce.start();
        Thread.sleep(30 * 1000);
        shundown();
        System.out.println("共生产消费任务数："+total);
    }

    // 生产者线程
    static class Produce implements Runnable {

        @Override
        public void run() {
            while (flag) {  //控制生产者停止标识，用于统计效率
                lock.lock();  //加锁
                try {
                    while (queue.size() == cap) {  //若大于边界值则等待
                        condition.await();
                    }
                    Task task = new Task(number.incrementAndGet());  //生产任务
                    queue.offer(task);
                    condition.signal();  //通知消费者已生产
                    System.out.println("生产任务数：" + (number.get()-100));
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
    }

    //消费者线程
    static class Consume implements Runnable {

        @Override
        public void run() {
            while (flag) {
                lock.lock();  //加锁
                try {
                    while (queue.size() == 0) {  //若任务队列为空则等待
                        condition.await();
                    }
                    Task task = queue.poll();   //取出任务消费
                    System.out.println("消费任务数：" + task.no);
                    condition.signal();  //通知生产者已取出
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
