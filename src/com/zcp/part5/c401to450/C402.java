package com.zcp.part5.c401to450;

/**
 * @author ZCP
 * @title: C402
 * @projectName algorithm
 * @description: https://leetcode.com/problems/remove-k-digits/
 * @date 2022/11/15 13:32
 */
public class C402 {
    public String removeKdigits(String num, int k) {
        int len = num.length();
        if (len == k) {
            return "0";
        }
        char[] next = new char[len];
        int index = 0;
        for (int i = 1; i < len; i++) {
            if (num.charAt(i) != num.charAt(i - 1)) {
                while (index < i) {
                    next[index++] = num.charAt(i);
                }
            }
        }
        while (index < len) {
            next[index++] = '#';
        }

        String ans = "";
        int i = 0;
        for (; i < len && k > 0; i++) {
            if (len - i == k) {
                return ans;
            }
            if (num.charAt(i) > next[i]) {
                k--;
                continue;
            }
            ans += num.charAt(i);
        }
        ans += num.substring(i);
        while(ans.length() > 1 && ans.charAt(0) == '0'){
            ans = ans.substring(1);
        }
        return ans;
    }

    public static void main(String[] args) {

        System.out.println(new C402().removeKdigits("1432219", 3));

    }
}
