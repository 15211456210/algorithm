package com.zcp.part5.c001to050;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author ZCP
 * @title: c020
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/valid-parentheses/submissions/
 * @date 2022/8/25 15:38
 */
public class c020 {

    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                stack.push(s.charAt(i));
                continue;
            }
            if (stack.isEmpty() || !stack.pop().equals(map.get(s.charAt(i)))) {
                return false;
            }

        }
        return stack.isEmpty();
    }
}
