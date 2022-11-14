package com.zcp.part5.c001to050;

/**
 * @author ZCP
 * @title: c004
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/median-of-two-sorted-arrays/submissions/
 * @date 2022/8/25 11:31
 */
public class c004 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null && nums2 == null) {
            return -1;
        }
        int len1 = nums1.length;
        int len2 = nums2.length;
        int totalLen = len1 + len2;
        int[] midIndex = new int[2];
        midIndex[0] = -1;
        midIndex[1] = -1;
        if (totalLen % 2 != 0) {
            //奇数
            midIndex[0] = totalLen / 2;
        } else {
            //偶数
            midIndex[0] = totalLen / 2 - 1;
            midIndex[1] = totalLen / 2;
        }
        double ans = 0;
        int p1 = 0, p2 = 0;
        int count = 0;
        while (count < totalLen) {
            int num = 0;
            if (p1 < len1 && p2 < len2) {
                if (nums1[p1] <= nums2[p2]) {
                    num = nums1[p1];
                    p1++;
                } else {
                    num = nums2[p2];
                    p2++;
                }
            } else if (p1 >= len1) {
                num = nums2[p2];
                p2++;
            } else {
                //(p2 >= len2)\
                num = nums1[p1];
                p1++;
            }

            count++;
            if (count - 1 == midIndex[0]) {
                ans += num;
                if (midIndex[1] == -1) {
                    return ans;
                }
            } else if (count - 1 == midIndex[1]) {
                //偶数情况
                ans += num;
                return ans / 2;
            }
        }
        return 0;
    }
}
