package com.zcp.part5.c201to250;

/**
 * @author ZCP
 * @title: C240
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/search-a-2d-matrix-ii/submissions/
 * @date 2022/9/22 9:29
 */
public class C240 {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1) {
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        //从右上角开始走
        int posi = 0;
        int posj = n - 1;
        while (posi < m && posj >= 0) {
            if (matrix[posi][posj] == target) {
                return true;
            } else if (matrix[posi][posj] < target) {
                posi++;
            } else {
                //matrix[posi][posj] > target
                posj--;
            }
        }
        return false;
    }
}
