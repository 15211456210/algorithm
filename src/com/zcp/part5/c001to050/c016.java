package com.zcp.part5.c001to050;

import java.util.*;

/**
 * @author ZCP
 * @title: c016
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/3sum-closest/
 * @date 2022/8/25 14:03
 */
public class c016 {

    public int threeSumClosest(int[] nums, int target) {

        return process2(nums, target);
        // 超时方法
        // return process(nums, 0, 3, 0, target);
    }

    public int process2(int[] nums, int target) {
        Arrays.sort(nums);
        int b = 1;
        int c = nums.length - 1;
        int ans = nums[0] + nums[b] + nums[c];
        for (int i = 0; i < nums.length; i++) {
            b = i + 1;
            c = nums.length - 1;
            while (b < c) {
                int sum = nums[b] + nums[c] + nums[i];
                if (Math.abs(sum - target) < Math.abs(ans - target)) {
                    ans = sum;
                }
                if (sum >= target) {
                    c--;
                } else {
                    b++;
                }
            }
        }
        return ans;

    }

    /**
     * 超时方法
     *
     * @param nums
     * @param index  当前所在数组下标位置
     * @param remain 还有多少各数可以选择
     * @param sum    当前选择的数的和
     * @param target 目标数
     * @return 最接近目标数的sum
     */
    public int process(int[] nums, int index, int remain, int sum, int target) {
        if (remain == 0) {
            return sum;
        }
        if (index >= nums.length) {
            return Integer.MAX_VALUE;
        }

        int p1 = process(nums, index + 1, remain - 1, sum + nums[index], target);

        int p2 = process(nums, index + 1, remain, sum, target);

        if (p1 == Integer.MAX_VALUE && p2 != Integer.MAX_VALUE) {
            return p2;
        } else if (p2 == Integer.MAX_VALUE && p1 != Integer.MAX_VALUE) {
            return p1;
        } else if (p1 == Integer.MAX_VALUE && p2 == Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        return Math.abs(p1 - target) < Math.abs(p2 - target) ? p1 : p2;


    }

}
