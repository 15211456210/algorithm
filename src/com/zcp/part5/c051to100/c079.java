package com.zcp.part5.c051to100;

/**
 * @author ZCP
 * @title: c079
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/word-search/submissions/
 * @date 2022/8/30 15:32
 */
public class c079 {

    public boolean exist(char[][] board, String word) {
        char[] wordc = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (fun(board, wordc, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean fun(char[][] board, char[] word, int i, int j, int index) {
        if (index >= word.length) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }
        if (board[i][j] != word[index]) {
            return false;
        }
        // 当前位置匹配
        char tmp = board[i][j];
        board[i][j] = 0;

        boolean r = fun(board, word, i + 1, j, index + 1) || fun(board, word, i, j + 1, index + 1) || fun(board, word, i - 1, j, index + 1)
                || fun(board, word, i, j - 1, index + 1);
        board[i][j] = tmp;

        return r;
    }
}
