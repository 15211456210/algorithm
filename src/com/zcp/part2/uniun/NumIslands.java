package com.zcp.part2.uniun;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/14 10:56
 * @description：岛屿数量 https://leetcode-cn.com/problems/number-of-islands/
 * @version:
 */
public class NumIslands {

    /**
     * 解决方案1
     * 递归实现思路：
     * 遍历整个二维数组，每次到达一个‘1’ 将与其相连接的所有‘1’都改为‘2’（任意值都可以，能区分开来就OK），
     * 返回值为总共遇到‘1’的次数，即为岛屿数量
     *
     * @param grid
     * @return
     */
    public int solution1(char[][] grid) {
        if (grid == null || grid.length < 1) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    ans++;
                    infect(grid, i, j);
                }
            }
        }
        return ans;
    }

    /**
     * 传染
     */
    public void infect(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = 2;
        //上下左右 去传染
        infect(grid, i - 1, j);
        infect(grid, i + 1, j);
        infect(grid, i, j - 1);
        infect(grid, i, j + 1);
    }

    /**
     * 使用并查集解决
     * 用一个int[M*N]来表示矩阵 grid[i][j] 就对应 下标 i*M+j 的位置
     *
     * @param grid
     * @return
     */
    public static int solution2(char[][] grid) {
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
                uniunSet.uniun(0, i - 1,0, i);
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

    public static void main(String[] args) {
        //[['1','1','1','1','0'],['1','1','0','1','0'],['1','1','0','0','0'],['0','0','0','0','0']]
        char[][] t = new char[][]{
                {'0', '1', '0'},
                {'1', '0', '1'},
                {'0', '1', '0'}
        };
        System.out.println(solution2(t));
    }

}
