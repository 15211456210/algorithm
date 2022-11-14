package com.zcp.part4.day22;

import java.util.Arrays;

/**
 * @author ZCP
 * @title: MaxSumOfThreeSubarrays
 * @projectName algorithm
 * @description: https://leetcode-cn.com/problems/maximum-sum-of-3-non-overlapping-subarrays/submissions/
 * @date 2022/3/14 17:00
 */
public class MaxSumOfThreeSubarrays {

    /**
     * 思路：
     * 1.生成左右方向preMaxSum辅助数组，记录每个位置左右两侧子数组最大累加和（要求子数组长度为k）
     * 2.同时生成辅助数组preMaxSumStartIndex辅助数组，每个位置左右两侧最大和子数组的开始下标
     * 3.通过移动中间大小为k的子数组，一次计算左，中，右3个子数组累加和，并通过preMaxSumStartIndex记录最优解下标位置
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int len = nums.length;
        int[] preSum = new int[len + 1];
        preSum[0] = 0;
        for (int i = 1; i < len; i++) {
            preSum[i] = nums[i - 1] + preSum[i - 1];
        }

        int[] leftMaxSumSubArr = new int[len];
        int[] rightMaxSumSubArr = new int[len];
        int[] leftMaxSumStartIndex = new int[len];
        int[] rightMaxSumStartIndex = new int[len];
        leftMaxSumSubArr[0] = nums[0];
        rightMaxSumSubArr[len - 1] = nums[len - 1];

        for (int i = 1; i < len; i++) {
            if (i < k) {
                leftMaxSumSubArr[i] = leftMaxSumSubArr[i - 1] + nums[i];
                rightMaxSumSubArr[len - 1 - i] = nums[len - i - 1] + rightMaxSumSubArr[len - i];
                leftMaxSumStartIndex[i] = 0;
                rightMaxSumStartIndex[len - i - 1] = len - i - 1;
                continue;
            }
            if (preSum[i + 1] - preSum[i - k + 1] > leftMaxSumSubArr[i - 1]) {
                leftMaxSumSubArr[i] = preSum[i + 1] - preSum[i - k + 1];
                leftMaxSumStartIndex[i] = i - k + 1;
            } else {
                leftMaxSumSubArr[i] = leftMaxSumSubArr[i - 1];
                leftMaxSumStartIndex[i] = leftMaxSumStartIndex[i - 1];
            }

            if (preSum[len - i + k - 1] - preSum[len - i - 1] >= rightMaxSumSubArr[len - i]) {
                rightMaxSumSubArr[len - i - 1] = preSum[len - i + k - 1] - preSum[len - i - 1];
                rightMaxSumStartIndex[len - i - 1] = len - i - 1;
            } else {
                rightMaxSumSubArr[len - i - 1] = rightMaxSumSubArr[len - i];
                rightMaxSumStartIndex[len - i - 1] = rightMaxSumStartIndex[len - i];
            }

        }

        int maxAns = 0;
        int[] ans = new int[3];
        for (int i = k; i <= len - 2 * k; i++) {
            int preMaxSum = leftMaxSumSubArr[i - 1];
            int postMaxSum = rightMaxSumSubArr[i + k];
            int midMaxSum = preSum[i + k] - preSum[i];
            if (preMaxSum + midMaxSum + postMaxSum > maxAns) {
                maxAns = preMaxSum + midMaxSum + postMaxSum;
                ans[0] = leftMaxSumStartIndex[i - 1];
                ans[1] = i;
                ans[2] = rightMaxSumStartIndex[i + k];
            }
        }
        return ans;
    }

}
