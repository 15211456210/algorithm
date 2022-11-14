package com.zcp.part5.c051to100;

import java.util.Arrays;

/**
 * @author ZCP
 * @title: c073
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/set-matrix-zeroes/submissions/
 * @date 2022/8/30 9:24
 */
public class c073 {

//    static boolean[] row = new boolean[200];
//    static boolean[] column = new boolean[200];
//
//    public void setZeroes(int[][] matrix) {
//        Arrays.fill(row, false);
//        Arrays.fill(column, false);
//
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[0].length; j++) {
//                if (matrix[i][j] == 0) {
//                    row[i] = true;
//                    column[j] = true;
//                }
//            }
//        }
//
//        for (int i = 0; i < row.length; i++) {
//            if (row[i]) {
//                Arrays.fill(matrix[i], 0);
//            }
//        }
//
//        for (int i = 0; i < column.length; i++) {
//            if (column[i]) {
//                for (int r = 0; r < matrix.length; r++) {
//                    matrix[r][i] = 0;
//                }
//            }
//        }
//
//    }


    static int flag = 254925;
    public void setZeroes(int[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    infect(matrix, i, j);
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == flag) {
                    matrix[i][j] = 0;
                }
            }
        }


    }

    private void infect(int[][] matrix, int i, int j) {

        for (int k = i - 1; k >= 0 && matrix[k][j] != 0; k--) {
            matrix[k][j] = flag;
        }

        for (int k = i + 1; k < matrix.length && matrix[k][j] != 0; k++) {
            matrix[k][j] = flag;
        }

        for (int k = j - 1; k >= 0 && matrix[i][k] != 0; k--) {
            matrix[i][k] = flag;
        }

        for (int k = j + 1; k < matrix[0].length && matrix[i][k] != 0; k++) {
            matrix[i][k] = flag;
        }
    }

}
