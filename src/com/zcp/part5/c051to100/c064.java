package com.zcp.part5.c051to100;

/**
 * @author ZCP
 * @title: c064
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/minimum-path-sum/
 * @date 2022/8/29 11:10
 */
public class c064 {


    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dist = new int[m][n];

        dist[m - 1][n - 1] = grid[m - 1][n - 1];
        for (int i = m - 2; i >= 0; i--) {
            dist[i][n - 1] = grid[i][n - 1] + dist[i + 1][n - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            dist[m - 1][i] = grid[m - 1][i] + dist[m - 1][i + 1];
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dist[i][j] = grid[i][j] + Math.min(dist[i + 1][j], dist[i][j + 1]);
            }
        }
        return dist[0][0];
    }
}
