package com.zcp.part5.c251to300;

import java.util.Arrays;

/**
 * @author ZCP
 * @title: C283
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/move-zeroes/submissions/
 * @date 2022/9/26 16:33
 */
public class C283 {

    public void moveZeroes(int[] nums) {
        int l = 0;
        int r = 0;
        while (r < nums.length) {
            if (nums[r] != 0) {
                nums[l++] = nums[r];
            }
            ++r;
        }
        Arrays.fill(nums, l, nums.length, 0);
    }

}
