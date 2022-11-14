package com.zcp.part5.c001to050;

import java.util.Arrays;

/**
 * @author ZCP
 * @title: c043
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/multiply-strings/submissions/
 * @date 2022/8/27 15:04
 */
public class c043 {

    static String[] map = new String[10];

    public String multiply(String num1, String num2) {
        init(num1);
        int len = num2.length();
        char[] num2s = num2.toCharArray();

        String ans = "";

        for (int i = 0; i < len; i++) {
            int mul = num2s[i] - '0';
            int zCnt = len - i - 1;
            String n2 = map[mul];
            if (!"0".equals(n2)){
                for (int k = 0; k < zCnt; k++) {
                    n2 += '0';
                }
            }
            ans = add(ans, n2);
        }
        return ans;
    }

    private void init(String num1) {
        map[0] = "0";
        map[1] = num1;
        for (int i = 2; i <= 9; i++) {
            map[i] = add(map[i - 1], num1);
        }

    }

    private String add(String num1, String num2) {
        int a = num1.length() - 1;
        int b = num2.length() - 1;
        int carry = 0;
        String sum = "";
        while (a >= 0 || b >= 0 || carry != 0) {
            int n1 = a >= 0 ? num1.charAt(a--) - '0' : 0;
            int n2 = b >= 0 ? num2.charAt(b--) - '0' : 0;
            int s = n1 + n2 + carry;
            carry = s / 10;
            sum = (char) ((s % 10) + '0') + sum;
        }
        return sum;
    }
}
