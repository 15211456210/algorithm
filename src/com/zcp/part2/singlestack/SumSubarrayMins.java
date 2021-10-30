package com.zcp.part2.singlestack;

import java.util.List;
import java.util.Stack;

/**
 * @author ：ZCP
 * @date ：Created in 2021/10/5
 * @description：[[子数组最小值的累加和]] https://leetcode-cn.com/problems/sum-of-subarray-minimums/
 * @version:
 */
public class SumSubarrayMins {

    public int sumSubarrayMins(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        long ans = 0;
        Stack<Integer> singleStack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!singleStack.isEmpty() && arr[singleStack.peek()] >= arr[i]) {
                Integer pop = singleStack.pop();
                int left = singleStack.isEmpty() ? -1 : singleStack.peek();
                ans += (long) (pop - left) * (i - pop) * arr[pop];
                ans %= 1000000007;
            }
            singleStack.push(i);
        }
        while (!singleStack.isEmpty()) {
            Integer pop = singleStack.pop();
            int left = singleStack.isEmpty() ? -1 : singleStack.peek();
            ans += (long) (arr.length - pop) * (pop - left) * arr[pop];
            ans %= 1000000007;
        }
        return (int) ans;
    }

    public int checkFun(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int min = arr[i];
                for (int k = i; k <= j; k++) {
                    min = Math.min(min, arr[k]);
                }
                ans += min;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int range = 10000;
        int size = 500;
        int testTime = 1000;
        SumSubarrayMins sumSubarrayMins = new SumSubarrayMins();
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] array = generateArray(size, range);
            int r1 = sumSubarrayMins.checkFun(array);
            int r2 = sumSubarrayMins.sumSubarrayMins(array);
            if (r1 != r2) {
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }

    private static int[] generateArray(int length, int range) {
        int len = (int) (Math.random() * length);
        int[] arr = new int[len];

        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * range) + 1;
        }
        return arr;
    }

}
