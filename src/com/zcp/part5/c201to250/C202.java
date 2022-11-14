package com.zcp.part5.c201to250;

import java.util.HashSet;

/**
 * @author ZCP
 * @title: C202
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/happy-number/
 * @date 2022/9/15 14:51
 */
public class C202 {

    public boolean isHappy(int n) {
        HashSet<Integer> appear = new HashSet<>();

        while (!appear.contains(n) && n != 1) {
            appear.add(n);
            int next = 0;
            while (n > 0) {
                next += (n % 10) * (n % 10);
                n /= 10;
            }
            n = next;
        }

        return n == 1;

    }

    public static void main(String[] args) {
        System.out.println(new C202().isHappy(19));
    }
}
