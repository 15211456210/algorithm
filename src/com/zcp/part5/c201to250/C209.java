package com.zcp.part5.c201to250;

/**
 * @author ZCP
 * @title: C209
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/minimum-size-subarray-sum/submissions/
 * @date 2022/9/16 9:18
 */
public class C209 {

    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, right = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;
        while (left < nums.length) {
            while (right < nums.length && sum < target) {
                sum += nums[right++];
            }
            if (sum >= target) {
                minLen = Math.min(minLen, right - left);
            }
            sum -= nums[left++];
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
