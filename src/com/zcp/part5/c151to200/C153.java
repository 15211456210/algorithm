package com.zcp.part5.c151to200;

/**
 * @author ZCP
 * @title: C153
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/submissions/
 * @date 2022/9/7 13:35
 */
public class C153 {

    public int findMin(int[] nums) {

        int l = 0, r = nums.length - 1;

        while (l <= r) {
            if (l == r) {
                return nums[l];
            }
            if (l + 1 == r) {
                return Math.min(nums[l], nums[r]);
            }
            int mid = l + (r - l) / 2;
            if (nums[l] < nums[mid] && nums[r] < nums[mid]) {
                l = mid;
            } else {
                r = mid;
            }

        }
        return 0;
    }
}
