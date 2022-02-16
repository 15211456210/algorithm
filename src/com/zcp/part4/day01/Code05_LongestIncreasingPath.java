package com.zcp.part4.day01;

/**
 * https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/
 */
public class Code05_LongestIncreasingPath {


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


    public static int longestIncreasingPath1(int[][] matrix) {
        int ans = 0;
        int N = matrix.length;
        int M = matrix[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = Math.max(ans, process1(matrix, i, j));
            }
        }
        return ans;
    }

    // 从m[i][j]开始走，走出来的最长递增链，返回！
    public static int process1(int[][] m, int i, int j) {
        int up = i > 0 && m[i][j] < m[i - 1][j] ? process1(m, i - 1, j) : 0;
        int down = i < (m.length - 1) && m[i][j] < m[i + 1][j] ? process1(m, i + 1, j) : 0;
        int left = j > 0 && m[i][j] < m[i][j - 1] ? process1(m, i, j - 1) : 0;
        int right = j < (m[0].length - 1) && m[i][j] < m[i][j + 1] ? process1(m, i, j + 1) : 0;
        return Math.max(Math.max(up, down), Math.max(left, right)) + 1;
    }

    public static int longestIncreasingPath2(int[][] matrix) {
        int ans = 0;
        int N = matrix.length;
        int M = matrix[0].length;
        int[][] dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = Math.max(ans, process2(matrix, i, j, dp));
            }
        }
        return ans;
    }

    // 从m[i][j]开始走，走出来的最长递增链，返回！
    public static int process2(int[][] m, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        // (i,j)不越界
        int up = i > 0 && m[i][j] < m[i - 1][j] ? process2(m, i - 1, j, dp) : 0;
        int down = i < (m.length - 1) && m[i][j] < m[i + 1][j] ? process2(m, i + 1, j, dp) : 0;
        int left = j > 0 && m[i][j] < m[i][j - 1] ? process2(m, i, j - 1, dp) : 0;
        int right = j < (m[0].length - 1) && m[i][j] < m[i][j + 1] ? process2(m, i, j + 1, dp) : 0;
        int ans = Math.max(Math.max(up, down), Math.max(left, right)) + 1;
        dp[i][j] = ans;
        return ans;
    }

}