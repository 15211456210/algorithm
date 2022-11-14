package com.zcp.part5.c151to200;

import java.util.Stack;

/**
 * @author ZCP
 * @title: C155
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/min-stack/submissions/
 * @date 2022/9/8 15:52
 */
public class C155 {
    class MinStack {


        Stack<Integer> stack;
        Stack<Integer> minStack;

        public MinStack() {
            stack = new Stack<Integer>();
            minStack = new Stack<Integer>();
            minStack.push(Integer.MAX_VALUE);
        }

        public void push(int val) {
            stack.push(val);
            if (minStack.peek() > val) {
                minStack.push(val);
            } else {
                minStack.push(minStack.peek());
            }
        }

        public void pop() {
            minStack.pop();
            stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
}
