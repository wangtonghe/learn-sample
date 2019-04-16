//package com.wthfeng.learn.lang.alg;
//
//import java.util.Objects;
//
///**
// * @author wangtonghe
// * @since 2019/4/13 18:16
// */
//public class MyHashMap<K, V> {
//
//    // 默认大小16
//    final static int DEFAULT_SIZE = 1 << 4;
//
//    // 实际元素个数
//    private int size;
//
//    private Node<K, V>[] table;
//
//    // 容量
//    private int capacity;
//
//    private float loadFactor;
//
//    // 默认负载因子 0.75
//    final static float DEFAULT_LOAD_FACTOR = 0.75f;
//
//
//    public MyHashMap() {
//        this.capacity = DEFAULT_SIZE;
//        table = (Node<K, V>[]) new Node[capacity];
//        loadFactor = DEFAULT_LOAD_FACTOR;
//
//    }
//
//    public MyHashMap(int capacity) {
//        this.capacity = capacity;
//        table = (Node<K, V>[]) new Node[capacity];
//        loadFactor = DEFAULT_LOAD_FACTOR;
//    }
//
//
//    class Node<K, V> {
//        final K key;
//        V value;
//        Node<K, V> next;
//        final int hash;
//
//        Node(K key, V value, int hash, Node<K, V> next) {
//            this.key = key;
//            this.value = value;
//            this.hash = hash;
//            this.next = next;
//        }
//    }
//
//    private int hash(Object key) {
//        return key == null ? 0 : Objects.hash(key);
//    }
//
//    public void put(K key, V value) {
//
//        if (key == null) {
//            return;
//        }
//        int hash = hash(key);
//        int i = hash % capacity;
//        Node<K, V> p, pre = null;
//        boolean succ = false;
//        if ((p = table[i]) == null) {
//            table[i] = new Node<>(key, value, hash, null);
//        } else {
//            while (p != null) {
//                if (key.equals(p.key)) {
//                    p.value = value;
//                    succ = true;
//                    break;
//                }
//                pre = p;
//                p = p.next;
//            }
//            if (!succ) {
//                pre.next = new Node<>(key, value, hash, null);
//            }
//        }
//        if (size++ > capacity * loadFactor) {
//            resize();
//        }
//    }
//
//    public V get(K key) {
//        if (key == null) {
//            return null;
//        }
//
//        Node<K, V> node = table[hash(key) % capacity];
//        V value = null;
//        while (node != null) {
//            if (key.equals(node.key)) {
//                value = node.value;
//                break;
//            }
//            node = node.next;
//        }
//        return value;
//    }
//
//    public boolean isContainsKey(K key) {
//        return get(key) != null;
//    }
//
//    private void resize() {
//        // TODO
//    }
//
//    public void print() {
//        Node<K, V>[] tab = table;
//        for (int i = 0; i < tab.length; i++) {
//            Node<K, V> node = tab[i];
//            while (node != null) {
//                System.out.println("key:" + node.key + ",value:" + node.value);
//                node = node.next;
//            }
//        }
//    }
//
//
//}
