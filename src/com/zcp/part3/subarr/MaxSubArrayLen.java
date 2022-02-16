package com.zcp.part3.subarr;

import javax.swing.text.MaskFormatter;
import java.util.HashMap;

/**
 * @description: 325. 和等于 k 的最长子数组长度
 * https://leetcode-cn.com/problems/maximum-size-subarray-sum-equals-k/
 * @projectName:algorithm
 * @see:com.zcp.part3.subarr
 * @author:ZCP
 * @createTime:2021/11/25
 * @version:1.0
 */
public class MaxSubArrayLen {

    public int maxSubArrayLen(int[] nums, int k) {
        if (nums != null && nums.length < 1) {
            return 0;
        }
        long[] preSum = new long[nums.length];
        preSum[0] = nums[0];
        HashMap<Long, Integer> preSumFirstIndex = new HashMap<>();//前缀和出现某个数的最早下标
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i];

        }

        int maxAns = 0;
        preSumFirstIndex.put(0l, -1);
        for (int i = 0; i < nums.length; i++) {
            long arm = preSum[i] - k;//前缀和目标
            if (preSumFirstIndex.containsKey(arm)) {
                maxAns = Math.max(maxAns, i - preSumFirstIndex.get(arm));
            }
            if (!preSumFirstIndex.containsKey(preSum[i])) {
                preSumFirstIndex.put(preSum[i], i);
            }
        }
        return maxAns;
    }


    public static void main(String[] args) {
        MaxSubArrayLen maxSubArrayLen = new MaxSubArrayLen();

        maxSubArrayLen.maxSubArrayLen(new int[]{-2,-1,2,1},1);

    }
}
