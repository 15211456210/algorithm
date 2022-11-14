package com.zcp.part5.c001to050;

/**
 * @author ZCP
 * @title: c050
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/powx-n/submissions/
 * @date 2022/8/28 15:56
 */
public class c050 {

    static double[] dp = new double[32];

    public double myPow(double x, int n) {
        if (x == 1d) {
            return 1d;
        }
        init(x);
        boolean isNegative = ((n >> 31) & 1) == 1;
        double ans = 1;
        long nn = isNegative ? -(long) n : (long) n;
        for (int i = 0; i <= 32; i++) {
            if (((nn >> i) & 1) == 1) {
                ans *= dp[i];
            }
        }
        return isNegative ? 1 / ans : ans;
    }

    private void init(double x) {
        dp[0] = x;
        for (int i = 1; i < 32; i++) {
            dp[i] = dp[i - 1] * dp[i - 1];
        }
    }
}
