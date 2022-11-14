package com.zcp.part5.c251to300;

/**
 * @author ZCP
 * @title: C279
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/perfect-squares/
 * @date 2022/9/25 12:12
 */
public class C279 {

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int sqrt = (int) Math.sqrt(i);
            int min = dp[i - 1] + 1;
            for (int k = 1; k * k <= i; k++) {
                min = Math.min(min, dp[i - k * k] + 1);
            }
            dp[i] = min;
        }
        return dp[n];
    }
}
