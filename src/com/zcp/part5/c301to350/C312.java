package com.zcp.part5.c301to350;

/**
 * @author ZCP
 * @title: C312
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/burst-balloons/submissions/
 * @date 2022/9/29 14:59
 */
public class C312 {

    public int maxCoins(int[] nums) {
        int[][] dp = new int[nums.length + 2][nums.length + 2];
        int[] arr = new int[nums.length + 2];
        System.arraycopy(nums, 0, arr, 1, nums.length);
        arr[0] = 1;
        arr[arr.length - 1] = 1;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return fun(arr, 0, nums.length + 1, dp);
    }

    public int fun(int[] arr, int i, int j, int[][] dp) {
        if (i >= j - 1) {
            return 0;
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int max = 0;
        for (int k = i + 1; k < j; k++) {
            int c = arr[k] * arr[i] * arr[j];
            max = Math.max(max, c + fun(arr, i, k, dp) + fun(arr, k, j, dp));
        }
        dp[i][j] = max;
        return dp[i][j];

    }
}
