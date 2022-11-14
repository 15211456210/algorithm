package com.zcp.part5.c001to050;

/**
 * @author ZCP
 * @title: c034
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/submissions/
 * @date 2022/8/26 16:30
 */
public class c034 {

    public int[] searchRange(int[] nums, int target) {
        int[] ans = new int[2];
        ans[0] = -1;
        ans[1] = -1;
        if (nums == null || nums.length == 0) {
            return ans;
        }
        int l = 0;
        int r = nums.length - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] >= target) {
                r = mid - 1;
            }
        }
        if ((r >= 0 && r < nums.length && nums[r] == target) || (r + 1 >= 0 && r + 1 < nums.length && nums[r + 1] == target)) {
            ans[0] = (r >= 0 && r < nums.length && nums[r] == target) ? r : r + 1;
        }

        l = 0;
        r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] <= target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            }
        }
        if ((l >= 0 && l < nums.length && nums[l] == target) || (l - 1 >= 0 && l - 1 < nums.length && nums[l - 1] == target)) {
            ans[1] = (l >= 0 && l < nums.length && nums[l] == target) ? l : l - 1;
        }
        return ans;
    }
}
