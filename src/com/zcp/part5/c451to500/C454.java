package com.zcp.part5.c451to500;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZCP
 * @title: C454
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/4sum-ii/submissions/
 * @date 2023/2/4 9:56
 */
public class C454 {

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> leftMap = new HashMap<>();
        Map<Integer, Integer> rightMap = new HashMap<>();
        int n = nums1.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int num = nums1[i] + nums2[j];
                leftMap.put(num, leftMap.getOrDefault(num, 0) + 1);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int num = nums3[i] + nums4[j];
                rightMap.put(num, rightMap.getOrDefault(num, 0) + 1);
            }
        }
        int ans = 0;
        for (int leftNum : leftMap.keySet()) {
            ans += leftMap.get(leftNum) * rightMap.getOrDefault(-leftNum, 0);
        }
        return ans;
    }

}
