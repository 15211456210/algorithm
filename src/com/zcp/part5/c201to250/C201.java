package com.zcp.part5.c201to250;

/**
 * @author ZCP
 * @title: C201
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/bitwise-and-of-numbers-range/
 * @date 2022/9/15 14:31
 */
public class C201 {

    public int rangeBitwiseAnd(int left, int right) {
        int ans = 0;
        for (int i = 0; i <= 31; i++) {
            int n = 1;
            for (int num = left; num <= right; num++) {
                if ((1 & (num >>> i)) == 0) {
                    n = 0;
                    break;
                }
                if (num == Integer.MAX_VALUE) {
                    break;
                }
            }
            ans |= n << i;
        }
        return ans;
    }

    public static void main(String[] args) {

        System.out.println(new C201().rangeBitwiseAnd(2147483646
                , 2147483647));
    }
}
