package com.wthfeng.learn.lang.alg;

/**
 * 我的链表2
 * 含有首尾指针，首部添加，尾部删除
 *
 * @author wangtonghe
 * @since 2018/9/22 13:36
 */
public class MyLinkedList2<T> {

    private Node head;

    private Node tail;

    private int length;

    private class Node {
        T value;
        Node next;

        Node(T value) {
            this.value = value;
        }
    }

    public void add2Head(T value) {
        Node node = new Node(value);
        node.next = head;
        head = node;
        length++;
    }

    public void add2Tail(T value) {
        Node node = new Node(value);
        tail.next = node;
        tail = node;
    }


}
