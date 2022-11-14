package com.zcp.part5.c251to300;

import java.util.TreeSet;

/**
 * @author ZCP
 * @title: C264
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/ugly-number-ii/
 * @date 2022/9/24 18:02
 */
public class C264 {

    public int nthUglyNumber(int n) {
        TreeSet<Long> treeSet = new TreeSet<>();
        treeSet.add(1L);
        for (int i = 1; i < n; i++) {
            long first = treeSet.pollFirst();
            treeSet.add(first * 2);
            treeSet.add(first * 3);
            treeSet.add(first * 5);
        }
        return treeSet.first().intValue();
    }
}
