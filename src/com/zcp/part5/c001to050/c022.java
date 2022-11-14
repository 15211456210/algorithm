package com.zcp.part5.c001to050;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZCP
 * @title: c022
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/generate-parentheses/submissions/
 * @date 2022/8/25 15:39
 */
public class c022 {

    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        fun(0, 0, new char[2 * n], list);
        return list;
    }

    /**
     * index:当前来到的位置
     * remain：剩下多少个左括号需要匹配
     * path：当前选择路径
     * list：结果
     */
    public void fun(int index, int remain, char[] path, List<String> list) {
        if (remain < 0) {
            return;
        }
        if (index == path.length) {
            if (remain == 0) {
                list.add(new String(path));
            }
            return;
        }
        // 还没走完
        // p1 填左括号
        path[index] = '(';
        fun(index + 1, remain + 1, path, list);
        path[index] = ')';
        fun(index + 1, remain - 1, path, list);
    }
}
