package com.zcp.part5.c351to400;

/**
 * @author ZCP
 * @title: C390
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/elimination-game/description/
 * @date 2022/11/3 10:26
 */
public class C390 {

    public int lastRemaining(int n) {
        int first = 1;
        int last = n;
        int step = 1;
        int k = 0;
        int cnt = n;
        while (cnt > 1) {
            if (k % 2 == 0) {
                // 左边开始
                first += step;
                if (cnt % 2 != 0) {
                    // 奇数
                    last -= step;
                }
            } else {
                // 右边开始
                last -= step;
                if (cnt % 2 != 0) {
                    // 奇数
                    first += step;
                }
            }
            ++k;
            step *= 2;
            cnt /= 2;
        }

        return first;

    }


    /**
     * ttl
     *
     * @param n
     * @return
     */
    public int lastRemaining2(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }

        while (arr.length > 1) {
            int[] tmp = new int[arr.length >> 1];
            for (int i = 1, j = tmp.length - 1; i < arr.length; i = i + 2, j--) {
                tmp[j] = arr[i];
            }
            arr = tmp;
        }
        return arr[0];
    }

    public static void main(String[] args) {
        for (int i = 1; i < 100; i++) {
            System.out.println(i + ": " + new C390().lastRemaining(i));
        }


    }
}
