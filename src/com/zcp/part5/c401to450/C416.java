package com.zcp.part5.c401to450;

/**
 * @author ZCP
 * @title: C416
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/partition-equal-subset-sum/description/
 * @date 2022/11/24 9:02
 */
public class C416 {

    public boolean canPartition(int[] nums) {
        int sum = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        if (sum % 2 == 1) {
            return false;
        }

        int target = sum / 2;

        int[][] dp = new int[n + 1][target + 1];
        return f(nums, 0, target, dp);


    }

    public boolean f(int[] nums, int i, int remain, int[][] dp) {
        if (remain == 0) {
            return true;
        }
        if (i >= nums.length || remain < 0) {
            return false;
        }
        if (dp[i][remain] != 0) {
            return dp[i][remain] == 1;
        }
        dp[i][remain] = f(nums, i + 1, remain, dp) || f(nums, i + 1, remain - nums[i], dp) ? 1 : -1;
        return dp[i][remain] == 1;

    }
}
