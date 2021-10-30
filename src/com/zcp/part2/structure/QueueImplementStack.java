package com.zcp.part2.structure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 队列实现栈
 */
public class QueueImplementStack {

    public static class MyStack<T> {
        Queue<T> a;
        Queue<T> b;
        int size;

        public MyStack() {
            this.a = new LinkedList<>();
            this.b = new LinkedList<>();
            this.size = 0;
        }

        public void push(T val) {
            if (!a.isEmpty()) {
                a.offer(val);
            } else {
                b.offer(val);
            }
            size++;
        }

        public T peek() {
            if (size == 0) {
                throw new RuntimeException("stack is empty");
            }
            if (!a.isEmpty()) {
                while (a.size() > 1) {
                    b.offer(a.poll());
                }
                T ans = a.peek();
                b.offer(a.poll());
                return ans;
            } else {
                while (b.size() > 1) {
                    a.offer(b.poll());
                }
                T ans = b.peek();
                a.offer(b.poll());
                return ans;
            }
        }

        public T pop() {
            if (size == 0) {
                throw new RuntimeException("stack is empty");
            }
            if (!a.isEmpty()) {
                while (a.size() > 1) {
                    b.offer(a.poll());
                }
                size--;
                return a.poll();
            } else {
                while (b.size() > 1) {
                    a.offer(b.poll());
                }
                size--;
                return b.poll();
            }
        }

    }

    public static void main(String[] args) {
        int testTime = 1000000;
        int range = 500;
        Stack<Integer> stack = new Stack();
        MyStack<Integer> myStack = new MyStack<>();
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int opt = 0;//0:push  1:pop 2:peek
            do {
                opt = (int) (Math.random() * 3);
            } while (stack.size() == 0 && (opt == 1 || opt == 2));
            switch (opt){
                case 0:
                    int val = (int)(Math.random()*range) - (int)(Math.random()*range);
                    stack.push(val);
                    myStack.push(val);
                    break;
                case 1:
                    if(!stack.pop().equals(myStack.pop())){
                        System.out.println("出错了");
                        break;
                    }
                    break;
                case 2:
                    if(!stack.peek().equals(myStack.peek())){
                        System.out.println("出错了");
                        break;
                    }
                    break;
            }
        }
        System.out.println("测试结束");
    }


}
