package com.zcp.part5.c201to250;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ZCP
 * @title: C225
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/implement-stack-using-queues/submissions/
 * @date 2022/9/19 11:17
 */
public class C225 {

    class MyStack {
        Queue<Integer> q1;
        Queue<Integer> q2;

        /**
         * Initialize your data structure here.
         */
        public MyStack() {
            q1 = new LinkedList<Integer>();
            q2 = new LinkedList<Integer>();
        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            if (q1.isEmpty()) {
                q1.offer(x);
                while (!q2.isEmpty()) {
                    q1.offer(q2.poll());
                }
            } else {
                q2.offer(x);
                while (!q1.isEmpty()) {
                    q2.offer(q1.poll());
                }
            }
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            if (!q1.isEmpty()) {
                return q1.poll();
            }
            if (!q2.isEmpty()) {
                return q2.poll();
            }
            return 0;
        }

        /**
         * Get the top element.
         */
        public int top() {
            if (!q1.isEmpty()) {
                return q1.peek();
            }
            if (!q2.isEmpty()) {
                return q2.peek();
            }
            return 0;
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return q1.isEmpty() && q2.isEmpty();
        }
    }

}
