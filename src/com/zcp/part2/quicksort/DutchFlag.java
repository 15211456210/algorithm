package com.zcp.part2.quicksort;

import java.util.Arrays;

/**
 * 荷兰国旗问题
 * 给定一个数组arr，和一个整数num。请把小于等于num的数放在数组的左边，大于num的数放在数组的右边。
 * 要求额外空间复杂度O(1)，时间复杂度O(N)
 */
public class DutchFlag {

    public static void solution(int[] arr, int num) {
        if (arr == null || arr.length < 2) {
            return;
        }
        partition(arr, 0, arr.length - 1, num);
    }

    /**
     * arr[L...R]
     *
     * @param arr
     * @param L
     * @param R
     */
    public static void partition(int[] arr, int L, int R, int num) {
        int pl = L - 1;
        int pr = R + 1;
        int index = L;
        while (index < pr) {
            if (arr[index] <= num) {
                swap(arr, ++pl, index++);
            } else {
                swap(arr, --pr, index);
            }
        }
    }

    public static void swap(int[] arr, int p1, int p2) {
        int tmp = arr[p1];
        arr[p1] = arr[p2];
        arr[p2] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {5, 6, 8, 1, -2, 4, -8, 8, 6};
        solution(arr, 3);
        System.out.println(Arrays.toString(arr));
    }


}
