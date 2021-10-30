package com.zcp.part2.heap;

import java.util.Arrays;

/**
 * 堆排序
 * 1，先让整个数组都变成大根堆结构，建立堆的过程:
 * 1)从上到下的方法，时间复杂度为O(N * logN)
 * 2)从下到上的方法，时间复杂度为O(N)
 * 2，把堆的最大值和堆末尾的值交换，然后减少堆的大小之后，再去调整堆，一直周而复始，时间复杂度为O(N * logN)
 * 3，堆的大小减小成0之后，排序完成
 */
public class HeapSort {

    public static void solution(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //创建大根堆
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }

        int size = arr.length;
        //heapify 排序
        while (size > 0) {
            swap(arr, 0, --size);
            heapify(arr, size);
        }


    }

    private static void heapify(int[] arr, int size) {
        int index = 0;
        int left = index * 2 + 1;
        while (left < size) {
            int right = left + 1;
            if (right < size && arr[right] > arr[left] && arr[index] < arr[right]) {
                swap(arr, right, index);
                index = right;
            } else if (arr[index] < arr[left]) {
                swap(arr,left,index);
                index = left;
            }else {
                break;
            }
            left = index * 2 + 1;
        }
    }

    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    /**
     * 对照函数
     * @param arr
     */
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
