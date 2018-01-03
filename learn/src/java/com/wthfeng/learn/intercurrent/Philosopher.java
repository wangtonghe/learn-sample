package com.wthfeng.learn.intercurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 哲学家就餐问题
 * 使用ReentrantLock解决
 *
 * @author wangtonghe
 * @date 2017/10/1 17:53
 */
public class Philosopher implements Runnable {


    private boolean isEating;

    private Philosopher left;

    private Philosopher right;

    private Lock table;

    private Condition condition;

    public Philosopher(Philosopher left, Philosopher right, Lock lock) {
        this.left = left;
        this.right = right;
        this.table = lock;
        this.condition = lock.newCondition();
    }


    @Override
    public void run() {
        while (true) {
            think();
            eat();
        }

    }

    private void eat() {
        table.lock();
        try {
            if (left.isEating || right.isEating) {
                this.condition.await();
            }
            isEating = true;
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private void think() {
        table.lock();
        try {
            isEating = false;
            left.condition.signal();
            right.condition.signal();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            table.unlock();
        }


    }
}
