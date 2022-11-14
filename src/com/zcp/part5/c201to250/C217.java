package com.zcp.part5.c201to250;

import java.util.HashSet;

/**
 * @author ZCP
 * @title: C217
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/contains-duplicate/submissions/
 * @date 2022/9/17 17:28
 */
public class C217 {

    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> appear = new HashSet<>();
        for (int num : nums) {
            if (appear.contains(num)) {
                return true;
            }
            appear.add(num);
        }
        return false;

    }
}
