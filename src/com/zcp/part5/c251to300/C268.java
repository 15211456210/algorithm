package com.zcp.part5.c251to300;

/**
 * @author ZCP
 * @title: C268
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/missing-number/submissions/
 * @date 2022/9/24 18:07
 */
public class C268 {

    public int missingNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {

            int num = nums[i];
            nums[i] = -1;

            while (num < nums.length && nums[num] != -1) {
                int t = nums[num];
                nums[num] = num;
                num = t;
            }

            if (num < nums.length) {
                nums[num] = num;
            }
        }


        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }

        }

        return nums.length;
    }
}
