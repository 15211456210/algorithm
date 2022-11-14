package com.zcp.part5.c151to200;

/**
 * @author ZCP
 * @title: C169
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/majority-element/submissions/
 * @date 2022/9/9 9:36
 */
public class C169 {

    public int majorityElement(int[] nums) {
        int num = 0;
        for (int i = 0; i <= 31; i++) {
            int oneCnt = 0;
            for (int j = 0; j < nums.length; j++) {
                if ((nums[j] & 1 << i) != 0) {
                    oneCnt++;
                }
            }
            if (oneCnt > nums.length / 2) {
                num = num + (1 << i);
            }
        }
        return num;
    }
}
