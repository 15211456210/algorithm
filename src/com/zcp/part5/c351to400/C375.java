package com.zcp.part5.c351to400;

/**
 * @author ZCP
 * @title: C375
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/guess-number-higher-or-lower-ii/description/
 * @date 2022/10/26 17:06
 */
public class C375 {

    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        return dfs(dp, 1, n);
    }

    private int dfs(int[][] dp, int l, int r) {
        if (l >= r) {
            return 0;
        }
        if (dp[l][r] != 0) {
            return dp[l][r];
        }
        int res = Integer.MAX_VALUE;
        for (int i = l; i <= r; ++i) {
            int cost = i + Math.max(dfs(dp, l, i - 1), dfs(dp, i + 1, r));
            res = Math.min(res, cost);
        }
        dp[l][r] = res;
        return res;
    }
}
