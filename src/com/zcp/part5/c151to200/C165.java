package com.zcp.part5.c151to200;

/**
 * @author ZCP
 * @title: C165
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/compare-version-numbers/submissions/
 * @date 2022/9/8 16:40
 */
public class C165 {

    public int compareVersion(String version1, String version2) {
        String[] v1s = version1.split("\\.");
        String[] v2s = version2.split("\\.");
        int len = Math.max(v1s.length, v2s.length);
        int idx = 0;
        while (idx < len) {
            int i1 = idx < v1s.length ? Integer.valueOf(v1s[idx]) : 0;
            int i2 = idx < v2s.length ? Integer.valueOf(v2s[idx]) : 0;
            if (i1 != i2) {
                return i1 > i2 ? 1 : -1;
            }
            ++idx;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new C165().compareVersion("0.1", "1.1"));
    }
}
