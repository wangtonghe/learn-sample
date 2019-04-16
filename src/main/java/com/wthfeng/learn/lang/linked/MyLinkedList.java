package com.wthfeng.learn.lang.linked;

/**
 * 双向链表
 *
 * @author wangtonghe
 * @since 2019/2/20 10:32
 */
public class MyLinkedList<T> {

    /**
     * 头节点
     */
    private Node<T> head;

    /**
     * 尾节点
     */
    private Node<T> tail;

    /**
     * 链表长度
     */
    private int size;

    /**
     * node节点
     *
     * @param <T>
     */
    static class Node<T> {

        T data;

        Node<T> next;

        Node<T> prev;

        public Node(T data) {
            this.data = data;
        }

        Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    public void add(T data) {

        Node<T> t = tail;

        // 新节点
        Node<T> newNode = new Node<>(data);

        tail = newNode;
        if (t == null) {
            head = newNode;
        } else {
            t.next = newNode;
            newNode.prev = t;
        }
        size++;
    }

    public boolean remove(T data) {

        if (data == null) {

            for (Node<T> node = head; node != null; node = node.next) {

                if (node.data == null) {
                    remove(node);
                    return true;
                }
            }
        } else {

            for (Node<T> node = head; node != null; node = node.next) {
                if (data.equals(node.data)) {
                    remove(node);
                    return true;
                }
            }
        }
        return false;

    }

    private void remove(Node<T> node) {

        Node<T> p = node.prev;
        Node<T> n = node.next;
        if (p == null) {
            head = tail = null;
        } else {
            p.next = n;
            n.prev = p;
            node.next = null;
            node = null;
            tail = n;
        }
        size--;
    }

    public boolean add(T data, int index) {

        checkPosition(index);

        Node<T> newNode = new Node<>(data);

        Node<T> node = head;

        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        Node<T> p = node.prev;

        p.next = newNode;
        newNode.prev = p;
        newNode.next = node;
        node.prev = newNode;

        return true;
    }

    public int size() {
        return size;
    }

    private void checkPosition(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }

    }

    public void display() {
        if (tail != null) {
            Node<T> tmp = head;
            while (tmp != null) {
                System.out.print(tmp.data + "\t");
                tmp = tmp.next;
            }
        }
    }

    public boolean isLoop() {

        if (tail == null) {
            throw new NullPointerException("list is null");
        }
        Node<T> tmp = head;
        Node<T> fast = tmp;
        Node<T> low = tmp;
        boolean loop = false;

        while (low != null) {
            System.out.println(low.data);
            low = low.next;
            if (fast == null || fast.next == null) {
                break;
            }
            fast = fast.next.next;
            if (low == fast) {
                loop = true;
                break;
            }
        }
        return loop;
    }

    public static void main(String[] args) {

        MyLinkedList<Integer> list = new MyLinkedList<>();

        list.add(4);
        list.add(5);
        list.add(2);
        list.add(6);
        list.add(7);
        list.add(1);
        list.display();

//        list.tail.next = list.head;
        System.out.println(list.isLoop());


    }


}
