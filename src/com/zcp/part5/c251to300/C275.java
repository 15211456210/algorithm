package com.zcp.part5.c251to300;

import java.util.Arrays;

/**
 * @author ZCP
 * @title: C275
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/h-index-ii/submissions/
 * @date 2022/9/25 11:58
 */
public class C275 {

    public int hIndex(int[] citations) {
        int max = 0;
        int len = citations.length;
        int l = 0, r = len - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            max = Math.max(max, Math.min(len - mid, citations[mid]));
            if (citations[mid] < len - mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return max;
    }
}
