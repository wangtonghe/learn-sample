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
public class MyLinkedBlockingQueue<E> {

    //头节点
    private Node<E> first;

    //尾节点
    private Node<E> tail;

    //总数，为空则为int的最大值
    private volatile int capacity;

    //当前队列元素个数
    private AtomicInteger count = new AtomicInteger(0);


    private Lock takeLock = new ReentrantLock();

    private Condition takeCondition = takeLock.newCondition();

    private Lock putLock = new ReentrantLock();

    private Condition putCondition = putLock.newCondition();

    public MyLinkedBlockingQueue(int capacity) {
        this.capacity = capacity;
        tail = first = new Node<>(null);
    }

    static class Node<E> {
        E item;
        Node<E> next;

        Node(E item) {
            this.item = item;
        }
    }


    /**
     * 入队，若队列满则等待
     *
     * @param e 入队元素
     */
    public void put(E e) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        }
        Node<E> node = new Node<>(e);
        int c = -1;
        takeLock.lockInterruptibly(); //takeLock，添加元素的锁
        try {
            while (count.get() == capacity) {  //若队列满，阻塞以等待
                takeCondition.await();
            }
            enqueue(node);
            c = count.incrementAndGet();  //更新队列元素数
            if (c < capacity) {
                takeCondition.signal();  //若入队后发现还有空位，通知其他阻塞的入队线程（若有）
            }
        } finally {
            takeLock.unlock();
        }
        if (c == 1) {  //若入队前队列为空，则通知被阻塞的出队线程，现在可以出队了
            putLock.lockInterruptibly();
            try {
                putCondition.signal();
            } finally {
                putLock.unlock();
            }
        }
    }

    /**
     * 出队,若无元素一直等待
     *
     * @return 出队元素
     */
    public E take() throws InterruptedException {
        takeLock.lock();   //takeLock，移除元素的锁
        E e = null;
        int c = -1;
        try {
            while (count.get() == 0) { //队列为空，移除操作阻塞
                takeCondition.await();
            }
            e = dequeue();
            c = count.decrementAndGet(); //更新队列元素数
            if (c > 0) { //若出队后仍有元素，通知其他被阻塞的出队线程（若有）
                takeCondition.signal();
            }
        } finally {
            takeLock.unlock();
        }
        if (c == capacity - 1) {  //若出队前队列已满，通知阻塞的入队线程，现在可以入队了
            putLock.lockInterruptibly();
            try {
                putCondition.signal();
            } finally {
                putLock.unlock();
            }
        }
        return e;
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


    public int size() {
        return count.get();
    }


}
