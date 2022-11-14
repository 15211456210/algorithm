package com.zcp.part5.c101to150;

/**
 * @author ZCP
 * @title: C135
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/candy/submissions/
 * @date 2022/9/6 16:44
 */
public class C135 {

    public int candy(int[] ratings) {

        if (ratings == null || ratings.length < 1) {
            return 0;
        }

        int N = ratings.length;
        int[] left = new int[N];
        int[] right = new int[N];
        left[0] = 1;
        right[N - 1] = 1;
        for (int i = 1; i < N; i++) {
            left[i] = ratings[i] > ratings[i - 1] ? left[i - 1] + 1 : 1;
            right[N - i - 1] = ratings[N - i - 1] > ratings[N - i] ? right[N - i] + 1 : 1;
        }
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += left[i] > right[i] ? left[i] : right[i];
        }
        return sum;

    }
}
