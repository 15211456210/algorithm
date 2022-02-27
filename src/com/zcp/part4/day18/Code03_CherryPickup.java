package com.zcp.part4.day18;

// 牛客的测试链接：
// https://www.nowcoder.com/questionTerminal/8ecfe02124674e908b2aae65aad4efdf
// 把如下的全部代码拷贝进java编辑器
// 把文件大类名字改成Main，可以直接通过

import java.util.Scanner;

public class Code03_CherryPickup {


    /**
     * 思路：
     * 动态规划+记忆化搜索
     *
     * @param grid
     * @return
     */
    public static int cherryPickup2(int[][] grid) {
        if (grid == null || grid.length < 1) {
            return 0;
        }
        int[][][] dp = new int[grid.length][grid[0].length][grid.length];

        return process2(grid, 0, 0, 0, dp);
    }

    /**
     * 返回A(i1,j1),B(i2,j2)两个点出发到右下角能获取的最大收益
     *
     * @param grid
     * @param i1
     * @param j1
     * @param i2
     * @param dp
     * @return
     */
    public static int process2(int[][] grid, int i1, int j1, int i2, int[][][] dp) {


        int n = grid.length;
        int m = grid[0].length;

        int j2 = i1 + j1 - i2;

        if (i1 > n - 1 || j1 > m - 1 || i2 > n - 1 || j2 > m - 1) {
            return 0;
        }

        if (dp[i1][j1][i2] != 0) {
            return dp[i1][j1][i2];
        }

        if (i1 == n - 1 && j1 == m - 1) {
            dp[i1][j1][i2] = grid[i1][j1];
            return grid[i1][j1];
        }

        //p1：A 右 B 右
        //p2：A 右 B 下
        //p3：A 下 B 右
        //p4：A 下 B 下
        int bestNext = process2(grid, i1, j1 + 1, i2, dp);
        bestNext = Math.max(bestNext, process2(grid, i1, j1 + 1, i2 + 1, dp));
        bestNext = Math.max(bestNext, process2(grid, i1 + 1, j1, i2, dp));
        bestNext = Math.max(bestNext, process2(grid, i1 + 1, j1, i2 + 1, dp));

        int cur = 0;
        if (i1 == i2 && j1 == j2) {
            cur = grid[i1][j1];
        } else {
            cur = grid[i1][j1] + grid[i2][j2];
        }
        dp[i1][j1][i2] = cur + bestNext;
        return cur + bestNext;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
//        int ans = cherryPickup(matrix);
        int ans = cherryPickup2(matrix);
        System.out.println(ans);
        sc.close();
    }

    public static int cherryPickup(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;
        int[][][] dp = new int[N][M][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < N; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }
        int ans = process(grid, 0, 0, 0, dp);
        return ans < 0 ? 0 : ans;
    }

    public static int process(int[][] grid, int x1, int y1, int x2, int[][][] dp) {
        if (x1 == grid.length || y1 == grid[0].length || x2 == grid.length || x1 + y1 - x2 == grid[0].length) {
            return Integer.MIN_VALUE;
        }
        if (dp[x1][y1][x2] != Integer.MIN_VALUE) {
            return dp[x1][y1][x2];
        }
        if (x1 == grid.length - 1 && y1 == grid[0].length - 1) {
            dp[x1][y1][x2] = grid[x1][y1];
            return dp[x1][y1][x2];
        }
        int next = Integer.MIN_VALUE;
        next = Math.max(next, process(grid, x1 + 1, y1, x2 + 1, dp));
        next = Math.max(next, process(grid, x1 + 1, y1, x2, dp));
        next = Math.max(next, process(grid, x1, y1 + 1, x2, dp));
        next = Math.max(next, process(grid, x1, y1 + 1, x2 + 1, dp));
        if (grid[x1][y1] == -1 || grid[x2][x1 + y1 - x2] == -1 || next == -1) {
            dp[x1][y1][x2] = -1;
            return dp[x1][y1][x2];
        }
        if (x1 == x2) {
            dp[x1][y1][x2] = grid[x1][y1] + next;
            return dp[x1][y1][x2];
        }
        dp[x1][y1][x2] = grid[x1][y1] + grid[x2][x1 + y1 - x2] + next;
        return dp[x1][y1][x2];
    }

}