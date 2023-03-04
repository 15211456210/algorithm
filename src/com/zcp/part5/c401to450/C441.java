package com.zcp.part5.c401to450;

/**
 * @author ZCP
 * @title: C441
 * @projectName algorithm
 * @description: https://leetcode.com/problems/arranging-coins/
 * @date 2023/2/1 11:09
 */
public class C441 {
    public int arrangeCoins(int n) {
        if (n == 0) {
            return 0;
        }
        long level = 1;
        long num = (level+2)*(level+1) / 2;
        while (n >= num) {
            level++;
            num = (level+2)*(level+1) / 2;
        }
        return (int)level;
    }

    public static void main(String[] args) {
        System.out.println(new C441().arrangeCoins(1804289383));
    }
}
