package com.zcp.part5.c201to250;

import java.util.Arrays;

/**
 * @author ZCP
 * @title: C242
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/valid-anagram/submissions/
 * @date 2022/9/22 10:22
 */
public class C242 {

    static int[] cnt = new int[26];

    public boolean isAnagram(String s, String t) {
        Arrays.fill(cnt, 0);

        for (int i = 0; i < s.length(); i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            cnt[t.charAt(i) - 'a']--;
        }

        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i] != 0) {
                return false;
            }
        }

        return true;

    }
}
