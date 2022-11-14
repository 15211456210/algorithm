package com.zcp.part5.c151to200;

import java.util.Stack;

/**
 * @author ZCP
 * @title: C151
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/reverse-words-in-a-string/submissions/
 * @date 2022/9/7 13:22
 */
public class C151 {
    public String reverseWords(String s) {
        Stack<String> stack = new Stack<String>();


        char[] chars = s.toCharArray();
        int begin = 0;
        while (begin < chars.length && chars[begin] == ' ') {
            begin++;
        }
        int end;
        for (; begin < chars.length; ) {
            end = begin;
            while (end < chars.length && chars[end] != ' ') {
                end++;
            }
            stack.push(s.substring(begin, end));
            while (end < chars.length && chars[end] == ' ') {
                end++;
            }
            begin = end;
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            String pop = stack.pop();
            if (stack.size() == 0) {
                sb.append(pop);
            } else {
                sb.append(pop).append(" ");
            }
        }

        return sb.toString();

    }


    public static void main(String[] args) {
        System.out.println(new C151().reverseWords("the sky is blue"));
    }
}
