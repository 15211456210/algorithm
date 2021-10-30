package com.zcp.part2.dp;

import java.util.TreeMap;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/20
 * @description：[[数字转化为字符串的结果数]]https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/
 * @version:
 */
public class TransferNumToStr {


    /**
     * 方案1：暴力递归
     *
     * @param num
     * @return
     */
    public int solution1(int num) {
        char[] nums = String.valueOf(num).toCharArray();
        return process1(nums, 0);
    }

    /**
     * 从index位置开始有多少种转换方案
     *
     * @param nums
     * @param index
     * @return
     */
    public int process1(char[] nums, int index) {
        if (index >= nums.length) {
            return 1;
        }
        int p1 = process1(nums, index + 1);
        int p2 = 0;
        if (nums[index] != '0') {
            //p2 当前二位数作为字母
            if (index + 1 < nums.length && (Integer.valueOf(nums[index] + "") * 10 + Integer.valueOf(nums[index + 1] + "")) < 26) {
                p2 = process1(nums, index + 2);
            }
        }
        return p1 + p2;
    }

    /**
     * 方案2：动态规划
     *
     * @param num
     * @return
     */
    public int solution2(int num) {
        char[] nums = String.valueOf(num).toCharArray();

        int length = nums.length;
        int[] dp = new int[length];
        dp[length - 1] = 1;
        for (int index = length - 2; index >= 0; index--) {
            int p1 = dp[index + 1];
            int p2 = 0;
            if (nums[index] != '0') {
                if (index + 1 < length && (Integer.valueOf(nums[index] + "") * 10 + Integer.valueOf(nums[index + 1] + "")) < 26) {
                    p2 = index + 2 < length ? dp[index + 2] : 1;
                }
            }
            dp[index] = p1 + p2;
        }
        return dp[0];
    }


    public static void main(String[] args) {
        TransferNumToStr transferNumToStr = new TransferNumToStr();
        System.out.println(transferNumToStr.solution1(25));
        System.out.println(transferNumToStr.solution2(25));
    }

}
