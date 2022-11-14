package com.zcp.part5.c001to050;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ZCP
 * @title: c039
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/combination-sum/
 * @date 2022/8/27 10:23
 */
public class c039 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        fun(candidates, 0, target, new ArrayList<Integer>(), list);
        return list;
    }

    public void fun(int[] candidates, int index, int remain, List<Integer> selected, List<List<Integer>> list) {
        if (index == candidates.length) {
            if (remain == 0) {
                list.add(new ArrayList<Integer>(selected));
            }
            return;
        }
        if (remain == 0) {
            list.add(new ArrayList<Integer>(selected));
            return;
        }

        int curNum = candidates[index];
        for (int i = 0; i <= remain / curNum; i++) {
            for (int k = 1; k <= i; k++) {
                selected.add(curNum);
            }
            fun(candidates, index + 1, remain - curNum * i, selected, list);
            for (int k = 1; k <= i; k++) {
                selected.remove(selected.size() - 1);
            }
        }
    }
}
