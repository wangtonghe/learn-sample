package com.wthfeng.learn.test;

import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangtonghe
 * @since 2019/3/28 21:28
 */
public class AlgTest {

    public int getTotal2(int num) {

        int result = 0;

        while (num > 1) {
            int tmp = num / 2;
            result += tmp;
            num = tmp + num % 2;
        }
        return result;
    }

    public int getTotal(int origin) {


        if (origin <= 1) {
            return 0;
        }
        if (origin == 2) {
            return 1;
        }

        int result = 0;
        int ret = 0;
        int num = origin;
        int odd = 0;


        while ((ret = (num / 2)) != 0) {
            if (num % 2 == 1) {
                odd++;
            }
            result += ret;
            num = ret;

        }
        return result + getTotal(odd + 1);

    }

    public static List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> result = new ArrayList<>();
        int len = A.length;
        for (int i = 0; i < len; i++) {
            int num = getNumFromArr(A, i);
            result.add(num % 5 == 0);

        }
        return result;
    }

    private static int getNumFromArr(int[] arr, int j) {
        int result = 0;
        for (int k = 0; k <= j; k++) {
            result += arr[k] * (int) (Math.pow(2, j - k));
        }
        return result;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public int[] nextLargerNodes(ListNode head) {
        ListNode node = head;
        int len = 0;
        int[] arr = new int[10000];
        while (node != null) {
            arr[len++] = node.val;
            node = node.next;
        }
        int[] result = new int[len];
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (arr[j] > arr[i]) {
                    result[i] = arr[j];
                    break;
                }
            }
        }
        result[len - 1] = 0;
        return result;

    }


    public static void main(String[] args) {
        AlgTest algTest = new AlgTest();
        ListNode listNode = new ListNode(2);
        ListNode sec = new ListNode(1);
        ListNode th = new ListNode(5);
        listNode.next = sec;
        sec.next = th;


        int[] arr = algTest.nextLargerNodes(listNode);
        Arrays.stream(arr).forEach(System.out::println);


        int[][] array = new int[4][4];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j <array[i].length ; j++) {

            }

        }

    }
}
