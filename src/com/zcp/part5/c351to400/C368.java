package com.zcp.part5.c351to400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ZCP
 * @title: C368
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/largest-divisible-subset/description/
 * @date 2022/10/25 16:04
 */
public class C368 {


    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        // dp[i] 以i位置结尾的最大符合要求的长度
        int[] dp = new int[len];
        // help[i] 表示 以i结尾的最大长度情况下  i的上一个元素位置
        int[] help = new int[len];
        Arrays.fill(help, -1);
        int max = 0;
        int maxEnd = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    if (dp[i] < 1 + dp[j]) {
                        dp[i] = 1 + dp[j];
                        help[i] = j;
                        if (dp[i] > max) {
                            max = dp[i];
                            maxEnd = i;
                        }
                    }
                }
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(nums[maxEnd]);
        while (help[maxEnd] != -1) {
            maxEnd = help[maxEnd];
            ans.add(nums[maxEnd]);
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(new C368().largestDivisibleSubset(new int[]{2, 3, 4, 9, 8}));
    }
}
