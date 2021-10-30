package com.zcp.part2.slidewin;

import java.util.LinkedList;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/28
 * @description：[[整形数组中子数组最大值减最小值达标的子数组个数]] 给出两个参数arr[]数组，和k
 * 返回数组arr 所有满足 子数组中最大值max-最小值min<= k的子数组个数
 * @version:
 */
public class SubArrayMaxMinCount {

    /**
     * 对照函数，暴力
     *
     * @param arr
     * @param k
     * @return
     */
    public static int checkFun(int[] arr, int k) {
        if (arr == null || arr.length < 1 || k < 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int min = arr[i];
                int max = arr[j];
                for (int m = i; m <= j; m++) {
                    min = Math.min(arr[m], min);
                    max = Math.max(arr[m], max);
                }
                if (max - min <= k) {
                    ans++;
                }
            }
        }
        return ans;
    }

    /**
     * 滑动窗口求解
     *
     * @param arr
     * @param k
     * @return
     */
    public static int solution(int[] arr, int k) {
        if (arr == null || arr.length < 1 || k < 0) {
            return 0;
        }
        LinkedList<Integer> maxQueue = new LinkedList<>();
        LinkedList<Integer> minQueue = new LinkedList<>();
        int ans = 0;
        int L = 0, R = 0;
        while (L < arr.length) {
            while (R < arr.length) {
                while (!maxQueue.isEmpty() && arr[maxQueue.peekLast()] <= arr[R]) {
                    maxQueue.pollLast();
                }
                maxQueue.addLast(R);
                while (!minQueue.isEmpty() && arr[minQueue.peekLast()] >= arr[R]) {
                    minQueue.pollLast();
                }
                minQueue.addLast(R);
                if (arr[maxQueue.peekFirst()] - arr[minQueue.peekFirst()] > k) {
                    break;
                }
                R++;
            }
            ans += R - L;
            if (maxQueue.peekFirst() == L) {
                maxQueue.pollFirst();
            }
            if (minQueue.peekFirst() == L) {
                minQueue.pollFirst();
            }
            L++;
        }
        return ans;
    }


    public static void main(String[] args) {
        int size = 50;
        int range = 10;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] array = generateRandomArray(size, range);
            int k = (int) (Math.random() * range);
            if (checkFun(array,k) != solution(array,k)){
                System.out.println("出错了");
                break;
            }
            System.out.println(checkFun(array,k));
        }
    }

    public static int[] generateRandomArray(int size, int range) {
        int sz = (int) (Math.random() * size);
        int[] ans = new int[sz];
        for (int i = 0; i < sz; i++) {
            ans[i] = (int) (Math.random() * range);
        }
        return ans;
    }
}
