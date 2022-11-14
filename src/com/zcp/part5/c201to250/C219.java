package com.zcp.part5.c201to250;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ZCP
 * @title: C219
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/contains-duplicate-ii/submissions/
 * @date 2022/9/17 17:30
 */
public class C219 {

    public boolean containsNearbyDuplicate(int[] nums, int k) {

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < Math.min(k + 1, nums.length); i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
        }
        for (int i = 0; i + k + 1 < nums.length; i++) {
            set.remove(nums[i]);
            if (set.contains(nums[i + k + 1])) {
                return true;
            }
            set.add(nums[i + k + 1]);
        }

        return false;

    }
}
