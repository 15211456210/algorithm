package com.zcp.part4.day22;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**
 * @author ZCP
 * @title: Test
 * @projectName algorithm
 * @description: 输入参数  所在层数n，需要到达的层数m，每次可以爬楼梯至上一次或下一层，也可以坐电梯至当前2倍层数，返回从n->m层的最小代价
 * @date 2022/4/22 16:29
 */
public class MinimumCost {


    /**
     * 暴力递归
     *
     * @param n
     * @param m
     * @return
     */
    public static int fun(int n, int m) {
        int[] dp = new int[Math.max(n, m) * 2 + 1];
        // 创建dp表，dp[n]表示n 位置到m的最小代价
        Arrays.fill(dp, -1);
        int ans = fun2(n, m, dp);
        return ans;
    }


    /**
     * 返回n - > m 最小代价
     *
     * @param n
     * @param m
     * @param dp
     * @return
     */
    public static int fun2(int n, int m, int[] dp) {
        if (m < 1 || n < 1) {
            // 代价无穷大
            return Integer.MAX_VALUE;
        }
        if (dp[n] != -1) {
            return dp[n];
        }
        if (n == m) {
            dp[n] = 0;
            return 0;
        }
        if (n > m) {
            // 如果n > m 直接往下走 （n-m）层 结束
            dp[n] = n - m;
            return n - m;
        } else {
            //上楼梯，下楼梯，上电梯，3中情况取最小值
            dp[n] = Integer.MAX_VALUE;
            int up = fun2(n + 1, m, dp);
            int down = fun2(n - 1, m, dp);
            int ele = fun2(n * 2, m, dp);
            dp[n] = (up == Integer.MAX_VALUE && down == Integer.MAX_VALUE && ele == Integer.MAX_VALUE) ? Integer.MAX_VALUE : Math.min(up, Math.min(down, ele)) + 1;
            return dp[n];
        }
    }

    public static void main(String[] args) {
        System.out.println(fun(10000, 20020));
    }

}
