package com.zcp.part5.c101to150;

import java.util.Arrays;

/**
 * @author ZCP
 * @title: C137
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/single-number-ii/submissions/
 * @date 2022/9/6 16:45
 */
public class C137 {

//    public int singleNumber(int[] nums) {
//        Arrays.sort(nums);
//
//        for (int i = 0; i < nums.length; i = i + 3) {
//            if (i == nums.length - 1 || nums[i] != nums[i + 1]) {
//                return nums[i];
//            }
//        }
//        return 0;
//    }


    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i <= 31; i++) {
            int oneCnt = 0;
            for (int j = 0; j < nums.length; j++) {
                oneCnt += (nums[j] >>> i) & 1;
            }
            ans += oneCnt % 3 == 1 ? (1 << i) : 0;
        }
        return ans;
    }
}
