package com.zcp.part5.c201to250;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ZCP
 * @title: C241
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/different-ways-to-add-parentheses/submissions/
 * @date 2022/9/22 9:30
 */
public class C241 {

    public List<Integer> diffWaysToCompute(String expression) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (expression.length() == 0) {
            return ans;
        }

        boolean hasOpt = false;
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c < '0' || c > '9') {
                hasOpt = true;
                List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                List<Integer> right = diffWaysToCompute(expression.substring(i + 1));
                for (int l = 0; l < left.size(); l++) {
                    for (int r = 0; r < right.size(); r++) {
                        if (c == '+') {
                            ans.add(left.get(l) + right.get(r));
                        } else if (c == '-') {
                            ans.add(left.get(l) - right.get(r));
                        } else if (c == '*') {
                            ans.add(left.get(l) * right.get(r));
                        } else {
                            ans.add(left.get(l) / right.get(r));
                        }
                    }
                }

            }
        }
        if (!hasOpt) {
            ans.add(Integer.valueOf(expression));
        }
        return ans;
    }

//    public List<Integer> diffWaysToCompute(String expression) {
//
//        ArrayList<Integer> nums = new ArrayList<>();
//        ArrayList<String> opts = new ArrayList<>();
//
//
//        for (int i = 0; i < expression.length(); ) {
//            if (expression.charAt(i) < '0' || expression.charAt(i) > '9') {
//                opts.add(expression.charAt(i) + "");
//                i++;
//            } else {
//                int end = i + 1;
//                while (end < expression.length() && expression.charAt(end) >= '0' && expression.charAt(end) <= '9') {
//                    end++;
//                }
//                nums.add(Integer.valueOf(expression.substring(i, end)));
//                i = end;
//            }
//
//        }
//        List<Integer> ans = new ArrayList<>();
//
//        process(nums, opts, ans);
//        return ans;
//
//    }
//
//    public void process(List<Integer> nums, List<String> opts, List<Integer> ans) {
//        if (opts.size() == 0) {
//            ans.add(Integer.valueOf(nums.get(0)));
//            return;
//        }
//        int size = opts.size();
//        for (int i = 0; i < size; i++) {
//            String opt = opts.remove(i);
//            Integer num1 = nums.remove(i);
//            Integer num2 = nums.remove(i);
//            if (opt.equals("+")) {
//                nums.add(i, num1 + num2);
//            } else if (opt.equals("-")) {
//                nums.add(i, num1 - num2);
//            } else if (opt.equals("*")) {
//                nums.add(i, num1 * num2);
//            } else {
//                nums.add(i, num1 / num2);
//            }
//            process(nums, opts, ans);
//            nums.remove(i);
//            nums.add(i, num2);
//            nums.add(i, num1);
//            opts.add(i, opt);
//        }
//    }

    public static void main(String[] args) {
        System.out.println(new C241().diffWaysToCompute("2*3-4*5"));
    }
}
