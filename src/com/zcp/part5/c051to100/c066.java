package com.zcp.part5.c051to100;

/**
 * @author ZCP
 * @title: c066
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/plus-one/submissions/
 * @date 2022/8/29 14:45
 */
public class c066 {


    public int[] plusOne(int[] digits) {
        int n = digits.length;
        int carry = (digits[n - 1] + 1) / 10;
        digits[n - 1] = (digits[n - 1] + 1) % 10;
        int k = 2;
        while (k <= n && carry > 0) {
            int sum = digits[n - k] + carry;
            carry = sum / 10;
            digits[n - k] = sum % 10;
            k++;
        }
        int[] ans = new int[n + 1];
        if (carry > 0) {
            System.arraycopy(digits, 0, ans, 1, n);
            ans[0] = 1;
            return ans;
        } else {
            return digits;
        }

    }
}
