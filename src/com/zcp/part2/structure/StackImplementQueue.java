package com.zcp.part2.structure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 用栈实现队列
 */
public class StackImplementQueue {
    public static class MyQueue<T> {
        Stack<T> pushStack;
        Stack<T> popStack;

        public MyQueue() {
            this.pushStack = new Stack<>();
            this.popStack = new Stack<>();
        }

        /**
         * 将push栈数据 移到 pop栈
         */
        private void translate() {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }

        public T peek() {
            if (popStack.isEmpty()) {
                translate();
            }
            if (!popStack.isEmpty()) {
                return popStack.peek();
            } else {
                throw new RuntimeException("queue is empty");
            }
        }


        public T poll() {
            if (popStack.isEmpty()) {
                translate();
            }
            if (!popStack.isEmpty()) {
                return popStack.pop();
            } else {
                throw new RuntimeException("queue is empty");
            }
        }

        public void offer(T val) {
            pushStack.push(val);
        }
    }


    public static void main(String[] args) {
        int testTime = 10000000;
        int range = 500;
        Queue<Integer> queue = new LinkedList<>();
        MyQueue<Integer> myQueue = new MyQueue<>();
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int opt = 0;
            do {
                opt = (int) (Math.random() * 3);//0 offer 1 poll 2 peek
            } while (queue.size() == 0 && (opt == 1 || opt == 2));
            switch (opt) {
                case 0:
                    int val = (int) (Math.random() * range) - (int) (Math.random() * range);
                    queue.offer(val);
                    myQueue.offer(val);
                    break;
                case 1:
                    if (!queue.poll().equals(myQueue.poll())) {
                        System.out.println("出错了");
                        return;
                    }
                    break;
                case 2:
                    if (!queue.peek().equals(myQueue.peek())) {
                        System.out.println("出错了");
                        return;
                    }
                    break;
            }

        }
        System.out.println("测试结束");
    }


}
