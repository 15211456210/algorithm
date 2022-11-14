package com.zcp.part5.c051to100;

import java.util.HashSet;

/**
 * @author ZCP
 * @title: c081
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/search-in-rotated-sorted-array-ii/
 * @date 2022/8/30 15:49
 */
public class c081 {

    public boolean search(int[] nums, int target) {

        int l = 0;
        int r = nums.length - 1;
        int mid;

        while (l <= r) {
            mid = l + (r - l) / 2;
            if (nums[mid] == target || nums[l] == target || nums[r] == target) {
                return true;
            }


            if (nums[mid] == nums[l] && nums[mid] == nums[r]) {
                l++;
                r--;
            } else if (nums[mid] < target) {
                if (nums[mid] > nums[r] || nums[r] > target) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            } else {
                // nums[mid] > target
                if (nums[mid] < nums[l] || nums[l] < target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }

        return false;

    }
}
