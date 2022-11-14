package com.zcp.part5.c301to350;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author ZCP
 * @title: C301
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/remove-invalid-parentheses/submissions/
 * @date 2022/9/27 13:58
 */
public class C301 {

    /**
     * 思路：
     * 动态规划
     *
     * @param s
     * @return
     */
    public List<String> removeInvalidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return new ArrayList<String>();
        }
        Info info = new Info();
        process2(s.toCharArray(), 0, 0, "", info);
        return new ArrayList<>(info.set);
    }

    public class Info {
        HashSet set = new HashSet<String>();
        int maxLen = 0;

        public void add(String s) {
            if (s.length() > maxLen) {
                set.clear();
                set.add(s);
                maxLen = s.length();
            } else if (s.length() == maxLen) {
                set.add(s);
            }
        }
    }

    public void process2(char[] s, int index, int count, String path, Info info) {
        if (index == s.length) {
            if (count != 0) {
                return;
            }
            info.add(path);
            return;
        }
        if (s[index] == '(' || s[index] == ')') {
            if (s[index] == '(') {
                process2(s, index + 1, count, path, info);
                process2(s, index + 1, count + 1, path + "(", info);
            } else {
                if (count > 0) {
                    process2(s, index + 1, count, path, info);
                    process2(s, index + 1, count - 1, path + ")", info);
                } else {
                    process2(s, index + 1, count, path, info);
                }
            }
        } else {
            process2(s, index + 1, count, path + String.valueOf(s[index]), info);
        }
    }
}
