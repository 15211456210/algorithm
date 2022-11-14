package com.zcp.part5.c001to050;

/**
 * @author ZCP
 * @title: c026
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/remove-duplicates-from-sorted-array/submissions/
 * @date 2022/8/25 15:42
 */
public class c026 {

    public int removeDuplicates(int[] nums) {
        int l = 0;
        int r = 1;
        for (; r < nums.length; ) {
            if (nums[l] != nums[r]) {
                nums[++l] = nums[r++];

            } else {
                ++r;
            }
        }
        return l + 1;
    }
}
