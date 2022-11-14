package com.zcp.part5.c001to050;

/**
 * @author ZCP
 * @title: c036
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/valid-sudoku/
 * @date 2022/8/26 16:47
 */
public class c036 {

    public boolean isValidSudoku(char[][] board) {
        int[] row = new int[9];
        int[] col = new int[9];
        int[] box = new int[9];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int serialNumber = board[i][j] - '0';
                if (((row[i] >>> serialNumber) & 1) == 1) {
                    return false;
                } else {
                    row[i] |= (1 << serialNumber);
                }

                if (((col[j] >>> serialNumber) & 1) == 1) {
                    return false;
                } else {
                    col[j] |= (1 << serialNumber);
                }
                int boxIdx = (i / 3) * 3 + (j / 3);
                if (((box[boxIdx] >>> serialNumber) & 1) == 1) {
                    return false;
                } else {
                    box[boxIdx] |= (1 << serialNumber);
                }

            }
        }

        return true;


    }
}
