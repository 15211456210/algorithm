package com.zcp.part5.c051to100;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author ZCP
 * @title: c071
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/simplify-path/submissions/
 * @date 2022/8/30 8:43
 */
public class c071 {

    public String simplifyPath(String path) {
        String[] ps = path.split("/");
        LinkedList<String> dueue = new LinkedList<>();
        for (int i = 0; i < ps.length; i++) {
            if (".".equals(ps[i]) || "".equals(ps[i])) {
                continue;
            }

            if ("..".equals(ps[i])) {
                if (!dueue.isEmpty()) {
                    dueue.pollLast();
                }
                continue;
            }

            dueue.offerLast(ps[i]);
        }

        StringBuilder ans = new StringBuilder();


        while (!dueue.isEmpty()) {
            ans.append("/");
            ans.append(dueue.pollFirst());
        }
        if (ans.length() == 0) {
            ans.append("/");
        }

        return ans.toString();
    }
}
