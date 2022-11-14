package com.zcp.part5.c301to350;

/**
 * @author ZCP
 * @title: C343
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/integer-break/description/
 * @date 2022/10/20 9:25
 */
public class C343 {

    public int integerBreak(int n) {
        if (n < 2) {
            return -1;
        }

        return (int) process(1, n, n, 1);

    }

    /**
     * 从数字index开始组成remain 的最大乘积, mult表示当前的乘积
     *
     * @param index
     * @param remain
     * @param mult
     * @return
     */
    public double process(int index, int remain, int n, double mult) {
        if (remain == 0) {
            return mult;//如果成组成数字，返回当前的乘积
        }
        if (index > remain || index == n) {
            return -1;//表示不能组成数字
        }
        double max = -1;
        for (int count = 0; count * index <= remain; count++) {
            double next = process(index + 1, remain - count * index, n, mult * Math.pow(index, count));
            if (next != -1) {
                //说明可以构成
                max = Math.max(max, next);
            }
        }
        return max;
    }
}
