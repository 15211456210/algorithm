package com.zcp.part5.c101to150;

/**
 * @author ZCP
 * @title: C125
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/valid-palindrome/submissions/
 * @date 2022/9/2 10:30
 */
public class C125 {

    public boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            while (l < s.length() && !((s.charAt(l) >= 'A' && s.charAt(l) <= 'Z') || (s.charAt(l) >= 'a' && s.charAt(l) <= 'z') || s.charAt(l) >= '0' && s.charAt(l) <= '9')) {
                l++;
            }
            while (r >= 0 && !((s.charAt(r) >= 'A' && s.charAt(r) <= 'Z') || (s.charAt(r) >= 'a' && s.charAt(r) <= 'z') || (s.charAt(r) >= '0' && s.charAt(r) <= '9'))) {
                r--;
            }
            if (l < s.length()) {
                char cl = s.charAt(l);
                char cr = s.charAt(r);
                cl = (char) (cl - (cl >= 'a' && cl <= 'z' ? 32 : 0));
                cr = (char) (cr - (cr >= 'a' && cr <= 'z' ? 32 : 0));
                if (cl == cr) {
                    l++;
                    r--;
                } else {
                    return false;
                }
            }

        }
        return true;
    }
}
