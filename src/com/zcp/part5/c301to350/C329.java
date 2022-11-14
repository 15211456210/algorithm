package com.zcp.part5.c301to350;

/**
 * @author ZCP
 * @title: C329
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/longest-increasing-path-in-a-matrix/description/
 * @date 2022/10/17 16:30
 */
public class C329 {

    /**
     * 思路：
     * 动态规划，傻缓存
     *
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 0) {
            return 0;
        }
        int N = matrix.length;
        int M = matrix[0].length;
        int[][] dp = new int[N][M];
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = Math.max(ans, process(matrix, i, j, dp));
            }
        }
        return ans;
    }

    /**
     * 从matrix[i][j]点出发最长的递增链长度
     *
     * @param matrix
     * @param i
     * @param j
     * @return
     */
    public int process(int[][] matrix, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int up = (i > 0 && matrix[i - 1][j] > matrix[i][j]) ? process(matrix, i - 1, j, dp) : 0;
        int down = (i < matrix.length - 1 && matrix[i + 1][j] > matrix[i][j]) ? process(matrix, i + 1, j, dp) : 0;
        int left = (j > 0 && matrix[i][j - 1] > matrix[i][j]) ? process(matrix, i, j - 1, dp) : 0;
        int right = (j < matrix[0].length - 1 && matrix[i][j + 1] > matrix[i][j]) ? process(matrix, i, j + 1, dp) : 0;
        int ans = Math.max(Math.max(up, down), Math.max(left, right)) + 1;

        dp[i][j] = ans;
        return ans;
    }

}
