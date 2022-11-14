package com.zcp.part5.c151to200;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author ZCP
 * @title: C187
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/repeated-dna-sequences/submissions/
 * @date 2022/9/13 16:15
 */
public class C187 {

    public List<String> findRepeatedDnaSequences(String s) {
        if (s == null || s.length() < 10) {
            return new ArrayList<>();
        }
        HashSet<String> ans = new HashSet<>();

        HashSet<String> set = new HashSet<>();

        int idx = 0;

        for (; idx + 10 <= s.length(); idx++) {
            String sub = s.substring(idx, idx + 10);
            if (set.contains(sub)) {
                ans.add(sub);
            } else {
                set.add(sub);
            }
        }
        return new ArrayList<>(ans);
    }

}
