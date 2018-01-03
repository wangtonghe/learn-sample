package com.wthfeng.learn.lang;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author wangtonghe
 * @date 2017/10/11 19:26
 */
public class BucketSort {

    public int[] sort(int[] arr, int sum) {
        int length = arr.length;
        int[] bucket = new int[sum];
        for (int i = 0; i < length; i++) {
            bucket[arr[i]]++;
        }
        int[] result = new int[length];
        int index = 0;
        for (int i = 0; i < sum; i++) {

            if (bucket[i] > 0) {
                int num = bucket[i];
                for (int j = 0; j < num; j++) {
                    result[index] = i;
                    index++;
                }
            }
        }
        return result;
    }

    @Test
    public void test() {
        int[] arr = {3, 4, 8, 1, 2, 6, 5, 9, 7};
        int[] arr2 = sort(arr, 10);
        System.out.println(Arrays.toString(arr2));

    }
}
