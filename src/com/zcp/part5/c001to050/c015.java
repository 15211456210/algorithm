package com.zcp.part5.c001to050;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ZCP
 * @title: c015
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/3sum/submissions/
 * @date 2022/8/25 14:02
 */
public class c015 {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int m = len - 1;
            for (int j = i + 1; j < len; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                while (nums[i] + nums[j] + nums[m] > 0 && j < m) {
                    m--;
                }
                if (j >= m) {
                    break;
                }
                if (nums[i] + nums[j] + nums[m] == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[m]);
                    ans.add(list);
                }

            }
        }
        return ans;
    }
}
