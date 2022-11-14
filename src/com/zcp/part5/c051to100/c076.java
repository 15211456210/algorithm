package com.zcp.part5.c051to100;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZCP
 * @title: c076
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/minimum-window-substring/submissions/
 * @date 2022/8/30 10:01
 */
public class c076 {

    public String minWindow(String s, String t) {
        String ans = "";
        int ansLen = Integer.MAX_VALUE;
        // 当前需要扣减的数
        int count = t.length();
        // 欠账表
        Map<Character, Integer> map = new HashMap<>();
        // 字符范围(l,r]
        int l = -1;
        int r = 0;
        for (int i = 0; i < t.length(); i++) {
            if (!map.containsKey(t.charAt(i))) {
                map.put(t.charAt(i), 0);
            }
            map.put(t.charAt(i), map.get(t.charAt(i)) + 1);
        }
        while (l < s.length() && r < s.length()) {
            while (count > 0 && r < s.length()) {
                char c = s.charAt(r);
                if (map.containsKey(c)) {
                    int num = map.get(c);
                    map.put(c, num - 1);
                    if (num > 0) {
                        count--;
                    }
                }
                r++;
            }

            if (count <= 0 && ansLen > r - l) {
                ansLen = r - l;
                ans = s.substring(l + 1, r);
            }


            while (count == 0) {
                l++;
                char c = s.charAt(l);
                if (map.containsKey(c)) {
                    int num = map.get(c);
                    map.put(c, num + 1);
                    if (num >= 0) {
                        count++;
                    }
                }
                if (count <= 0 && ansLen > r - l) {
                    ansLen = r - l;
                    ans = s.substring(l + 1, r);
                }
            }
        }

        return ans;

    }
}
