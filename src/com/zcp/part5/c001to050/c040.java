package com.zcp.part5.c001to050;

import java.util.*;

/**
 * @author ZCP
 * @title: c040
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/combination-sum-ii/submissions/
 * @date 2022/8/27 10:24
 */
public class c040 {


    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        ArrayList<List<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> select = new ArrayList<>();
        int[] nums = new int[51];
        for (int i = 0; i < candidates.length; i++) {
            nums[candidates[i]]++;
        }
        process2(nums, 1, target, 0, ans, select);


        // etl
//        HashSet<String> set = new HashSet<>();
//        Arrays.sort(candidates);
//        process(candidates, 0, target, 0, ans, select, set);
        return ans;
    }


    public void process2(int[] nums, int i, int target, int sum, List<List<Integer>> ans, List<Integer> select) {
        if (sum == target) {
            ans.add(new ArrayList<>(select));
            return;
        }
        if (i == nums.length) {
            return;
        }

        for (int cnt = 0; cnt <= Math.min(nums[i], (target - sum) / i); cnt++) {
            for (int k = cnt; k > 0; k--) {
                select.add(i);
            }
            process2(nums, i + 1, target, sum + cnt * i, ans, select);
            for (int k = cnt; k > 0; k--) {
                select.remove(select.size() - 1);
            }
        }

    }


    public void process(int[] candidates, int i, int target, int sum, List<List<Integer>> ans, List<Integer> select, Set<String> set) {
        if (sum == target) {
            String serialize = serialize(select);
            if (!set.contains(serialize)) {
                ans.add(new ArrayList<>(select));
                set.add(serialize);
            }
            return;
        }
        if (i == candidates.length || (candidates[i] + sum) > target) {
            return;
        }
        select.add(candidates[i]);
        process(candidates, i + 1, target, sum + candidates[i], ans, select, set);
        select.remove(select.size() - 1);
        process(candidates, i + 1, target, sum, ans, select, set);
    }

    /**
     * 序列化数组
     *
     * @param select
     * @return
     */
    private String serialize(List<Integer> select) {
        return Arrays.toString(select.toArray());
    }


}
