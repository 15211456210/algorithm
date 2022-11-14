package com.zcp.part5.c251to300;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author ZCP
 * @title: C
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/single-number-iii/
 * @date 2022/9/23 9:03
 */
public class C260 {

    public int[] singleNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i])) {
                set.add(nums[i]);
            } else {
                set.remove(nums[i]);
            }
        }

        int[] ans = new int[2];

        Iterator<Integer> iterator = set.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            ans[i++] = iterator.next();
        }
        return ans;

    }

}
