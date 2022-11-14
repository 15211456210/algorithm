package com.zcp.part5.c251to300;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author ZCP
 * @title: C290
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/word-pattern/submissions/
 * @date 2022/9/26 17:01
 */
public class C290 {

    public boolean wordPattern(String pattern, String s) {
        String[] ss = s.split(" ");
        HashMap<Character, String> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        if (pattern.length() != ss.length) {
            return false;
        }
        for (int i = 0; i < ss.length; i++) {
            char c = pattern.charAt(i);
            if (!map.containsKey(c)) {
                if (set.contains(ss[i])) {
                    return false;
                }
                set.add(ss[i]);
                map.put(c, ss[i]);
            } else {
                if (!map.get(c).equals(ss[i])) {
                    return false;
                }
            }
        }
        return true;
    }
}
