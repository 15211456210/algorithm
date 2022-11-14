package com.zcp.part5.c151to200;

/**
 * @author ZCP
 * @title: C152
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/maximum-product-subarray/submissions/
 * @date 2022/9/7 13:34
 */
public class C152 {

    public int maxProduct(int[] nums) {
        int[][] dp = new int[nums.length][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        int max = dp[0][0];
        for (int i = 1; i < nums.length; i++) {
            dp[i][0] = Math.max(nums[i], Math.max(dp[i - 1][0] * nums[i], dp[i - 1][1] * nums[i]));
            dp[i][1] = Math.min(nums[i], Math.min(dp[i - 1][0] * nums[i], dp[i - 1][1] * nums[i]));
            max = Math.max(max, dp[i][0]);
        }
        return max;
    }
}
