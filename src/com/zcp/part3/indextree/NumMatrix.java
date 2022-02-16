package com.zcp.part3.indextree;

import java.util.Arrays;

/**
 * @description: 二维IndexTree
 * @projectName:algorithm
 * @see:com.zcp.part2.indextree
 * @author:ZCP
 * @createTime:2021/10/30
 * @version:1.0
 */
public class NumMatrix {

    int[][] numMatrix;
    int[][] help;

    public NumMatrix(int[][] matrix) {
        numMatrix = matrix;
        printArray(numMatrix);
        build();

    }

    /**
     * 构建help数组
     */
    private void build() {
        System.out.println("build----");
        help = new int[numMatrix.length + 1][numMatrix[0].length + 1];
        int rowCnt = help.length;
        int columnCnt = help[0].length;
        for (int i = 1; i < rowCnt; i++) {
            for (int j = 1; j < columnCnt; j++) {
                int rowEdge = i ^ (i & (-i));
                int colEdge = j ^ (j & (-j));
                for (int curRow = i; curRow > rowEdge; curRow--) {
                    for (int curCol = j; curCol > colEdge; curCol--) {
                        help[i][j] += numMatrix[curRow - 1][curCol - 1];
                    }
                }
            }
        }
        printArray(help);
    }

    public void update(int row, int col, int val) {
        //算出和原先的差值
        int diff = val - numMatrix[row][col];
        numMatrix[row][col] = val;
        if (diff != 0) {
            int curRow = row + 1;
            //取help数组中每个会被影响的书加上diff偏差值
            while (curRow < help.length) {
                int curCol = col + 1;
                while (curCol < help[0].length) {
                    help[curRow][curCol] += diff;
                    curCol += curCol & (-curCol);//cur+二进制最右侧的1，目的为了进位
                }
                curRow += curRow & (-curRow);//cur+二进制最右侧的1，目的为了进位
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sum(row2 + 1, col2 + 1) + sum(row1, col1) - sum(row2 + 1, col1) - sum(row1, col2 + 1);
    }

    /**
     * 计算[0,0] - [row,col]范围内数的和
     *
     * @param row
     * @param col
     * @return
     */
    public int sum(int row, int col) {
        int curRow = row;
        int sum = 0;
        while (curRow != 0) {
            int curCol = col;
            while (curCol != 0) {
                sum += help[curRow][curCol];
                curCol = curCol ^ (curCol & (-curCol));//去掉二进制最右侧的1
            }
            curRow = curRow ^ (curRow & (-curRow));//去掉二进制最右侧的1
        }
        return sum;
    }


    public static void printArray(int[][] array) {
        for (int[] ints : array) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public static void main(String[] args) {
        NumMatrix numMatrix = new NumMatrix(new int[][]{{1,2}});
        numMatrix.update(0,0,3);
        printArray(numMatrix.help);
        System.out.println(numMatrix.sumRegion(0, 0, 0, 1));
        numMatrix.update(0,1,5);
        printArray(numMatrix.help);
        System.out.println(numMatrix.sumRegion(0, 0, 0, 1));

//        NumMatrix numMatrix = new NumMatrix(new int[][]{{2, 5, 6, 5, 4, 2}, {2, 3, 0, 3, 6, 2}, {9, 6, 2, 1, 4, 2}, {9, 6, 3, 2, 3, 2}});
//
//        System.out.println(numMatrix.sumRegion(1, 1, 2, 2));//11
//        numMatrix.update(1,1,2);
//        System.out.println(numMatrix.sumRegion(1, 1, 2, 2));//10
//        numMatrix.update(0,0,5);
//        System.out.println(numMatrix.sumRegion(1, 1, 2, 2));//10
    }
}
