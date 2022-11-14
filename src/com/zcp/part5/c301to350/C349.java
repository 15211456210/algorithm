package com.zcp.part5.c301to350;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ZCP
 * @title: C349
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/intersection-of-two-arrays/description/
 * @date 2022/10/20 9:51
 */
public class C349 {

    Set<Integer> set1 = new HashSet<>();
    Set<Integer> ans = new HashSet<>();

    public int[] intersection(int[] nums1, int[] nums2) {
        for (int i = 0; i < nums1.length; i++) {
            set1.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (set1.contains(nums2[i])) {
                ans.add(nums2[i]);
            }
        }
        int[] a = new int[ans.size()];
        int i = 0;
        for (Integer num : ans) {
            a[i++] = num;
        }
        return a;

    }
}
