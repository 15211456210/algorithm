package com.zcp.part5.c051to100;

/**
 * @author ZCP
 * @title: c074
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/search-a-2d-matrix/submissions/
 * @date 2022/8/30 9:50
 */
public class c074 {


    public boolean searchMatrix(int[][] matrix, int target) {
        int i = 0;
        int j = matrix[0].length - 1;

        while (i < matrix.length && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            }
            if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }

        return false;
    }
}
