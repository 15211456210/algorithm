package com.zcp.part5.c101to150;

import java.util.Stack;

/**
 * @author ZCP
 * @title: C150
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/evaluate-reverse-polish-notation/submissions/
 * @date 2022/9/7 11:16
 */
public class C150 {

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (tokens[i].equals("-")) {
                stack.push(-stack.pop() + stack.pop());
            } else if (tokens[i].equals("/")) {
                Integer first = stack.pop();
                Integer second = stack.pop();
                stack.push(second / first);
            } else if (tokens[i].equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else {
                stack.push(Integer.valueOf(tokens[i]));
            }
        }

        return stack.pop();
    }
}
