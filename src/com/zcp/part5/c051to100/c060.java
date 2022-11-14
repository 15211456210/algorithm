package com.zcp.part5.c051to100;

import java.util.Arrays;

/**
 * @author ZCP
 * @title: c060
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/permutation-sequence/submissions/
 * @date 2022/8/28 18:59
 */
public class c060 {

    static int[] map = new int[]{0, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};
    static boolean[] select = new boolean[10];

    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        Arrays.fill(select, false);
        for (int i = 1; i < n; i++) {
            int mod = k % map[n - i];
            int rank = (k / map[n - i]) + (mod == 0 ? 0 : 1);
            k = mod == 0 ? map[n - i] : mod;
            int selected = getK(rank);
            sb.append(selected + "");
            select[selected] = true;
        }
        int selected = getK(1);
        sb.append(selected + "");
        return sb.toString();

    }

    private int getK(int rank) {
        int i = 1;
        for (; i < select.length; i++) {
            if (!select[i]) {
                rank--;
                if (rank == 0) {
                    break;
                }
            }
        }
        return i;
    }
}
