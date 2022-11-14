package com.zcp.part5.c301to350;

/**
 * @author ZCP
 * @title: C304
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/range-sum-query-2d-immutable/
 * @date 2022/9/27 14:17
 */
public class C304 {

    class NumMatrix {
        int[][] preSum;

        public NumMatrix(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            preSum = new int[m + 1][n + 1];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    preSum[i + 1][j + 1] = preSum[i + 1][j] + preSum[i][j + 1] - preSum[i][j] + matrix[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return preSum[row2 + 1][col2 + 1] + preSum[row1][col1] - preSum[row1][col2 + 1] - preSum[row2 + 1][col1];
        }
    }

    public static void main(String[] args) {


    }

}
