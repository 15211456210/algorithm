package com.zcp.part2.fib;

import java.io.FileInputStream;

/**
 * @author ：ZCP
 * @date ：Created in 2021/10/5
 * @description：[[斐波那契数列矩阵乘法方式的实现]] https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/
 * @version:
 */
public class Fib {
//    public int fib(int n) {
//        if (n == 0) {
//            return 0;
//        }
//        if (n == 1) {
//            return 1;
//        }
//        int n1 = 0;
//        int n2 = 1;
//        for (int i = 0; i < n - 1; i++) {
//            int ans = n1 + n2;
//            n1 = n2;
//            n2 = ans% 1000000007;
//        }
//        return n2;
//    }

    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int n1 = 0;
        int n2 = 1;
        for (int i = 0; i < n - 1; i++) {
            int ans = n1 + n2;
            n1 = n2;
            n2 = ans% 1000000007;
        }
        return n2;
    }

    public static void main(String[] args) {
        Fib fib = new Fib();
        int r = fib.fib(45);
        System.out.println(r);
    }
}
