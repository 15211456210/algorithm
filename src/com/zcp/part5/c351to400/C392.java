package com.zcp.part5.c351to400;

/**
 * @author ZCP
 * @title: C392
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/is-subsequence/description/
 * @date 2022/11/11 10:26
 */
public class C392 {

    public boolean isSubsequence(String s, String t) {

        if (s.length() > t.length()) {
            return false;
        }
        if (s.equals("")) {
            return true;
        }

        boolean[][] dp = new boolean[s.length()][t.length()];
        dp[0][0] = s.charAt(0) == t.charAt(0);

        for (int j = 1; j < dp[0].length; j++) {
            dp[0][j] = dp[0][j - 1] || (s.charAt(0) == t.charAt(j));
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = i; j < dp[0].length; j++) {
                dp[i][j] = s.charAt(i) == t.charAt(j) ? dp[i - 1][j - 1] : dp[i][j - 1];
            }
        }

        for (int j = 0; j < dp[0].length; j++) {
            if (dp[dp.length - 1][j]) {
                return true;
            }
        }

        return false;

    }
}
