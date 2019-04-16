package com.wthfeng.learn.lang.alg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author wangtonghe
 * @since 2019/2/23 11:32
 */
public class LinkedTest {
    static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {

        Stack<Integer> stack = new Stack();
        ArrayList<Integer> result = new ArrayList<>();
        for (ListNode ln = listNode; ln != null; ln = ln.next) {
            stack.push(ln.val);
        }
        for (int i = 0; i < stack.size(); i++) {
            result.add(stack.pop());
        }
        return result;
    }


    public static void main(String[] args) {

        LinkedTest linkedTest = new LinkedTest();
        LinkedList<ListNode> list = new LinkedList<>();
        list.add(new ListNode(67));
        list.add(new ListNode(0));
        list.add(new ListNode(24));
        list.add(new ListNode(58));

//        linkedTest.printListFromTailToHead()


    }
}
