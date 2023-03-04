package com.zcp.part5.c451to500;

/**
 * @author ZCP
 * @title: C479
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/largest-palindrome-product/
 * @date 2023/3/1 15:13
 */
public class C479 {

    public int largestPalindrome(int n) {

        if (n == 1) {
            return 9;
        }

        long upper = (long) (Math.pow(10, n) - 1);

        for (long left = upper; left > 0; left--) {
            // 构建回文数字
            long p = left;
            for (long l = left; l > 0; l /= 10) {
                p = p * 10 + l % 10;
            }
            for (long x = upper; x * x >= p; x--) {
                if (p % x == 0) {
                    return (int) (p % 1337);
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int i = new C479().largestPalindrome(2);
        System.out.println(
                i
        );
    }
}
