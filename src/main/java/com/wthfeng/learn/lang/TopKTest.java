package com.wthfeng.learn.lang;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangtonghe
 * @since 2019/3/17 00:22
 */
public class TopKTest {

    public static void main(String[] args) {

        TopKTest top = new TopKTest();
        int[] arr = {3, 67, 41, 23, 12, 6, 35, 78, 45, 32, 1, 9, 2, 21, 81, 6};
        List<Integer> list = top.topK(arr, 8);
        System.out.println(list);

    }

    public List<Integer> topK(int[] array, int k) {

        int len = array.length;

        if (len == 0 || len < k) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();

        int j = part(array, 0, array.length - 1);
        while (j + 1 != k) {
            if (j + 1 > k) {
                j = part(array, 0, j - 1);
            } else {
                j = part(array, j + 1, array.length - 1);
            }
        }
        for (int i = 0; i < k; i++) {
            result.add(array[i]);
        }

        return result;
    }


    public int part(int[] arr, int low, int high) {
        int i = low;
        int j = high;
        int base = arr[low];

        while (i < j) {
            while (base <= arr[j] && i < j) {
                j--;

            }
            while (base >= arr[i] && i < j) {
                i++;

            }
            swap(arr, i, j);
        }
        swap(arr, low, j);
        return j;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
