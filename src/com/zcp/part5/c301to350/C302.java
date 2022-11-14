package com.zcp.part5.c301to350;

/**
 * @author ZCP
 * @title: C302
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/range-sum-query-immutable/
 * @date 2022/9/27 13:59
 */
public class C302 {

    class NumArray {
        int[] preSum;

        public NumArray(int[] nums) {
            preSum = new int[nums.length + 1];

            for (int i = 0; i < nums.length; i++) {
                preSum[i + 1] = preSum[i] + nums[i];
            }


        }

        public int sumRange(int left, int right) {
            return preSum[right + 1] - preSum[left];
        }
    }
}
