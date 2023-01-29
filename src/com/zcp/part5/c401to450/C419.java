package com.zcp.part5.c401to450;

/**
 * @author ZCP
 * @title: C419
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/battleships-in-a-board/description/
 * @date 2022/11/24 10:15
 */
public class C419 {

    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int countBattleships(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') {
                    ans++;
                    dfs(board, i, j);
                }
            }
        }
        return ans;
    }

    private void dfs(char[][] board, int i, int j) {
        if (board[i][j] == 'X') {
            board[i][j] = '0';
        }
        for (int[] dir : dirs) {
            int nexti = i + dir[0];
            int nextj = j + dir[1];
            if (nexti >= 0 && nexti < board.length && nextj >= 0 && nextj < board[0].length && board[nexti][nextj] == 'X') {
                dfs(board, nexti, nextj);
            }
        }
    }
}
