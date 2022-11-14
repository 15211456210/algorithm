package com.zcp.part5.c051to100;

/**
 * @author ZCP
 * @title: c063
 * @projectName algorithm
 * @description: https://leetcode.com/problems/unique-paths-ii/
 * @date 2022/8/29 10:54
 */
public class c063 {

//    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
//        int m = obstacleGrid.length;
//        int n = obstacleGrid[0].length;
//        return process(obstacleGrid, 0, 0);
//    }
//
//    public int process(int[][] obstacleGrid, int i, int j) {
//        if (i >= obstacleGrid.length || j >= obstacleGrid[0].length || obstacleGrid[i][j] == 1) {
//            return 0;
//        }
//
//        if (i == obstacleGrid.length - 1 && j == obstacleGrid[0].length - 1) {
//            return 1;
//        }
//
//        return process(obstacleGrid, i + 1, j) + process(obstacleGrid, i, j + 1);
//    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m + 1][n + 1];

        dp[m - 1][n - 1] = 1;
        boolean hasObstacle = false;
        for (int j = n - 1; j >= 0; j--) {
            if (obstacleGrid[m - 1][j] == 1) {
                hasObstacle = true;
            }
            dp[m - 1][j] = hasObstacle ? 0 : 1;
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }
                dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
            }
        }
        return dp[0][0];

    }

}
