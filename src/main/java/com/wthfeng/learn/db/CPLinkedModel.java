package com.wthfeng.learn.db;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangtonghe
 * @date 2018/1/5 08:28
 */
public class CPLinkedModel {

    private Lock consume = new ReentrantLock();

    private Condition consumeCondition = consume.newCondition();

    private Lock produce = new ReentrantLock();

    private Condition produceCondition = produce.newCondition();



    private AtomicInteger count = new AtomicInteger(0);

    public CPLinkedModel() {
        this.head = head;
        this.tail = tail;

    }

    private Node head;

    private Node tail;

    class Node {
        private Task item;

        private Node next;

        Node(Task item) {
            this.item = item;
        }
    }


    //消费者线程
    class Consume implements Runnable {

        @Override
        public void run() {



        }
    }


    class Task {
        int no;

        Task(int no) {
            this.no = no;
        }
    }

    private void enqueue(Task item){

    }


}
