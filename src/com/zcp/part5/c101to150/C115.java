package com.zcp.part5.c101to150;

/**
 * @author ZCP
 * @title: C115
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/distinct-subsequences/
 * @date 2022/9/1 16:24
 */
public class C115 {

    public static int numDistinct(String S, String T) {
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        int[] dp = new int[t.length + 1];
        dp[0] = 1;
        // for (int j = 1; j <= t.length; j++) {
        //     dp[j] = 0;
        // }
        for (int i = 1; i <= s.length; i++) {
            for (int j = t.length; j >= 1; j--) {
                dp[j] += s[i - 1] == t[j - 1] ? dp[j - 1] : 0;
            }
        }
        return dp[t.length];
    }
}
