package com.zcp.part5.c251to300;

import java.util.*;

/**
 * @author ZCP
 * @title: C282
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/expression-add-operators/
 * @date 2022/9/25 12:13
 */
public class C282 {

    public List<String> addOperators(String num, int target) {
        ArrayList<String> ans = new ArrayList<>();
        process(num, 1, target, ans);
        return ans;
    }

    public void process(String expression, int index, int target, List<String> ans) {
        if (index >= expression.length()) {
            if (calc(expression) == target) {
                ans.add(expression);
            }
            return;
        }
        process(expression, index + 1, target, ans);
        process(expression.substring(0, index) + "+" + expression.substring(index), index + 2, target, ans);
        process(expression.substring(0, index) + "-" + expression.substring(index), index + 2, target, ans);
        process(expression.substring(0, index) + "*" + expression.substring(index), index + 2, target, ans);

    }


    static Map<Character, Integer> priority = new HashMap<>();

    static {
        priority.put('+', 0);
        priority.put('-', 1);
        priority.put('*', 2);
    }


    public int calc(String s) {
        Stack<Integer> nums = new Stack<>();
        Stack<Character> opts = new Stack<>();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') {
                if (!opts.isEmpty() && priority.get(opts.peek()) >= priority.get(c)) {
                    Character pop = opts.pop();
                    int num1 = nums.isEmpty() ? 0 : nums.pop();
                    int num2 = nums.isEmpty() ? 0 : nums.pop();
                    if (pop == '*') {
                        nums.push(num1 * num2);
                    } else if (pop == '-') {
                        nums.push(num2 - num1);
                    } else {
                        nums.push(num1 + num2);
                    }
                }
                opts.push(c);
                i++;
            } else {
                boolean hasPreZero = (c == '0');
                int num = 0;
                int idx = i;
                while (idx < s.length() && s.charAt(idx) >= '0' && s.charAt(idx) <= '9') {
                    num = num * 10 + (s.charAt(idx) - '0');
                    idx++;
                }
                if (hasPreZero && (num != 0 || idx - i != 1)) {
                    // 排除前导零
                    return Integer.MAX_VALUE;
                }
                i = idx;
                nums.push(num);
            }
        }
        while (!opts.isEmpty()) {
            Character pop = opts.pop();
            int num1 = nums.isEmpty() ? 0 : nums.pop();
            int num2 = nums.isEmpty() ? 0 : nums.pop();
            if (pop == '*') {
                nums.push(num1 * num2);
            } else if (pop == '-') {
                nums.push(num2 - num1);
            } else {
                nums.push(num1 + num2);
            }
        }
        return nums.pop();
    }


    public static void main(String[] args) {
//        int calc = new C282().calc("23-8*8");
        List<String> strings = new C282().addOperators("123", 6);

        System.out.println(strings);
    }
}
