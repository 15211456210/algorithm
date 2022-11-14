package com.zcp.part5.c151to200;

/**
 * @author ZCP
 * @title: C174
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/dungeon-game/
 * @date 2022/9/9 10:12
 */
public class C174 {

    public int calculateMinimumHP(int[][] dungeon) {
//        return minCost(dungeon, 0, 0);
        int n = dungeon.length;
        int m = dungeon[0].length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][m] = Integer.MAX_VALUE;
        }
        for (int j = 0; j <= m; j++) {
            dp[n][j] = Integer.MAX_VALUE;
        }

        dp[n - 1][m - 1] = dungeon[n - 1][m - 1] >= 0 ? 1 : -dungeon[n - 1][m - 1] + 1;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (i == n - 1 && j == m - 1) {
                    dp[i][j] = dungeon[i][j] >= 0 ? 1 : -dungeon[i][j] + 1;
                    continue;
                }
                int min = Math.min(dp[i + 1][j], dp[i][j + 1]);
                dp[i][j] = dungeon[i][j] >= min ? 1 : min - dungeon[i][j];
            }
        }

        return dp[0][0];
    }

    /**
     * 从[i,j]位置开始走到终点 最少需要多少血量
     *
     * @param dungeon
     * @param i
     * @param j
     * @return
     */
    public int minCost(int[][] dungeon, int i, int j) {
        if (i >= dungeon.length || j >= dungeon[0].length) {
            return Integer.MAX_VALUE;
        }
        if (i == dungeon.length - 1 && j == dungeon[0].length - 1) {
            return dungeon[i][j] >= 0 ? 1 : -dungeon[i][j] + 1;
        }

        int min = Math.min(minCost(dungeon, i + 1, j), minCost(dungeon, i, j + 1));

        return dungeon[i][j] >= min ? 1 : min - dungeon[i][j];
    }

    public static void main(String[] args) {
        System.out.println(new C174().calculateMinimumHP(new int[][]{{100}}));

    }
}
