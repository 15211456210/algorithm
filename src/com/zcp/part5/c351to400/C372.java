package com.zcp.part5.c351to400;

import java.util.Arrays;

/**
 * @author ZCP
 * @title: C372
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/super-pow/description/
 * @date 2022/10/26 10:54
 */
public class C372 {

    public int superPow(int a, int[] b) {
        int ans = 1;
        int len = b.length;
        int x = a % 1337;
        for (int i = 0; i < len; i++) {
            int num = b[len - i - 1];
            ans = (ans * pow(x, num)) % 1337;
            x = pow(x, 10);
        }
        return ans;
    }

    private int pow(int x, int num) {
        int res = 1;
        for (int i = 0; i < num; i++) {
            res = (res * x) % 1337;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new C372().superPow(2, new int[]{1,0}));
    }
}
