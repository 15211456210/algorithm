package com.zcp.part5.c201to250;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZCP
 * @title: C228
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/summary-ranges/submissions/
 * @date 2022/9/19 14:04
 */
public class C228 {

    public List<String> summaryRanges(int[] nums) {
        ArrayList<String> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        int start = nums[0];
        int end = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (end + 1 == nums[i]) {
                end = nums[i];
                continue;
            }
            ans.add(start == end ? "" + start : start + "->" + end);
            start = nums[i];
            end = nums[i];
        }
        ans.add(start == end ? "" + start : start + "->" + end);
        return ans;
    }

}
