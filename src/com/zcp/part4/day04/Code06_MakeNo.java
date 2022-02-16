package com.zcp.part4.day04;

import java.util.Arrays;

public class Code06_MakeNo {


    /**
     * 思路：
     * 数学技巧
     *
     * @param size
     * @return
     */
    public static int[] makeNo2(int size) {
        if (size == 1) {
            return new int[]{1};
        }
        int csz = 1;
        int[] pre = {1};
        while (csz < size) {
            int nsz = csz << 1;
            int[] cArr = new int[nsz];
            for (int i = 0; i < csz; i++) {
                cArr[i] = 2 * pre[i];
            }
            for (int i = csz; i < nsz; i++) {
                cArr[i] = cArr[i - csz] + 1;
            }
            pre = cArr;
            csz = nsz;
        }
        int[] ans = new int[size];
        for (int i = 0; i < size; i++) {
            ans[i] = pre[i];
        }
        return ans;
    }

    // 生成长度为size的达标数组
    // 达标：对于任意的 i<k<j，满足 [i] + [j] != [k] * 2
    public static int[] makeNo(int size) {
        if (size == 1) {
            return new int[]{1};
        }
        // size
        // 一半长达标来
        // 7 : 4
        // 8 : 4
        // [4个奇数] [3个偶]
        int halfSize = (size + 1) / 2;
        int[] base = makeNo(halfSize);
        // base -> 等长奇数达标来
        // base -> 等长偶数达标来
        int[] ans = new int[size];
        int index = 0;
        for (; index < halfSize; index++) {
            ans[index] = base[index] * 2 - 1;
        }
        for (int i = 0; index < size; index++, i++) {
            ans[index] = base[i] * 2;
        }
        return ans;
    }

    // 检验函数
    public static boolean isValid(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            for (int k = i + 1; k < N; k++) {
                for (int j = k + 1; j < N; j++) {
                    if (arr[i] + arr[j] == 2 * arr[k]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        for (int N = 1; N < 1000; N++) {
            int[] arr = makeNo(N);
            int[] arr2 = makeNo2(N);
            if (!isValid(arr) || !isValid(arr2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
        System.out.println(isValid(makeNo(1042)));
        System.out.println(isValid(makeNo2(2981)));
        System.out.println(Arrays.toString(makeNo(20)));
        System.out.println(Arrays.toString(makeNo2(20)));
    }

}
