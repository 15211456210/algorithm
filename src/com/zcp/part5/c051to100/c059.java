package com.zcp.part5.c051to100;

/**
 * @author ZCP
 * @title: c059
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/spiral-matrix-ii/submissions/
 * @date 2022/8/28 18:47
 */
public class c059 {
    static int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int i = 0, j = 0;
        int fillCnt = 1;
        int directionIndex = 0;
        while (fillCnt <= n * n) {
            matrix[i][j] = fillCnt++;
            int nextI = i + directions[directionIndex][0];
            int nextJ = j + directions[directionIndex][1];
            if (nextI < 0 || nextI == n || nextJ < 0 || nextJ == n || matrix[nextI][nextJ] != 0) {
                directionIndex = (directionIndex + 1) % 4;
            }
            i = i + directions[directionIndex][0];
            j = j + directions[directionIndex][1];
        }

        return matrix;

    }
}
