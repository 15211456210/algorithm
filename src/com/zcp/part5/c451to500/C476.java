package com.zcp.part5.c451to500;

/**
 * @author ZCP
 * @title: C476
 * @projectName algorithm
 * @description: https://leetcode.com/problems/number-complement/
 * @date 2023/2/20 16:23
 */
public class C476 {

    public int findComplement(int num) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int flag = (num >>> i) & 1;
            if (flag == 0) {
                ans |= (1 << i);
            }
        }
        int i = 31;
        for (; i >= 0; i--) {
            if (((ans >>> i) & 1) == 0) {
                break;
            }
        }
        return ans & ((1 << (i + 1)) - 1);
    }

    public static void main(String[] args) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((-3 >>> i) & (1));
        }

    }
}
