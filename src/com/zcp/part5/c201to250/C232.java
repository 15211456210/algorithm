package com.zcp.part5.c201to250;

import java.util.Stack;

/**
 * @author ZCP
 * @title: C232
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/implement-queue-using-stacks/
 * @date 2022/9/19 14:48
 */
public class C232 {

    class MyQueue {

        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        int size = 0;

        public MyQueue() {

        }

        public void push(int x) {
            stack1.push(x);
            size++;
        }

        public int pop() {
            if (size > 0) {
                if (stack2.isEmpty()) {
                    while (!stack1.isEmpty()) {
                        stack2.push(stack1.pop());
                    }
                }
                size--;
                return stack2.pop();

            }
            return 0;
        }

        public int peek() {
            if (size > 0) {
                if (stack2.isEmpty()) {
                    while (!stack1.isEmpty()) {
                        stack2.push(stack1.pop());
                    }
                }
                return stack2.peek();
            }
            return 0;
        }

        public boolean empty() {
            return size > 0 ? false : true;
        }
    }

}
