package com.zcp.part5.c401to450;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZCP
 * @title: C446
 * @projectName algorithm
 * @description: https://leetcode.com/problems/arithmetic-slices-ii-subsequence/
 * @date 2023/2/1 16:37
 */
public class C446 {

    public int numberOfArithmeticSlices(int[] nums) {
        int ans = 0;
        int length = nums.length;
        // Map<步长, Map<数列长度, 个数>>
        Map<Long, Map<Integer, Integer>>[] maps = new Map[length];
        for (int i = 0; i < length; i++) {
            HashMap<Long, Map<Integer, Integer>> map = new HashMap<>();
            for (int j = 0; j < i; j++) {
                long step = (long) nums[i] - (long)nums[j];
                Map<Integer, Integer> cLenMap = map.getOrDefault(step, new HashMap<>());
                Map<Integer, Integer> lenMap = maps[j].getOrDefault(step, new HashMap<>());
                cLenMap.put(2, cLenMap.getOrDefault(2, 0) + 1);
                for (Integer len : lenMap.keySet()) {
                    int addCnt = lenMap.get(len);
                    cLenMap.put(len + 1, cLenMap.getOrDefault(len + 1, 0) + addCnt);
                    if (len + 1 >= 3) {
                        ans += addCnt;
                    }
                }
                map.put(step, cLenMap);
            }
            maps[i] = map;
        }
        return ans;
    }

    /**
     * n >= m
     * C(n,m)+ C(n,m+1)+...+ C(n,n)
     *
     * @param m
     * @param n
     * @return
     */
    private static long ac(int m, int n) {
        int ans = 0;
        for (; m <= n; m++) {
            ans += combination(m, n);
        }
        return ans;
    }

    /**
     * 计算n的阶乘
     *
     * @param n
     * @return 返回 n!
     */
    private static long factorial(int n) {
        long sum = 1;
        while (n > 0) {
            sum = sum * n--;
        }
        return sum;
    }

    /**
     * 计算组合C(n, m)
     *
     * @param m
     * @param n
     * @return 返回C(n, m)的组合个数
     */
    private static long combination(int m, int n) {
        return m <= n ? factorial(n) / (factorial(n - m) * factorial(m)) : 0;
    }

    public static void main(String[] args) {
        System.out.println(new C446().numberOfArithmeticSlices(new int[]{7, 7, 7, 7}));
        System.out.println(new C446().numberOfArithmeticSlices(new int[]{2, 4, 6, 8, 10}));
        System.out.println(new C446().numberOfArithmeticSlices(new int[]{2, 2, 3, 4}));
    }
}
