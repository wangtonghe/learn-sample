package com.wthfeng.learn.test;

/**
 * @author wangtonghe
 * @since 2019/3/29 09:22
 */
public class LinkedTest {

    static class Node {
        private int data;

        private Node next;

        public Node(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }


    public static void traversal(Node head) {
        Node p = head;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
    }

    /**
     * 翻转链表，主要记录当前指针和之前的指针，下一个指针主要为记录临时变量。
     */
    public static Node reserve(Node node) {

        if (node == null) {
            return null;
        }
        Node pre = null;
        Node cur = node;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;

        }
        return pre;
    }

    public static void main(String[] args) {
        Node head = new Node(2);
        Node node1 = new Node(21);
        Node node2 = new Node(34);
        Node node3 = new Node(45);
        head.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);

//        traversal(head);

        Node cur = reserve(head);
        traversal(cur);


    }


}
