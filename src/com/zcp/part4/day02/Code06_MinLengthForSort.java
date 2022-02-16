package com.zcp.part4.day02;

// 本题测试链接 : https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
public class Code06_MinLengthForSort {


    /**
     * 思路：
     * 找出左边界 和 右边界
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length < 0) {
            return 0;
        }

        int right = -1;
        int max = Integer.MIN_VALUE;//前面所有数中的最大值
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < max) {
                right = i;
            }
            max = Math.max(max, nums[i]);
        }

        int left = nums.length;
        int min = Integer.MAX_VALUE;//前面所有数中的最大值
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > min) {
                left = i;
            }
            min = Math.min(min, nums[i]);
        }
        return (right == -1) ? 0 : right - left + 1;//第一种可能是数组原本就有序
    }


    public static int findUnsortedSubarray2(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int N = nums.length;
        int right = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            if (max > nums[i]) {
                right = i;
            }
            max = Math.max(max, nums[i]);
        }
        int min = Integer.MAX_VALUE;
        int left = N;
        for (int i = N - 1; i >= 0; i--) {
            if (min < nums[i]) {
                left = i;
            }
            min = Math.min(min, nums[i]);
        }
        return Math.max(0, right - left + 1);
    }

}
