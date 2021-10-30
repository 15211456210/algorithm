package com.zcp.part2.forceRecursion;

import java.util.Stack;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/17
 * @description：递归实现一个栈的逆序 (要求不使用任何额外的空间)
 * @version:
 */
public class ReverseStack {


    public static void solution(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        Integer last = process(stack);
        solution(stack);
        stack.push(last);
    }

    /**
     * 返回栈底元素抽出来返回，其余的不变
     *
     * @param stack
     * @return
     */
    public static Integer process(Stack<Integer> stack) {
        Integer pop = stack.pop();
        if (stack.isEmpty()) {
            return pop;
        } else {
            Integer last = process(stack);
            stack.push(pop);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(2);
        stack.push(1);
        stack.push(0);
        solution(stack);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
