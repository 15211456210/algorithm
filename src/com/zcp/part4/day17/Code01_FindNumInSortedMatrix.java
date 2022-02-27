package com.zcp.part4.day17;

public class Code01_FindNumInSortedMatrix {


    /**
     * 思路：
     * 从某个点开始走，逐步筛选一些无用的数字
     *
     * @param matrix
     * @param target
     * @return
     */
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
            if (matrix[posi][posj] == target){
                return true;
            }else if(matrix[posi][posj] < target){
                posi++;
            }else{
                //matrix[posi][posj] > target
                posj--;
            }
        }
        return false;
    }


    public static boolean isContains(int[][] matrix, int K) {
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col > -1) {
            if (matrix[row][col] == K) {
                return true;
            } else if (matrix[row][col] > K) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0, 1, 2, 3, 4, 5, 6}, // 0
                {10, 12, 13, 15, 16, 17, 18}, // 1
                {23, 24, 25, 26, 27, 28, 29}, // 2
                {44, 45, 46, 47, 48, 49, 50}, // 3
                {65, 66, 67, 68, 69, 70, 71}, // 4
                {96, 97, 98, 99, 100, 111, 122}, // 5
                {166, 176, 186, 187, 190, 195, 200}, // 6
                {233, 243, 321, 341, 356, 370, 380} // 7
        };
        int K = 233;
        System.out.println(isContains(matrix, K));
    }

}
