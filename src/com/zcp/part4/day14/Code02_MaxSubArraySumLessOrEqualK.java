package com.zcp.part4.day14;

import java.util.Arrays;
import java.util.TreeSet;

public class Code02_MaxSubArraySumLessOrEqualK {


    /**
     * 思路：
     * 有序表
     *
     * @param arr
     * @param K
     * @return
     */
    public static int getMaxLessOrEqualK2(int[] arr, int K) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int len = arr.length;
        int[] preSum = new int[len];
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(0);
        int maxSubSum = 0;
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                preSum[i] = arr[0];
            } else {
                preSum[i] = preSum[i - 1] + arr[i];
            }
            treeSet.add(preSum[i]);
            int target = preSum[i] - K;
            Integer ceiling = treeSet.ceiling(target);
            maxSubSum = Math.max(preSum[i] - (ceiling == null ? 0 : ceiling), maxSubSum);

        }
        return maxSubSum;
    }

    // 请返回arr中，求个子数组的累加和，是<=K的，并且是最大的。
    // 返回这个最大的累加和
    public static int getMaxLessOrEqualK(int[] arr, int K) {
        // 记录i之前的，前缀和，按照有序表组织
        TreeSet<Integer> set = new TreeSet<Integer>();
        // 一个数也没有的时候，就已经有一个前缀和是0了
        set.add(0);
        int max = Integer.MIN_VALUE;
        int sum = 0;
        // 每一步的i，都求子数组必须以i结尾的情况下，求个子数组的累加和，是<=K的，并且是最大的
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i]; // sum -> arr[0..i];
            if (set.ceiling(sum - K) != null) {
                max = Math.max(max, sum - set.ceiling(sum - K));
            }
            set.add(sum); // 当前的前缀和加入到set中去
        }
        return max;
    }


    public static int[] generateRandomArray(int size, int range) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = ((int) (Math.random() * range)) - ((int) (Math.random() * range));
        }
        return array;
    }

    public static void main(String[] args) {
        int testTime = 1000;
        int size = 1000;
        int range = 500;

        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(size, range);
            int[] arr2 = new int[size];
            int K = (int) (Math.random() * range);
            System.arraycopy(arr1, 0, arr2, 0, size);
            int ans1 = getMaxLessOrEqualK(arr1, K);
            int ans2 = getMaxLessOrEqualK2(arr1, K);
            if (ans1 != ans2) {
                System.out.println("oop!");
                System.out.println(Arrays.toString(arr1) + "  K:" + K);
                System.out.println("ans1:" + ans1);
                System.out.println("ans2:" + ans2);
                break;
            }
        }
        System.out.println("nice!");
    }

}
