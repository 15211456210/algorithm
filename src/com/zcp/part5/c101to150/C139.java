package com.zcp.part5.c101to150;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author ZCP
 * @title: C139
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/word-break/submissions/
 * @date 2022/9/7 9:35
 */
public class C139 {

    public boolean wordBreak(String s, List<String> wordDict) {
        HashMap<Character, List<String>> dict = new HashMap<>();
        for (String word : wordDict) {
            char head = word.charAt(0);
            if (!dict.containsKey(head)) {
                dict.put(head, new ArrayList<String>());
            }
            dict.get(head).add(word);
        }
        int[] dp = new int[s.length() + 1];
        return fun(s.toCharArray(), 0, dict, dp);
    }

    public boolean fun(char[] s, int i, HashMap<Character, List<String>> dict, int[] dp) {
        if (i == s.length) {
            dp[i] = 1;
            return true;
        }
        if (dp[i] != 0) {
            return dp[i] == 1;
        }
        boolean ans = false;
        char head = s[i];
        int maxLen = s.length - i;
        List<String> candicates = dict.get(head);
        if (candicates != null) {
            for (String word : candicates) {
                if (word.length() <= maxLen && word.equals(new String(s, i, word.length()))) {
                    ans |= fun(s, i + word.length(), dict, dp);
                }
                if (ans) {
                    break;
                }
            }
        }
        dp[i] = ans ? 1 : -1;
        return ans;
    }
}
