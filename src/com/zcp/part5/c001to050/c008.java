package com.zcp.part5.c001to050;

/**
 * @author ZCP
 * @title: c008
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/string-to-integer-atoi/
 * @date 2022/8/25 13:29
 */
public class c008 {

    public int myAtoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int num = 0;
        int index = -1;
        int n1 = Integer.MAX_VALUE / 10;
        int n2 = Integer.MAX_VALUE % 10;
        while (index + 1 < s.length() && s.charAt(++index) == ' ') {
        }

        boolean isPositive = s.charAt(index) != '-';
        index += (s.charAt(index) == '-' || s.charAt(index) == '+') ? 1 : 0;
        while (index < s.length()) {
            char cur = s.charAt(index);
            if (cur >= '0' && cur <= '9') {
                if (isPositive) {
                    if (num > n1 || (num == n1 && cur - '0' > 7)) {
                        return Integer.MAX_VALUE;
                    }

                } else {
                    if (num > n1 || (num == n1 && cur - '0' > 8)) {
                        return Integer.MIN_VALUE;
                    }

                }
                num = num * 10 + cur - '0';
                index++;
            } else {
                break;
            }
        }
        return isPositive ? num : -num;
    }
}
