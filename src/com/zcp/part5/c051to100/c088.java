package com.zcp.part5.c051to100;

/**
 * @author ZCP
 * @title: c088
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/merge-sorted-array/submissions/
 * @date 2022/8/31 9:29
 */
public class c088 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int t1 = m - 1, t2 = n - 1, t = m + n - 1;
        while (t > -1) {
            if (t2 < 0 || (t1 >= 0 && nums1[t1] > nums2[t2])) {
                nums1[t--] = nums1[t1--];
            } else {
                nums1[t--] = nums2[t2--];
            }
        }
    }
}
