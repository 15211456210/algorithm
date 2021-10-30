package com.zcp.part2.quicksort;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 随机快排(3.0)
 */
public class QuickSort {
    public static void solution(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] partition = partition(arr, L, R);
        quickSort(arr, L, partition[0]);
        quickSort(arr, partition[1]+1,R);

    }


    /**
     * arr[L...R]
     *
     * @param arr
     * @param L
     * @param R
     */
    public static int[] partition(int[] arr, int L, int R) {
        swap(arr, L + (int) (Math.random() * (R - L)), R);//随机选择一个标志位
        int flag = arr[R];
        int small = L - 1;
        int more = R;
        int index = L;
        while (index < more) {
            if (arr[index] < flag) {
                swap(arr, ++small, index++);
            } else if (arr[index] > flag) {
                swap(arr, --more, index);
            } else {
                //arr[index] = flag
                index++;
            }
        }
        swap(arr, more, R);
        return new int[]{small, more};
    }

    public static void swap(int[] arr, int p1, int p2) {
        int tmp = arr[p1];
        arr[p1] = arr[p2];
        arr[p2] = tmp;
    }

    /**
     * 对照函数
     * @param arr
     */
    public static void solution2(int[] arr){
        if (arr == null || arr.length<2){
            return;
        }
        Arrays.sort(arr);
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
            solution2(array1);

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
            arr[i] = (int) (Math.random() * range) - (int) (Math.random() * range);
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
