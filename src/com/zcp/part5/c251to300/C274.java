package com.zcp.part5.c251to300;

import java.util.Arrays;

/**
 * @author ZCP
 * @title: C274
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/h-index/
 * @date 2022/9/25 3:02
 */
public class C274 {

    public int hIndex(int[] citations) {
        int max = 0;

        Arrays.sort(citations);
        int len = citations.length;
        for (int i = len - 1; i >= 0; i--) {
            max = Math.max(max, Math.min(len - i, citations[i]));
        }
        return max;
    }
}
