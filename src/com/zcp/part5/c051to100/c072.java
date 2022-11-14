package com.zcp.part5.c051to100;

/**
 * @author ZCP
 * @title: c072
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/edit-distance/submissions/
 * @date 2022/8/30 9:23
 */
public class c072 {

    public int minDistance(String word1, String word2) {
        return minCost3(word1, word2, 1, 1, 1);
    }

    /**
     * 思路：
     * 动态规划，样本对应模型
     * dp[i][j]的含义：
     * s1[0...(i-1)]字符串 -> s2[0...(j-1)]字符串锁需花费的最小编辑代价
     *
     * @param s1
     * @param s2
     * @param i  插入代价
     * @param d  删除代价
     * @param u  替换代价
     * @return
     */
    public static int minCost3(String s1, String s2, int i, int d, int u) {
        if (s1 == null || s2 == null) {
            return 0;
        }
        int N = s1.length();
        int M = s2.length();

        int[][] dp = new int[N + 1][M + 1];
        for (int x = 0; x <= N; x++) {
            dp[x][0] = x * d;
        }
        for (int y = 0; y <= M; y++) {
            dp[0][y] = y * i;
        }
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= M; y++) {
                int ans = dp[x - 1][y] + d;
                ans = Math.min(ans, dp[x][y - 1] + i);
                if (s1.charAt(x - 1) == s2.charAt(y - 1)) {
                    ans = Math.min(ans, dp[x - 1][y - 1]);
                } else {
                    ans = Math.min(ans, dp[x - 1][y - 1] + u);
                }
                dp[x][y] = ans;
            }
        }
        return dp[N][M];
    }
}
