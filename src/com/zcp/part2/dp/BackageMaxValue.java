package com.zcp.part2.dp;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/20
 * @description：背包能装下最多的价值 给出两个长度为N的数组 W ，V 以及背包最大能承受的重量Bag，W对应物品的重量，V对应物品的价值，
 * 要求返回背包能装下的最大价值
 * <p>
 * W[2,3,6,5,4,2,3,8]
 * V[2,5,6,3,4,5,6,4]
 * bag：10
 * 返回18
 * @version:
 */
public class BackageMaxValue {

    /**
     * 方案一：暴力递归
     *
     * @param w
     * @param v
     * @param bag
     * @return
     */
    public static int solution1(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length < 1 || bag < 0) {
            return -1;
        }
        return process1(w, v, 0, bag);
    }

    /**
     * index之前的货物不存在，只关系index起往后的货物，在bag容量允许的范围内，返回物品的最大价值
     *
     * @param w
     * @param v
     * @param index
     * @param bag
     * @return
     */
    public static int process1(int[] w, int[] v, int index, int bag) {
        if (index == w.length) {
            return 0;
        }
        int p1 = Integer.MIN_VALUE;
        //p1 选择当前位置物品
        if (bag >= w[index]) {
            p1 = v[index] + process1(w, v, index + 1, bag - w[index]);
        }
        //p2 不选择当前位置物品
        int p2 = process1(w, v, index + 1, bag);
        return Math.max(p1, p2);
    }

    /**
     * 方案二：动态规划
     *
     * @param w
     * @param v
     * @param bag
     * @return
     */
    public static int solution2(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length < 1 || bag < 0) {
            return -1;
        }
        int length = w.length;
        int[][] dp = new int[length + 1][bag + 1];
        for (int index = length - 1; index >= 0; index--) {
            for (int b = 0; b <= bag; b++) {
                int p1 = Integer.MIN_VALUE;
                if (b - w[index] >= 0) {
                    p1 = v[index] + dp[index + 1][b - w[index]];
                }
                int p2 = dp[index + 1][b];
                dp[index][b] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }


    public static void main(String[] args) {
        int[] w = {2, 3, 6, 5, 4, 2, 3, 8};
        int[] v = {2, 5, 6, 3, 4, 5, 6, 4};
        System.out.println(solution1(w, v, 10));
        System.out.println(solution2(w, v, 10));
    }


}
