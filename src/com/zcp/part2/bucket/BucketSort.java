package com.zcp.part2.bucket;

import com.zcp.part2.heap.HeapSort;

import java.util.Arrays;

/**
 * 桶排序
 */
public class BucketSort {

    public static void solution(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = Math.max(max, i);
        }

        int maxDigit = digit(max);
        for (int i = 0; i < maxDigit; i++) {
            int[] count = new int[10];
            for (int k = 0; k < arr.length; k++) {
                count[(arr[k] / (int) Math.pow(10, i)) % 10]++;
            }
            for (int m = 1; m < count.length; m++) {
                count[m] = count[m - 1] + count[m];
            }
            int[] tmp = new int[arr.length];
            for (int k = arr.length - 1; k >= 0; k--) {
                int pos = --count[(arr[k] / (int) Math.pow(10, i)) % 10];
                tmp[pos] = arr[k];
            }
            for (int m = 0; m < arr.length; m++) {
                arr[m] = tmp[m];
            }
        }

    }

    private static int digit(int max) {
        int w = 1;
        while ((max = max / 10) > 0) {
            w++;
        }
        return w;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int length = 500;
        int range = 500;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] array = generateArray(length, range);
            int[] array1 = copyArray(array);
            solution(array);
            HeapSort.solution2(array1);
            if ( !check(array,array1)) {
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }


    private static boolean check(int[] array, int[] array1) {
        for (int i = 0; i < array.length; i++) {
            if(array[i]!= array1[i]){
                return false;
            }
        }
        return true;
    }

    private static int[] generateArray(int length, int range) {
        int len = (int) (Math.random() * length);
        int[] arr = new int[len];

        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * range);
        }
        return arr;
    }

    private static int[] copyArray(int[] arr) {
        int len = arr.length;
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }
}
