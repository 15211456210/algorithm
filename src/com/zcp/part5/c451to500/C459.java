package com.zcp.part5.c451to500;

/**
 * @author ZCP
 * @title: C459
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/repeated-substring-pattern/submissions/
 * @date 2023/2/8 9:49
 */
public class C459 {

    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        for (int i = 1; i * 2 <= n; ++i) {
            if (n % i == 0) {
                boolean match = true;
                for (int j = i; j < n; ++j) {
                    if (s.charAt(j) != s.charAt(j - i)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return true;
                }
            }
        }
        return false;
    }
}
