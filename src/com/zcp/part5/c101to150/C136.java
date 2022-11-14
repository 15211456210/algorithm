package com.zcp.part5.c101to150;

/**
 * @author ZCP
 * @title: C136
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/single-number/submissions/
 * @date 2022/9/6 16:44
 */
public class C136 {

    public int singleNumber(int[] nums) {
        int ex = 0;
        for (int i = 0; i < nums.length; i++) {
            ex ^= nums[i];
        }
        return ex;
    }
}
