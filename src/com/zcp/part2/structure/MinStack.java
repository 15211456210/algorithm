package com.zcp.part2.structure;

import java.util.Stack;

/**
 * 最小栈
 */
public class MinStack {

    public static class MyMinStack {
        Stack<Integer> stack;
        Stack<Integer> help;

        public MyMinStack() {
            this.stack = new Stack<>();
            this.help = new Stack<>();
        }

        public Integer peek() {
            return stack.peek();
        }

        public void push(Integer val) {
            stack.push(val);
            help.push(help.size() == 0 ? val : Math.min(val, help.peek()));
        }

        public Integer pop() {
            Integer ans = stack.pop();
            help.pop();
            return ans;
        }

        public Integer getMin() {
            return help.peek();
        }
    }

    public static void main(String[] args) {
        MyMinStack myMinStack = new MyMinStack();
        myMinStack.push(6);
        myMinStack.push(4);
        myMinStack.push(2);
        myMinStack.push(5);
        System.out.println(myMinStack.getMin());
        myMinStack.pop();
        System.out.println(myMinStack.getMin());
        myMinStack.pop();
        System.out.println(myMinStack.getMin());
        myMinStack.pop();
        System.out.println(myMinStack.getMin());
        myMinStack.pop();
    }

}
