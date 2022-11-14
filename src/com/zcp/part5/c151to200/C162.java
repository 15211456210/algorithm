package com.zcp.part5.c151to200;

/**
 * @author ZCP
 * @title: C161
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/find-peak-element/submissions/
 * @date 2022/9/8 15:53
 */
public class C162 {
    public int findPeakElement(int[] nums) {

        if (nums.length == 1) {
            return 0;
        }
        if (nums.length == 2) {
            return nums[0] > nums[1] ? 0 : 1;
        }


        int l = 0;
        int r = nums.length - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (l + 1 == r) {
                return nums[l] > nums[r] ? l : r;
            }
            if (mid == 0 && nums[mid + 1] < nums[mid]) {
                return mid;
            }
            if (mid == nums.length - 1 && nums[mid] > nums[mid - 1]) {
                return mid;
            }
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            }

            if (nums[mid] <= nums[l]) {
                r = mid;
            } else if (nums[mid] <= nums[r]) {
                l = mid;
            } else {
                --r;
                ++l;
            }
        }
        return l;
    }
}
