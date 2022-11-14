package com.zcp.part5.c001to050;

/**
 * @author ZCP
 * @title: c048
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/rotate-image/submissions/
 * @date 2022/8/28 15:54
 */
public class c048 {

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i <= n / 2; i++) {
            rotate(matrix, i, n - i * 2);
        }
    }

    public void rotate(int[][] matrix, int begin, int len) {
        for (int i = 0; i < len - 1; i++) {
            int candicate = matrix[begin][begin + i];
            int tmp = matrix[begin + i][begin + len - 1];
            matrix[begin + i][begin + len - 1] = candicate;
            candicate = tmp;
            tmp = matrix[begin + len - 1][begin + len - 1 - i];
            matrix[begin + len - 1][begin + len - 1 - i] = candicate;
            candicate = tmp;
            tmp = matrix[begin + len - 1 - i][begin];
            matrix[begin + len - 1 - i][begin] = candicate;
            matrix[begin][begin + i] = tmp;
        }
    }
}
