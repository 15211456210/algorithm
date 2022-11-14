package com.zcp.part5.c201to250;

import java.util.Stack;

/**
 * @author ZCP
 * @title: C224
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/basic-calculator/
 * @date 2022/9/18 18:29
 */
public class C224 {


    class Node {
        int val;
        String sVal;
        boolean expression;

        public Node(int val, String sVal, boolean exp) {
            this.val = val;
            this.sVal = sVal;
            this.expression = exp;

        }
    }

    public int calculate(String s) {
        return simpleCalc(s.replaceAll(" ", ""));
    }

    /**
     * 不带括号的简单计算
     * 只包含 + - * /
     *
     * @return
     */
    public int simpleCalc(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        Stack<Node> nums = new Stack<>();
        Stack<String> calcSymbol = new Stack<>();

        int index = 0;
        while (index < s.length()) {
            if (s.charAt(index) == '(') {

                int c = 0;
                int i = index;
                while (i < s.length()) {
                    if (s.charAt(i) == '(') {
                        c++;
                    } else if (s.charAt(i) == ')') {
                        c--;
                        if (c == 0) {
                            nums.push(new Node(0, s.substring(index + 1, i), true));
                            break;
                        }
                    }
                    i++;
                }
                index = i + 1;
            } else {
                if (s.charAt(index) < '0' || s.charAt(index) > '9') {
                    if (!calcSymbol.isEmpty() && (calcSymbol.peek().equals("*") || calcSymbol.peek().equals("/") || calcSymbol.peek().equals("-"))) {
                        Node n1 = nums.isEmpty() ? new Node(0, null, false) : nums.pop();
                        Node n2 = nums.isEmpty() ? new Node(0, null, false) : nums.pop();
                        int num1 = n1.expression ? simpleCalc(n1.sVal) : n1.val;
                        int num2 = n2.expression ? simpleCalc(n2.sVal) : n2.val;
                        if (calcSymbol.peek().equals("*")) {
                            nums.push(new Node(num1 * num2, null, false));
                        } else if (calcSymbol.peek().equals("/")) {
                            nums.push(new Node(num2 / num1, null, false));
                        } else {
                            nums.push(new Node(num2 - num1, null, false));
                        }
                        calcSymbol.pop();
                    }
                    calcSymbol.push(s.substring(index, index + 1));
                    index++;
                } else {
                    int num = 0;
                    while (index < s.length() && s.charAt(index) >= '0' && s.charAt(index) <= '9') {
                        num = num * 10 + (s.charAt(index) - '0');
                        index++;
                    }
                    nums.push(new Node(num, null, false));
                }
            }

        }

        while (!calcSymbol.isEmpty()) {
            String pop = calcSymbol.pop();
            Node n1 = nums.isEmpty() ? new Node(0, null, false) : nums.pop();
            Node n2 = nums.isEmpty() ? new Node(0, null, false) : nums.pop();
            int num1 = n1.expression ? simpleCalc(n1.sVal) : n1.val;
            int num2 = n2.expression ? simpleCalc(n2.sVal) : n2.val;
            if (pop.equals("+")) {
                nums.push(new Node(num1 + num2, null, false));
            } else if (pop.equals("-")) {
                nums.push(new Node(num2 - num1, null, false));
            } else if (pop.equals("*")) {
                nums.push(new Node(num1 * num2, null, false));
            } else if (pop.equals("/")) {
                nums.push(new Node(num2 / num1, null, false));
            }
        }
        return nums.peek().expression ? simpleCalc(nums.peek().sVal) : nums.peek().val;
    }

    public static void main(String[] args) {
        int calculate = new C224().calculate("1*2-3/4+5*6-7*8+9/10");
        System.out.println(calculate);
    }

}
