package com.zcp.part5.c451to500;

import java.util.*;

/**
 * @author ZCP
 * @title: C472
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/concatenated-words/
 * @date 2023/2/15 16:25
 */
public class C472 {

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> ans = new ArrayList<>();

        HashSet<String> set = new HashSet<>();
        for (String word : words) {
            set.add(word);
        }
        for (String word : words) {
            set.remove(word);
            if (process(word, set)) {
                ans.add(word);
            }
            set.add(word);
        }
        return ans;
    }

    private boolean process(String word, HashSet<String> set) {
        if (word.length() == 0) {
            return true;
        }

        int len = word.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(word.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[len];
    }


}
