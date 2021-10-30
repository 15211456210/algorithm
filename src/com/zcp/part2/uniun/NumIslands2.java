package com.zcp.part2.uniun;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/14 14:31
 * @description：岛屿问题2 动态添加  https://leetcode-cn.com/problems/number-of-islands-ii/
 * @version:
 */
public class NumIslands2 {

    /**
     * 使用并查集解决
     * 用一个int[M*N]来表示矩阵 grid[i][j] 就对应 下标 i*M+j 的位置
     *
     * @return
     */
    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        if (m < 1 || n < 1) {
            return null;
        }
        ArrayList<Integer> ans = new ArrayList<>();
        ArrayUniunSet uniunSet = new ArrayUniunSet(m, n);
        for (int i = 0; i < positions.length; i++) {
            uniunSet.add(positions[i][0], positions[i][1]);
            ans.add(uniunSet.sets());
        }
        return ans;
    }

    public static class ArrayUniunSet {

        private int[] parents;

        private int[] sizes;

        private int rowSize;

        private int colSize;

        private int sets;

        public ArrayUniunSet(int m, int n) {
            rowSize = m;
            colSize = n;
            sets = 0;
            parents = new int[rowSize * colSize];
            sizes = new int[rowSize * colSize];
            for (int i = 0; i < parents.length; i++) {
                parents[i] = i;
            }
        }

        /**
         * @param ai 当前节点行号
         * @param aj 当前节点列号
         * @param bi
         * @param bj
         */
        public void uniun(int ai, int aj, int bi, int bj) {
            int aIndex = ai * colSize + aj;
            if (sizes[aIndex] == 0) {
                sizes[aIndex] = 1;//在此处初始化大小，遍历到过
                sets++;
            }
            if (ai < 0 || ai + 1 > rowSize || aj < 0 || aj + 1 > colSize || bi < 0 || bi + 1 > rowSize || bj < 0 || bj + 1 > colSize) {
                return;
            }
            int bIndex = bi * colSize + bj;

            if (sizes[bIndex] == 0) {
                return;
            }

            int aHead = findHead(aIndex);
            int bHead = findHead(bIndex);
            if (aHead == bHead) {
                return;
            }
            sets--;
            if (sizes[aHead] > sizes[bHead]) {
                parents[bHead] = aHead;
                sizes[aHead] = sizes[aHead] + sizes[bHead];
            } else {
                parents[aHead] = bHead;
                sizes[bHead] = sizes[aHead] + sizes[bHead];
            }
        }

        public int sets() {
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

        /**
         * 动态添加
         * 每次添加都向四个方向uniun
         *
         * @param r
         * @param c
         */
        public void add(int r, int c) {
            int index = r * colSize + c;
            if (sizes[index] > 0) {
                return;
            }
            uniun(r, c, r - 1, c);//上
            uniun(r, c, r, c - 1);//左
            uniun(r, c, r + 1, c);//下
            uniun(r, c, r, c + 1);//右
        }
    }

    public static void main(String[] args) {
        int[][] ints = {{0, 0}};
        System.out.println(numIslands2(1, 1, ints));
    }

}
