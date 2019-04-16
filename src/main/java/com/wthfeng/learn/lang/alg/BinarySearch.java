package com.wthfeng.learn.lang.alg;

import org.junit.Test;

/**
 * @author wangtonghe
 * @since 2018/9/23 10:35
 */
public class BinarySearch {

    /**
     * 二分查找递归实现
     *
     * @param arr
     * @param low
     * @param high
     * @param dest
     * @return
     */
    public int binarySearch(int[] arr, int low, int high, int dest) {
        if (low <= high) {
            int mid = (low + high) / 2;
            if (dest > arr[mid]) {
                return binarySearch(arr, mid + 1, high, dest);
            } else if (dest < arr[mid]) {
                return binarySearch(arr, low, mid - 1, dest);
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 二分查找非递归实现
     *
     * @param arr
     * @param dest
     * @return
     */
    public int binarySearchNotR(int[] arr, int dest) {
        int low = 0;
        int high = arr.length - 1;
        int index = -1;
        while (low <= high) {
            // 右移一位即除以2
            int mid = (low + high) >> 1;
            if (dest > arr[mid]) {
                low = mid + 1;
            } else if (dest < arr[mid]) {
                high = mid - 1;
            } else {
                index = mid;
                break;
            }
        }
        return index;
    }

    @Test
    public void testBinarySearch() {
        int[] arr = {1, 3, 7, 8, 10, 12, 14, 30, 34, 90};
        System.out.println(binarySearch(arr, 0, arr.length - 1, 14));
        System.out.println(binarySearchNotR(arr, 14));

    }

    @Test
    public void testBinaryCase() {
        int n = 100;
        String str = Integer.toBinaryString(n);
        System.out.println(str);

    }
}
