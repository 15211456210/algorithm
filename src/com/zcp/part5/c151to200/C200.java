package com.zcp.part5.c151to200;

import java.util.ArrayList;

/**
 * @author ZCP
 * @title: C200
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/number-of-islands/submissions/
 * @date 2022/9/15 14:30
 */
public class C200 {

    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length < 1) {
            return 0;
        }
        ArrayUniunSet uniunSet = new ArrayUniunSet(grid);
        if (grid[0][0] == '1') {
            uniunSet.sizes[0] = 1;
        }
        for (int i = 1; i < grid[0].length; i++) {
            //先处理第一行
            if (grid[0][i] == '1') {
                uniunSet.uniun(0, i - 1, 0, i);
            }
        }
        for (int i = 1; i < grid.length; i++) {
            //先处理第一列
            if (grid[i][0] == '1') {
                uniunSet.uniun(i - 1, 0, i, 0);
            }
        }
        //处理剩余区域
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    uniunSet.uniun(i - 1, j, i, j);
                    uniunSet.uniun(i, j - 1, i, j);
                }
            }
        }
        return uniunSet.sets();
    }

    public static class ArrayUniunSet {

        private int[] parents;

        private int[] sizes;

        private int rowSize;

        private int colSize;

        public ArrayUniunSet(char[][] grid) {
            rowSize = grid.length;
            colSize = grid[0].length;
            parents = new int[rowSize * colSize];
            sizes = new int[rowSize * colSize];
            for (int i = 0; i < parents.length; i++) {
                parents[i] = i;
            }
        }

        public void uniun(int ai, int aj, int bi, int bj) {
            int aIndex = ai * colSize + aj;
            int bIndex = bi * colSize + bj;
            if (sizes[bIndex] == 0) {
                sizes[bIndex] = 1;//在此处初始化大小，遍历到过
            }
            if (sizes[aIndex] == 0) {
                return;
            }

            int aHead = findHead(aIndex);
            int bHead = findHead(bIndex);
            if (aHead == bHead) {
                return;
            }
            if (sizes[aHead] > sizes[bHead]) {
                parents[bHead] = aHead;
                sizes[aHead] = sizes[aHead] + sizes[bHead];
            } else {
                parents[aHead] = bHead;
                sizes[bHead] = sizes[aHead] + sizes[bHead];
            }
        }

        public int sets() {
            int sets = 0;
            for (int i = 0; i < parents.length; i++) {
                if (parents[i] == i && sizes[i] > 0) {
                    //代表节点 且 大小大于0
                    sets++;
                }
            }
            return sets;
        }

        public int findHead(int index) {
            ArrayList<Integer> tempList = new ArrayList<>();
            while (parents[index] != index) {
                tempList.add(index);
                index = parents[index];
            }
            Integer finalIndex = index;
            tempList.forEach(item -> {
                parents[item] = finalIndex;
            });
            return index;
        }
    }
}
