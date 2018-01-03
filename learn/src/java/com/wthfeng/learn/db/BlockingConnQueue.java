package com.wthfeng.learn.db;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangtonghe
 * @date 2017/12/20 10:57
 */
public class BlockingConnQueue<E> {

    //头节点
    private Node<E> first;

    //尾节点
    private Node<E> tail;

    //总数，为空则为int的最大值
    private volatile int capacity;

    //当前队列元素个数
    private AtomicInteger count = new AtomicInteger(0);


    private Lock takeLock = new ReentrantLock();

    private Condition notFull = takeLock.newCondition();

    private Lock putLock = new ReentrantLock();

    private Condition notEmpty = putLock.newCondition();


    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();


    public BlockingConnQueue(int capacity) {
        this.capacity = capacity;
        tail = first.next;
    }

    public BlockingConnQueue() {
        this.capacity = Integer.MAX_VALUE;
        tail = first.next;
    }

    static class Node<E> {
        E item;
        Node<E> next;

        Node(E item) {
            this.item = item;
        }
    }


    /**
     * 阻塞入队
     *
     * @param e 入队元素
     */
    public void put(E e) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        }
        Node<E> node = new Node<>(e);
        takeLock.lock();
        try {
            while (count.get() == capacity) {
                notEmpty.await();
            }
            enqueue(node);
            count.incrementAndGet();
        } finally {
            takeLock.unlock();
        }
    }

    /**
     * 阻塞出队,若无元素一直等待
     *
     * @return 出队元素
     */
    public E take() throws InterruptedException {
        takeLock.lock();
        E e2 = null;
        int c = -1;
        try {
            while (count.get() == 0) {
                notFull.await();
            }
            E e = dequeue();
            c = count.decrementAndGet();

        } finally {
            takeLock.unlock();
        }
        return e2;
    }


    public void add(E e) throws InterruptedException {
        lock.lock();
        try {

            enqueue(new Node<>(e));
            condition.signal();
            count.incrementAndGet();
        } finally {
            lock.unlock();
        }
    }

    public E remeve() throws InterruptedException {
        lock.lock();
        try {
            while (count.get() == 0) {
                condition.await();
            }
            dequeue();
            count.decrementAndGet();
        } finally {
            lock.unlock();
        }
        return null;
    }


    /**
     * 入队，若队满等待一定时间，超时返回
     */
    public void offer(E e, long timeout) throws InterruptedException {

    }

    /**
     * 出队，若对空等待一定时间，超时返回
     *
     * @return bool
     */
    public Node poll() {
        return null;

    }

    /**
     * 从队尾入队
     *
     * @param e 入队元素
     */
    private void enqueue(Node<E> e) {
        tail.next = e;
        tail = tail.next;
    }

    //从队首出队
    private E dequeue() {
        Node<E> head = first;
        first = first.next;
        return head.item;
    }


}
