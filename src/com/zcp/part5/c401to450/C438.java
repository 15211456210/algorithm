package com.zcp.part5.c401to450;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author ZCP
 * @title: C438
 * @projectName algorithm
 * @description: https://leetcode.com/problems/find-all-anagrams-in-a-string/
 * @date 2023/1/31 13:33
 */
public class C438 {

    public List<Integer> findAnagrams(String s, String p) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (p.length() > s.length()) {
            return ans;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int len = p.length();
        for (int i = 0; i < len; i++) {
            char c = p.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // [left,right)
        int left = 0, right = 0;
        while (right < s.length()) {
            if (right - left == len) {
                char lc = s.charAt(left++);
                map.put(lc, map.getOrDefault(lc, 0) + 1);
                continue;
            }
            if (right - left < len) {
                char c = s.charAt(right);
                if (map.getOrDefault(c, 0) == 0) {
                    char lc = s.charAt(left++);
                    map.put(lc, map.getOrDefault(lc, 0) + 1);
                    continue;
                } else {
                    map.put(c, map.get(c) - 1);
                    if (++right - left == len) {
                        ans.add(left);
                        continue;
                    }
                }
            }
        }
        return ans;

    }
}
