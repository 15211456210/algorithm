package com.zcp.part5.c151to200;

import java.util.Arrays;

/**
 * @author ZCP
 * @title: C198
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/house-robber/submissions/
 * @date 2022/9/15 14:28
 */
public class C198 {

    public static int rob(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            fun(nums, i, dp);
        }
        System.out.println(Arrays.toString(dp));
        return dp[nums.length - 1];
    }


    public static int fun(int[] nums, int index, int[] dp) {
        if (dp[index] != 0) {
            return dp[index];
        }
        if (index == 0) {
            dp[index] = nums[0];
            return dp[index];
        }
        if (index == 1) {
            dp[index] = Math.max(nums[0], nums[1]);
            return dp[index];
        }
        if (index == 2) {
            dp[index] = Math.max(nums[0] + nums[2], nums[1]);
            return dp[index];
        }
        dp[index] = Math.max(nums[index] + dp[index - 2], dp[index - 1]);
        return dp[index];
    }
}
