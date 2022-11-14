package com.zcp.part5.c251to300;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZCP
 * @title: C289
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/game-of-life/submissions/
 * @date 2022/9/26 16:43
 */
public class C289 {
    class Node {
        int i, j, v;

        public Node(int i, int j, int v) {
            this.i = i;
            this.j = j;
            this.v = v;
        }
    }

    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        List<Node> chain = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Integer nextState = getNextState(board, m, n, i, j);
                if (board[i][j] != nextState) {
                    chain.add(new Node(i, j, nextState));
                }
            }
        }

        chain.forEach(node -> {
            board[node.i][node.j] = node.v;
        });

    }

    private Integer getNextState(int[][] board, int m, int n, int i, int j) {

        int oneCnt = 0;
        oneCnt += ((i - 1) >= 0 && (j - 1) >= 0 && board[i - 1][j - 1] == 1) ? 1 : 0;
        oneCnt += ((i - 1) >= 0 && board[i - 1][j] == 1) ? 1 : 0;
        oneCnt += ((i - 1) >= 0 && (j + 1) < n && board[i - 1][j + 1] == 1) ? 1 : 0;
        oneCnt += ((j - 1) >= 0 && board[i][j - 1] == 1) ? 1 : 0;
        oneCnt += ((j + 1) < n && board[i][j + 1] == 1) ? 1 : 0;
        oneCnt += ((i + 1) < m && (j - 1) >= 0 && board[i + 1][j - 1] == 1) ? 1 : 0;
        oneCnt += ((i + 1) < m && board[i + 1][j] == 1) ? 1 : 0;
        oneCnt += ((i + 1) < m && (j + 1) < n && board[i + 1][j + 1] == 1) ? 1 : 0;

        if (oneCnt < 2) {
            return 0;
        } else if (oneCnt < 3) {
            return board[i][j];
        } else if (oneCnt == 3) {
            return 1;
        } else {
            return 0;
        }


    }
}
