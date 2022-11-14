package com.zcp.part5.c201to250;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author ZCP
 * @title: C229
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/majority-element-ii/submissions/
 * @date 2022/9/19 14:11
 */
public class C229 {

    public List<Integer> majorityElement(int[] nums) {
        HashMap<Integer, Integer> candidates = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (candidates.containsKey(nums[i])) {
                candidates.put(nums[i], candidates.get(nums[i]) + 1);
            } else if (candidates.size() < 2) {
                candidates.put(nums[i], 1);
            } else {
                List<Integer> rmList = new ArrayList<>();
                Set<Integer> keys = candidates.keySet();
                for (int key : keys) {
                    int hp = candidates.get(key);
                    if (hp == 1) {
                        rmList.add(key);
                    } else {
                        candidates.put(key, hp - 1);
                    }
                }
                for (int rmKey : rmList) {
                    candidates.remove(rmKey);
                }
            }
        }

        Set<Integer> keys = candidates.keySet();
        for (int key : keys) {
            candidates.put(key, 0);
        }

        for (int i = 0; i < nums.length; i++) {
            if (candidates.containsKey(nums[i])) {
                candidates.put(nums[i], candidates.get(nums[i]) + 1);
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int key : keys) {
            if (candidates.get(key) > nums.length / 3) {
                ans.add(key);
            }
        }

        return ans;


    }
}
