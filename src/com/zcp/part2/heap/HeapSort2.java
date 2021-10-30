package com.zcp.part2.heap;

import java.util.Arrays;

/**
 * 相对有序数组排序
 * 一个无序数组排好序之后每个数相对于原先的位置距离不超过K
 * [1,4,5,2,3]->[1,2,3,4,5]  K=2  K<arr.length
 */
public class HeapSort2 {


    public static void solution(int[] arr, int K) {
        if (arr == null || arr.length < 2) {
            return;
        }
        MyHeapD.MyHeap myHeap = new MyHeapD.MyHeap();
        int i = 0;
        for (; i < K + 1; i++) {
            myHeap.add(arr[i]);
        }
        int index = 0;
        while (!myHeap.isEmpty()){
            arr[index++] = myHeap.poll();
            if(i<arr.length){
                myHeap.add(arr[i++]);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,4,5,2,3};
        solution(arr,2);
        System.out.println(Arrays.toString(arr));//[1, 2, 3, 4, 5]
    }
}
