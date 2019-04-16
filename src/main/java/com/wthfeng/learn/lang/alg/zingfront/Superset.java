package com.wthfeng.learn.lang.alg.zingfront;

import java.util.Objects;

/**
 * @author wangtonghe
 * @since 2019/4/12 23:05
 */
public class Superset {

    public static void main(String[] args) {
        Superset superset = new Superset();
        boolean isSuper = superset.isSuperSet("abbccdddeee", "abcddceeede");
        System.out.println(isSuper);
    }

    /**
     * 判断a是否是b的超集，使用一个map做辅助变量。时间复杂度为O(m+n),m为第一个字符长度，n为第二个字符串长度
     * 空间复杂度为O(n)级别，需要存储字符串a的字符。
     * 算法： 将字符串a存入map并计数，然后遍历b字符串，对于每个字符看a中是否存在，存在则减1，不存在直接返回false
     *
     * @param a a字符串
     * @param b b字符串
     */
    private boolean isSuperSet(String a, String b) {
        boolean isSuper = true;
        int aLen = a.length();
        int bLen = b.length();
        char c;
        Integer num;
        MyHashMap<Character, Integer> map = new MyHashMap<>();
        // 遍历a字符串，使用map记录每个字符个数
        for (int i = 0; i < aLen; i++) {
            c = a.charAt(i);
            if ((num = map.get(c)) != null && num > 0) {
                map.put(c, num + 1);
            } else {
                map.put(c, 1);
            }
        }
        // 遍历b字符串，对于每个字符看在map中是否存在，若不存在或为0，直接返回false,若存在则减1
        for (int i = 0; i < bLen; i++) {
            c = b.charAt(i);
            if ((num = map.get(c)) == null || num == 0) {
                isSuper = false;
                break;
            } else {
                map.put(c, num - 1);
            }
        }
        return isSuper;
    }


    class MyHashMap<K, V> {

        // 默认大小16
        final static int DEFAULT_SIZE = 1 << 4;

        // 实际元素个数
        private int size;

        private Node<K, V>[] table;

        // 容量
        private int capacity;

        private float loadFactor;

        // 默认负载因子 0.75
        final static float DEFAULT_LOAD_FACTOR = 0.75f;


        public MyHashMap() {
            this.capacity = DEFAULT_SIZE;
            table = (Node<K, V>[]) new Node[capacity];
            loadFactor = DEFAULT_LOAD_FACTOR;

        }

        public MyHashMap(int capacity) {
            this.capacity = capacity;
            table = (Node<K, V>[]) new Node[capacity];
            loadFactor = DEFAULT_LOAD_FACTOR;
        }


        private int hash(Object key) {
            return key == null ? 0 : Objects.hash(key);
        }

        public void put(K key, V value) {

            if (key == null) {
                return;
            }
            int hash = hash(key);
            int i = hash % capacity;
            Node<K, V> p, pre = null;
            boolean succ = false;
            if ((p = table[i]) == null) {
                table[i] = new Node<>(key, value, hash, null);
            } else {
                while (p != null) {
                    if (key.equals(p.key)) {
                        p.value = value;
                        succ = true;
                        break;
                    }
                    pre = p;
                    p = p.next;
                }
                if (!succ) {
                    pre.next = new Node<>(key, value, hash, null);
                }
            }
            if (size++ > capacity * loadFactor) {
                resize();
            }
        }

        public V get(K key) {
            if (key == null) {
                return null;
            }

            Node<K, V> node = table[hash(key) % capacity];
            V value = null;
            while (node != null) {
                if (key.equals(node.key)) {
                    value = node.value;
                    break;
                }
                node = node.next;
            }
            return value;
        }

        public boolean isContainsKey(K key) {
            return get(key) != null;
        }

        private void resize() {
            // TODO
        }

        public void print() {
            Node<K, V>[] tab = table;
            for (int i = 0; i < tab.length; i++) {
                Node<K, V> node = tab[i];
                while (node != null) {
                    System.out.println("key:" + node.key + ",value:" + node.value);
                    node = node.next;
                }
            }
        }


    }

    class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;
        final int hash;

        Node(K key, V value, int hash, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
        }
    }

}
