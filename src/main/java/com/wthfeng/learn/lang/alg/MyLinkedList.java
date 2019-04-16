package com.wthfeng.learn.lang.alg;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 单向链表结构
 * 只有一个头指针的链表，实现链表的添加、删除、遍历、
 *
 * @author wangtonghe
 * @since 2018/9/22 10:43
 */
public class MyLinkedList<T> {

    private Node<T> head;


    private class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }

        public Node() {
        }
    }

    public MyLinkedList() {
    }

    public MyLinkedList(List<T> list) {
        for (T t : list) {
            add2Tail(t);
        }
    }

    public void add2Head(T value) {
        Node node = new Node(value);
        node.next = head;
        head = node;
    }

    public void add2Tail(T value) {
        Node newNode = new Node(value);
        Node tmp = head;
        if (head == null) {
            head = newNode;
            return;
        }
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = newNode;

    }

    public int getLength() {
        if (head == null) {
            return 0;
        }
        int length = 0;
        Node tmp = head;
        while (tmp != null) {
            length++;
            tmp = tmp.next;
        }
        return length;
    }

//    public T removeFromTail() {
//        // 链表为空
//        if (head == null) {
//            throw new RuntimeException("链表为空，不能删除");
//        }
//        T result = null;
//        // 链表只有一个元素
//        if (head.next == null) {
//            result = head.value;
//            head = null;
//            return result;
//        }
//        Node tmp = head;
//        Node remNode = null;
//        while (tmp.next != null) {
//            if (tmp.next.next == null) {
//                remNode = tmp;
//                break;
//            }
//            tmp = tmp.next;
//        }
//        if (remNode == null) {
//            remNode = head;
//        }
//        result = remNode.next.value;
//        remNode.next = null;
//        return result;
//    }

    public T removeFromHead() {
        if (head == null) {
            throw new RuntimeException("链表为空，不能删除");
        }
        T result = head.value;
        head = head.next;
        return result;

    }

    public void display() {

        if (head == null) {
            System.out.println("该链表没有元素");
        }
        Node tmp = head;
        while (tmp != null) {
            System.out.print(tmp.value + " ");
            tmp = tmp.next;
        }
        System.out.println("");
    }

    public void printReverse() {
        ArrayList<T> stack = new ArrayList<>();
        Node node = head;
        while (node != null) {
//            stack
        }


    }

    public ArrayList<Integer> printListFromTailToHead(Node<Integer> listNode) {

        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> result = new ArrayList<>();
        for (Node<Integer> ln = listNode; ln != null; ln = ln.next) {
            stack.push(ln.value);
        }

        for (int i = 0; i < stack.size(); i++) {
            result.add(stack.pop());
        }
        return result;
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        list.add2Tail(67);
        list.add2Tail(0);
        list.add2Tail(24);
        list.add2Tail(58);

        List<Integer> result = list.printListFromTailToHead(list.head);

        result.forEach(t -> System.out.println(t));


    }


}



