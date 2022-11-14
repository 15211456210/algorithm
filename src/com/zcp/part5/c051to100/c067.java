package com.zcp.part5.c051to100;

/**
 * @author ZCP
 * @title: c067
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/add-binary/submissions/
 * @date 2022/8/29 14:57
 */
public class c067 {

    public String addBinary(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (i >= 0 || j >= 0 || carry > 0) {
            int c = i >= 0 ? a.charAt(i--) - '0' : 0;
            int d = j >= 0 ? b.charAt(j--) - '0' : 0;
            sb.append((c + d + carry) % 2 + "");
            carry = (c + d + carry) / 2;
        }
        return sb.reverse().toString();
    }
}
