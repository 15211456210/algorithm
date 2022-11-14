package com.zcp.part5.c201to250;

/**
 * @author ZCP
 * @title: C213
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/house-robber-ii/submissions/
 * @date 2022/9/17 17:25
 */
public class C213 {

    public int rob(int[] nums) {
        if (nums == null || nums.length == 1) {
            return nums[0];
        }
        int[] dp1 = new int[nums.length - 1];//[0,1,2...len-2]
        int[] dp2 = new int[nums.length - 1];//[1,2,3...len-1]
        for (int i = 0; i < nums.length - 1; i++) {
            fun(nums, i, dp1, 0);
            fun(nums, i, dp2, 1);
        }
        return Math.max(dp1[nums.length - 2], dp2[nums.length - 2]);
    }


    public static int fun(int[] nums, int index, int[] dp, int offset) {
        if (dp[index] != 0) {
            return dp[index];
        }
        if (index == 0) {
            dp[index] = nums[0 + offset];
            return dp[index];
        }
        if (index == 1) {
            dp[index] = Math.max(nums[0 + offset], nums[1 + offset]);
            return dp[index];
        }
        if (index == 2) {
            dp[index] = Math.max(nums[0 + offset] + nums[2 + offset], nums[1 + offset]);
            return dp[index];
        }
        dp[index] = Math.max(nums[index + offset] + dp[index - 2], dp[index - 1]);
        return dp[index];
    }
}
