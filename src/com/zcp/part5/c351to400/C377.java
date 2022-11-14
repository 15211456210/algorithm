package com.zcp.part5.c351to400;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZCP
 * @title: C377
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/combination-sum-iv/
 * @date 2022/10/29 9:49
 */
public class C377 {


    Map<Integer, Integer> dp = new HashMap<>();

    public int combinationSum4(int[] nums, int target) {
        // int[] dp = new int[target+1];
        Arrays.sort(nums);
        return dfs(nums, target);
    }

    private int dfs(int[] nums, int target) {
        if (target == 0) {
            return 1;
        }

        if (dp.containsKey(target)) {
            return dp.get(target);
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= target) {
                res += dfs(nums, target - nums[i]);
            }

        }
        dp.put(target, res);
        return res;
    }
}
