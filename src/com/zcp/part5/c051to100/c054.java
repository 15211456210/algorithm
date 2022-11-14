package com.zcp.part5.c051to100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZCP
 * @title: c054
 * @projectName algorithm
 * @description: https://leetcode.com/problems/spiral-matrix/submissions/
 * @date 2022/8/28 16:33
 */
public class c054 {

    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] direct = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        boolean[][] path = new boolean[m][n];
        int i = 0;
        int j = 0;
        List<Integer> list = new ArrayList<>();
        int directIdx = 0;
        for (; ; ) {
            list.add(matrix[i][j]);
            path[i][j] = true;
            int nexti = i + direct[directIdx][0];
            int nextj = j + direct[directIdx][1];
            if (nexti < 0 || nexti >= m || nextj < 0 || nextj >= n || path[nexti][nextj]) {
                directIdx = directIdx == 3 ? 0 : directIdx + 1;
                nexti = i + direct[directIdx][0];
                nextj = j + direct[directIdx][1];
                if (nexti < 0 || nexti >= m || nextj < 0 || nextj >= n || path[nexti][nextj]) {
                    break;
                }
            }
            i = nexti;
            j = nextj;
        }
        return list;
    }
}
