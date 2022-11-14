package com.zcp.part5.c001to050;

/**
 * @author ZCP
 * @title: c044
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/wildcard-matching/
 * @date 2022/8/27 16:36
 */
public class c044 {


    public boolean isMatch(String s, String p) {
        if ("".equals(p)) {
            return "".equals(s);
        }
        int[][] dp = new int[s.length() + 2][p.length() + 2];
        return match(s.toCharArray(), p.toCharArray(), 0, 0, dp);
    }

    public boolean match(char[] s, char[] p, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j] == 1;
        }
        if (j == p.length - 1) {
            if (p[j] == '*') {
                dp[i][j] = 1;
                return true;
            } else if (p[j] == '?') {
                dp[i][j] = i == s.length - 1 ? 1 : -1;
                return i == s.length - 1;
            } else {
                dp[i][j] = (i == s.length - 1 && s[i] == p[j]) ? 1 : -1;
                return dp[i][j] == 1;
            }

        }
        if (i > s.length) {
            dp[i][j] = -1;
            return false;
        }
        boolean res = false;
        if (p[j] == '?') {
            res = i < s.length && match(s, p, i + 1, j + 1, dp);
        } else if (p[j] == '*') {
            res = match(s, p, i, j + 1, dp) || match(s, p, i + 1, j, dp);
        } else {
            res = i < s.length && s[i] == p[j] && match(s, p, i + 1, j + 1, dp);
        }
        dp[i][j] = res ? 1 : -1;
        return res;
    }

}
