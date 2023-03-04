package com.zcp.part5.c451to500;

/**
 * @author ZCP
 * @title: C467
 * @projectName algorithm
 * @description: https://leetcode.com/problems/unique-substrings-in-wraparound-string/submissions/
 * @date 2023/2/10 16:53
 */
public class C467 {

    public int findSubstringInWraproundString(String p) {
        int len = p.length();
        int[] dp = new int[len];
        int[] lens = new int[26];

        dp[0] = 1;
        lens[p.charAt(0) - 'a'] = 1;
        for (int i = 1; i < len; i++) {
            dp[i] = 1;
            char pre = p.charAt(i - 1);
            char c = p.charAt(i);
            if (c - pre == 1 || (c == 'a' && pre == 'z')) {
                dp[i] = dp[i - 1] + 1;
            }
            lens[c - 'a'] = Math.max(lens[c - 'a'], dp[i]);
        }

        int ans = 0;
        for (int i = 0; i < lens.length; i++) {
            ans += lens[i];
        }
        return ans;

    }
}
