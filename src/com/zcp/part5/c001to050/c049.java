package com.zcp.part5.c001to050;

import java.util.*;

/**
 * @author ZCP
 * @title: c049
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/group-anagrams/submissions/
 * @date 2022/8/28 15:55
 */
public class c049 {

    public List<List<String>> groupAnagrams(String[] strs) {
        int[] charNum = new int[26];
        Map<String, List<String>> ans = new HashMap<>();
        for (String s : strs) {
            Arrays.fill(charNum, 0);
            String seq = createSeqStr(charNum, s);
            if (!ans.containsKey(seq)) {
                ans.put(seq, new ArrayList<String>());
            }
            ans.get(seq).add(s);
        }

        return new ArrayList<List<String>>(ans.values());
    }

    public String createSeqStr(int[] charNum, String s) {
        for (char c : s.toCharArray()) {
            charNum[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < charNum.length; i++) {
            sb.append(i + charNum[i] + "_");
        }
        return sb.toString();
    }

}
