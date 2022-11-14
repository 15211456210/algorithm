package com.zcp.part5.c201to250;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZCP
 * @title: C205
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/isomorphic-strings/submissions/
 * @date 2022/9/16 9:03
 */
public class C205 {

    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int len = s.length();
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            if (map.containsKey(s.charAt(i))) {
                if (!map.get(s.charAt(i)).equals(t.charAt(i))) {
                    return false;
                }
            } else {
                if (map.values().contains(t.charAt(i))) {
                    return false;
                }
                map.put(s.charAt(i), t.charAt(i));
            }
        }
        return true;

    }
}
