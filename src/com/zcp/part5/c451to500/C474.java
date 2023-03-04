package com.zcp.part5.c451to500;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ZCP
 * @title: C474
 * @projectName algorithm
 * @description: https://leetcode.com/problems/ones-and-zeroes/
 * @date 2023/2/18 10:39
 */
public class C474 {
    public int findMaxForm(String[] strs, int m, int n) {
        Arrays.sort(strs, Comparator.comparingInt(String::length));
        int[][][] dp = new int[strs.length + 1][m + 1][n + 1];
        for (int i = 0; i <= strs.length; i++) {
            for (int mm = 0; mm <= m; mm++) {
                for (int nn = 0; nn <= n; nn++) {
                    dp[i][mm][nn] = -1;
                }
            }
        }
        return dfs(strs, 0, m, n, dp);
    }

    private int dfs(String[] strs, int i, int m, int n, int[][][] dp) {

        if (i == strs.length || (m == 0 && n == 0)) {

            return 0;
        }
        if (dp[i][m][n] != -1) {
            return dp[i][m][n];
        }
        int max = 0;
        // p1 不选
        max = dfs(strs, i + 1, m, n, dp);
        int cm = 0, cn = 0;
        // 选择
        for (char c : strs[i].toCharArray()) {
            cm += (c == '0' ? 1 : 0);
            cn += (c == '1' ? 1 : 0);
        }
        if (cm <= m && cn <= n) {
            max = Math.max(max, 1 + dfs(strs, i + 1, m - cm, n - cn, dp));
        }
        dp[i][m][n] = max;
        return max;
    }
}
