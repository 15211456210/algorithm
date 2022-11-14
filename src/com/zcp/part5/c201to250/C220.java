package com.zcp.part5.c201to250;

import java.util.TreeSet;

/**
 * @author ZCP
 * @title: C220
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/contains-duplicate-iii/
 * @date 2022/9/17 18:17
 */
public class C220 {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Integer> treeSet = new TreeSet<>();

        for (int i = 0; i <= Math.min(indexDiff, nums.length - 1); i++) {
            if (treeSet.contains(nums[i])) {
                return true;
            }
            if (treeSet.lower(nums[i]) != null && Math.abs(treeSet.lower(nums[i]) - nums[i]) <= valueDiff) {
                return true;
            }
            if (treeSet.higher(nums[i]) != null && Math.abs(treeSet.higher(nums[i]) - nums[i]) <= valueDiff) {
                return true;
            }
            treeSet.add(nums[i]);
        }


        for (int i = 0; i + indexDiff + 1 < nums.length; ++i) {
            treeSet.remove(nums[i]);
            if (treeSet.contains(nums[i + indexDiff + 1])) {
                return true;
            }
            if (treeSet.lower(nums[i + indexDiff + 1]) != null && Math.abs(treeSet.lower(nums[i + indexDiff + 1]) - nums[i + indexDiff + 1]) <= valueDiff) {
                return true;
            }
            if (treeSet.higher(nums[i + indexDiff + 1]) != null && Math.abs(treeSet.higher(nums[i + indexDiff + 1]) - nums[i + indexDiff + 1]) <= valueDiff) {
                return true;
            }
            treeSet.add(nums[i + indexDiff + 1]);
        }

        return false;

    }
}
