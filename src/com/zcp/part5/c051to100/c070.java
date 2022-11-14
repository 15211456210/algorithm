package com.zcp.part5.c051to100;

/**
 * @author ZCP
 * @title: c070
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/climbing-stairs/submissions/
 * @date 2022/8/30 8:43
 */
public class c070 {

    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[] dp = new int[n + 1];

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }
}
