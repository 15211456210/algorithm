package com.zcp.part5.c301to350;

import java.util.*;

/**
 * @author ZCP
 * @title: C350
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/intersection-of-two-arrays-ii/description/
 * @date 2022/10/20 9:59
 */
public class C350 {

    Map<Integer, Integer> help = new HashMap();

    public int[] intersect(int[] nums1, int[] nums2) {
        for (int i = 0; i < nums1.length; i++) {
            Integer cnt = help.getOrDefault(nums1[i], 0);
            help.put(nums1[i], cnt + 1);
        }

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums2.length; i++) {
            Integer remain = help.getOrDefault(nums2[i], 0);
            if (remain > 0) {
                list.add(nums2[i]);
                help.put(nums2[i], remain - 1);
            }
        }
        int size = list.size();
        int[] ans = new int[size];
        for (int i = 0; i < size; i++) {
            ans[i] = list.get(i);
        }
        return ans;

    }
}
