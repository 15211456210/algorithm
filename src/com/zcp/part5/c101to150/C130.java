package com.zcp.part5.c101to150;

/**
 * @author ZCP
 * @title: C130
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/surrounded-regions/
 * @date 2022/9/5 18:37
 */
public class C130 {

    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = n; j > 0; j--) {
                if (infect(board, i, j, '1')) {
                    infect(board, i, j, 'X');
                } else {
                    infect(board, i, j, 'O');
                }
            }
        }

    }

    public boolean infect(char[][] board, int i, int j, char change) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }
        if (change != '1') {
            if (board[i][j] == change || board[i][j] == 'X') {
                return true;
            } else if (board[i][j] == '1') {
                board[i][j] = change;
            }

        } else {
            if (board[i][j] == '1' || board[i][j] == 'X') {
                return true;
            } else {
                board[i][j] = '1';
            }

        }
        return infect(board, i + 1, j, change) && infect(board, i - 1, j, change) && infect(board, i, j + 1, change) && infect(board, i, j - 1, change);
    }
}
