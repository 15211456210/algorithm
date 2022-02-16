package com.zcp.part3.matrix;

/**
 * @description: 面试题 01.07. 旋转矩阵
 * https://leetcode-cn.com/problems/rotate-matrix-lcci/
 * @projectName:algorithm
 * @see:com.zcp.part3.matrix
 * @author:ZCP
 * @createTime:2021/11/25
 * @version:1.0
 */
public class Rotate {

    public void rotate(int[][] matrix) {
        if (matrix != null && matrix.length != matrix[0].length) {
            return;
        }
        for (int i = 0, j = matrix.length - 1; i < j; i++, j--) {
            process(i, i, j, j, matrix);
        }
    }

    /**
     * 根据左上角坐标（a,b）右下角坐标（c,d）旋转
     *
     * @param a
     * @param b
     * @param c
     * @param d
     * @param matrix
     */
    private void process(int a, int b, int c, int d, int[][] matrix) {
        for (int i = 0; i < d - b; i++) {
            int temp = matrix[a][b + i];
            matrix[a][b + i] = matrix[c - i][b];
            matrix[c - i][b] = matrix[c][d - i];
            matrix[c][d - i] = matrix[a + i][d];
            matrix[a + i][d] = temp;
        }
    }

    public static void main(String[] args) {
        Rotate rotate = new Rotate();
        rotate.rotate(new int[][]{
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        });
    }
}
