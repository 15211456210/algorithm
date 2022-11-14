package com.zcp.part5.c251to300;

import java.util.HashSet;
import java.util.Stack;

/**
 * @author ZCP
 * @title: C316
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/remove-duplicate-letters/
 * @date 2022/10/1 21:48
 */
public class C316 {

    public String removeDuplicateLetters(String s) {

        Stack<Character> stack = new Stack<>();
        int[] count = new int[26];
        boolean[] has = new boolean[26];

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (has[c - 'a']) {
                count[c - 'a']--;
                continue;
            }
            while (!stack.isEmpty() && stack.peek() > c && count[stack.peek() - 'a'] >= 1) {
                has[stack.pop() - 'a'] = false;
            }
            count[c - 'a']--;
            stack.push(c);
            has[c - 'a'] = true;
        }


        char[] chars = new char[stack.size()];

        for (int i = chars.length - 1; i >= 0; i--) {
            chars[i] = stack.pop();
        }
        return new String(chars);
    }
}
