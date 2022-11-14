package com.zcp.part5.c201to250;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author ZCP
 * @title: C227
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/basic-calculator-ii/
 * @date 2022/9/19 13:19
 */
public class C227 {

    static HashMap<String, Integer> map = new HashMap<>();

    static {
        map.put("+", 1);
        map.put("-", 2);
        map.put("*", 3);
        map.put("/", 3);
    }

    public int calculate(String s) {
        return simpleCalc(s.replaceAll(" ", ""));
    }


    public int simpleCalc(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        Stack<Integer> nums = new Stack<>();
        Stack<String> calcSymbol = new Stack<>();

        int index = 0;
        while (index < s.length()) {

            if (s.charAt(index) < '0' || s.charAt(index) > '9') {
                while (!calcSymbol.isEmpty() && map.get(s.charAt(index) + "") <= map.get(calcSymbol.peek())) {
                    Integer num1 = nums.isEmpty() ? 0 : nums.pop();
                    Integer num2 = nums.isEmpty() ? 0 : nums.pop();
                    if (calcSymbol.peek().equals("*")) {
                        nums.push(num1 * num2);
                    } else if (calcSymbol.peek().equals("/")) {
                        nums.push(num2 / num1);
                    } else if (calcSymbol.peek().equals("+")) {
                        nums.push(num2 + num1);
                    } else {
                        nums.push(num2 - num1);
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
                nums.push(num);
            }
        }

        while (!calcSymbol.isEmpty()) {
            String pop = calcSymbol.pop();
            Integer num1 = nums.isEmpty() ? 0 : nums.pop();
            Integer num2 = nums.isEmpty() ? 0 : nums.pop();
            if (pop.equals("+")) {
                nums.push(num1 + num2);
            } else if (pop.equals("-")) {
                nums.push(num2 - num1);
            } else if (pop.equals("*")) {
                nums.push(num1 * num2);
            } else if (pop.equals("/")) {
                nums.push(num2 / num1);
            }
        }
        return nums.peek();
    }

    public static void main(String[] args) {
        System.out.println(new C227().calculate("1*2-3/4+5*6-7*8+9/10"));
    }
}
