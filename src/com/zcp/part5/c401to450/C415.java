package com.zcp.part5.c401to450;

/**
 * @author ZCP
 * @title: C415
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/add-strings/
 * @date 2022/11/24 8:54
 */
public class C415 {

    public String addStrings(String num1, String num2) {
        int a = num1.length() - 1;
        int b = num2.length() - 1;
        int carry = 0;
        String ans = "";
        while (a >= 0 || b >= 0 || carry > 0) {
            int numa = a >= 0 ? num1.charAt(a--) - '0' : 0;
            int numb = b >= 0 ? num2.charAt(b--) - '0' : 0;

            ans = (numa + numb + carry) % 10 + ans;
            carry = (numa + numb + carry) / 10;

        }
        return ans;

    }

    public static void main(String[] args) {
        new C415().addStrings("111", "23");
    }
}
