package com.zcp.part5.c401to450;

/**
 * @author ZCP
 * @title: C413
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/arithmetic-slices/description/
 * @date 2022/11/23 9:42
 */
public class C413 {

    public int numberOfArithmeticSlices(int[] nums) {

        int len = nums.length;
        int ans = 0;
        for (int l = 0; l <= len - 3; l++) {
            int step = nums[l + 1] - nums[l];
            for (int r = l + 1; r < len; r++) {
                if (nums[r] - nums[r - 1] != step) {
                    break;
                }
                if (r - l + 1 >= 3) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
