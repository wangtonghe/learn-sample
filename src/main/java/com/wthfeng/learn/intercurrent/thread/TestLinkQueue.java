package com.wthfeng.learn.intercurrent.thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author wangtonghe
 * @date 2017/5/17 18:38
 */
public class TestLinkQueue<E> {


    private AtomicReference<Node<E>> head = new AtomicReference<>();

    private AtomicReference<Node<E>> tail = head;

    private static class Node<E>{
        final E node;
        final AtomicReference<Node<E>> next;

        public Node(E node, Node<E> next) {
            this.node = node;
            this.next = new AtomicReference<>(next);
        }
    }

//    public boolean put(E item){
//        TreeNode<E> node = new TreeNode<>(item,null);
//        while(true){
//            TreeNode<E> curTail = tail.get();
//            curTail.next
//        }
//
//    }


}
