package com.zcp.part5.c001to050;

import java.util.Arrays;

/**
 * @author ZCP
 * @title: c037
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/sudoku-solver/
 * @date 2022/8/26 17:03
 */
public class c037 {

    static int[] row = new int[9];
    static int[] col = new int[9];
    static int[] box = new int[9];

    public void solveSudoku(char[][] board) {
        Arrays.fill(row, 0);
        Arrays.fill(col, 0);
        Arrays.fill(box, 0);
        init(board);
        process(board, 0, 0);
    }

    public boolean process(char[][] board, int i, int j) {
        if (i >= 9) {
            return true;
        }
        int boxIdx;
        if (board[i][j] == '.') {
            boxIdx = (i / 3) * 3 + (j / 3);
            for (int num = 9; num > 0; num--) {
                if ((((row[i] >> num) | (col[j] >> num) | (box[boxIdx] >> num)) & 1) == 0) {
                    board[i][j] = (char) ('0' + num);
                    row[i] |= (1 << num);
                    col[j] |= (1 << num);
                    box[boxIdx] |= (1 << num);
                    boolean correct = process(board, j == 8 ? i + 1 : i, j == 8 ? 0 : j + 1);
                    if (correct) {
                        return true;
                    } else {
                        board[i][j] = '.';
                        row[i] &= ~(1 << num);
                        col[j] &= ~(1 << num);
                        box[boxIdx] &= ~(1 << num);
                    }
                }
            }
            return false;
        } else {
            return process(board, j == 8 ? i + 1 : i, j == 8 ? 0 : j + 1);
        }

    }


    private void init(char[][] board) {
        int num;
        int boxIdx;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                num = board[i][j] - '0';
                row[i] |= (1 << num);
                col[j] |= (1 << num);
                boxIdx = (i / 3) * 3 + (j / 3);
                box[boxIdx] |= (1 << num);
            }
        }
    }
}
