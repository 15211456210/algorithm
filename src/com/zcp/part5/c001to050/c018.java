package com.zcp.part5.c001to050;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ZCP
 * @title: c018
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/4sum/submissions/
 * @date 2022/8/25 15:32
 */
public class c018 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);

        int len = nums.length;
        int[] lHelp = new int[len];
        int[] rHelp = new int[len];

        lHelp[len - 1] = len;
        for (int i = 0; i < len; i++) {
            int k = i;
            while (k + 1 < len && nums[k + 1] == nums[k]) {
                k++;
            }
            for (int m = i; m < k + 1; m++) {
                lHelp[m] = k + 1;
            }
        }


        for (int i = len - 1; i >= 0; i--) {
            int k = i;
            while (k - 1 >= 0 && nums[k - 1] == nums[k]) {
                k--;
            }
            for (int m = i; m >= k; m--) {
                rHelp[m] = k - 1;
            }
        }

        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for (int a = 0; a < len; ) {
            for (int b = a + 1; b < len; ) {
                long tar = (long) target - (long) nums[a] - (long) nums[b];
                int l = b + 1;
                int r = len - 1;
                while (l < r) {
                    if ((long) (nums[l] + nums[r]) == tar) {
                        ans.add(Arrays.asList(nums[a], nums[b], nums[l], nums[r]));
                        l = lHelp[l];
                        r = rHelp[r];
                    } else if ((long) (nums[l] + nums[r]) > tar) {
                        r = rHelp[r];
                    } else {
                        l = lHelp[l];
                    }
                }
                b = lHelp[b];
            }
            a = lHelp[a];
        }
        return ans;
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new c018().fourSum(new int[]{1000000000, 1000000000, 1000000000, 1000000000}, -294967296);

        System.out.println(Arrays.toString(lists.toArray()));


    }
}
