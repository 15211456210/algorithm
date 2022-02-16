package com.zcp.part3.matrix;

/**
 * @description: 剑指 Offer 29. 顺时针打印矩阵
 * https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/
 * @projectName:algorithm
 * @see:com.zcp.part3.matrix
 * @author:ZCP
 * @createTime:2021/11/25
 * @version:1.0
 */
public class SpiralOrder {

    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length < 0) {
            return new int[0];
        }
        int[] ans = new int[matrix.length * matrix[0].length];
        int index = 0;
        int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int maxRow = matrix.length;
        int maxCol = matrix[0].length;
        int x = 0, y = 0;
        int directIndex = 0;
        while (true) {
            ans[index++] = matrix[x][y];
            if (index >= ans.length) {
                break;
            }
            int nextX = x + direction[directIndex][0];
            int nextY = y + direction[directIndex][1];
            if ((directIndex == 0 && nextX == maxCol - nextY) ||
                    (directIndex == 1 && maxRow - nextX < maxCol - nextY) ||
                    (directIndex == 2 && maxRow - nextX - 1 > nextY) ||
                    (directIndex == 3 && nextX <= nextY)) {
                directIndex = (directIndex + 1) % 4;
            }
            x += direction[directIndex][0];
            y += direction[directIndex][1];
        }
        return ans;
    }

    public static void main(String[] args) {
//        [[1,2,3],[4,5,6],[7,8,9]]
        SpiralOrder spiralOrder = new SpiralOrder();
        spiralOrder.spiralOrder(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        });
    }
}
