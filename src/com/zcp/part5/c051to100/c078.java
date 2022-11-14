package com.zcp.part5.c051to100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZCP
 * @title: c078
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/subsets/submissions/
 * @date 2022/8/30 15:28
 */
public class c078 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        ans.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            List<List<Integer>> newAns = new ArrayList<List<Integer>>(ans);
            for (List<Integer> list : ans) {
                List<Integer> newList = new ArrayList<Integer>(list);
                newList.add(num);
                newAns.add(newList);
            }
            ans = newAns;
        }
        return ans;
    }
}
