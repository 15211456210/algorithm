package com.zcp.part4.day09;

import java.util.Arrays;

// 本题测试链接 : https://leetcode.com/problems/longest-increasing-subsequence
public class Code03_LIS {


    /**
     * 优化版本
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxLen = 1;
        int len = nums.length;
        //minEnd[i]表示：当前时刻，长度为（i+1）的递增自序列中最小的结尾值
        int[] minEnd = new int[len];
        Arrays.fill(minEnd, Integer.MAX_VALUE);
        //先放入数组一个元素 表示当前长度为1的递增子序列 最小结尾的值是nums[0]
        minEnd[0] = nums[0];
        //minEnd中的分界线，左边表示有用的值，右边表示未初始化的值，后续二分查找只在左半部分中进行
        int splitIndex = 0;
        for (int i = 1; i < len; i++) {
            int left = 0;
            int right = splitIndex;
            int mid = left + (right - left) / 2;
            //二分查找 minEnd中 小于nums[i]离它最近的位置
            while (left <= right) {
                mid = left + (right - left) / 2;
                if (minEnd[mid] >= nums[i]) {
                    right = mid - 1;
                } else if (minEnd[mid] < nums[i]) {
                    left = mid + 1;
                }
            }
            //计算以nums[i]当前元素结尾的最长递增子序列
            int curMaxLen = 0;
            if (minEnd[mid] < nums[i]) {
                curMaxLen = mid + 2;
                minEnd[curMaxLen - 1] = Math.min(minEnd[curMaxLen - 1], nums[i]);
            } else if (minEnd[mid] >= nums[i]) {
                if (mid == 0) {
                    curMaxLen = 1;
                    minEnd[0] = Math.min(minEnd[0], nums[i]);
                } else {
                    //mid>0
                    curMaxLen = mid + 1;
                    minEnd[curMaxLen - 1] = Math.min(minEnd[curMaxLen - 1], nums[i]);
                }
            }
            splitIndex = Math.max(splitIndex, curMaxLen - 1);
            maxLen = Math.max(maxLen, curMaxLen);
        }
        return maxLen;
    }


    /**
     * 思路：
     * 动态规划
     * 计算每个以nums[i]值为结尾的最长递增子序列的长度
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxLen = 0;
        int len = nums.length;
        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            int maxPrefixLen = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    maxPrefixLen = Math.max(maxPrefixLen, dp[j]);
                }
            }
            dp[i] = maxPrefixLen + 1;
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }

    public static int lengthOfLIS(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] ends = new int[arr.length];
        ends[0] = arr[0];
        int right = 0;
        int l = 0;
        int r = 0;
        int m = 0;
        int max = 1;
        for (int i = 1; i < arr.length; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                m = (l + r) / 2;
                if (arr[i] > ends[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = arr[i];
            max = Math.max(max, l + 1);
        }
        return max;
    }

}