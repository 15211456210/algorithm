package com.zcp.part5.c251to300;

import java.util.Arrays;

/**
 * @author ZCP
 * @title: C300
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/longest-increasing-subsequence/submissions/
 * @date 2022/9/27 13:55
 */
public class C300 {

    /**
     * 优化版本
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxLen = 1;
        int len = nums.length;
        int[] minEnd = new int[len];
        Arrays.fill(minEnd, Integer.MAX_VALUE);
        minEnd[0] = nums[0];
        int splitIndex = 0;
        for (int i = 1; i < len; i++) {
            int left = 0;
            int right = splitIndex;
            int mid = left + (right - left) / 2;
            while (left <= right) {
                mid = left + (right - left) / 2;
                if (minEnd[mid] >= nums[i]) {
                    right = mid - 1;
                } else if (minEnd[mid] < nums[i]) {
                    left = mid + 1;
                }
            }
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
}
