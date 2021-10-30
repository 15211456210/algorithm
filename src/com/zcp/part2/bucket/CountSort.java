package com.zcp.part2.bucket;

import java.util.Arrays;

/**
 * 计数排序
 * 不基于比较的排序，有较大的局限性
 */
public class CountSort {
    /**
     * 数组元素都大于0
     *
     * @param arr
     */
    public static void solution(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int[] help = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            help[arr[i]]++;
        }
        int index = 0;
        int indexHp = 0;
        while (index < arr.length) {
            int count = help[indexHp];
            while (count-- > 0) {
                arr[index++] = indexHp;
            }
            indexHp++;
        }
    }


    public static void main(String[] args) {
        int[] arr = {2,3,8,1,8,4,24,5,8,13,85,1,0,32,0};
        solution(arr);
        System.out.println(Arrays.toString(arr));
    }


}
