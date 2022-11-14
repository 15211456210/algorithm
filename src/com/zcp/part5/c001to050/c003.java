package com.zcp.part5.c001to050;

import java.util.Arrays;

/**
 * @author ZCP
 * @title: c003
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 * @date 2022/8/25 11:30
 */
public class c003 {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int[] lastAppearLocate = new int[256];
        int preLength = 0;
        int ans = 0;
        Arrays.fill(lastAppearLocate, -1);
        for (int i = 0; i < chars.length; i++) {
            preLength = lastAppearLocate[chars[i]] == -1 ? preLength + 1 : Math.min(preLength + 1, i - lastAppearLocate[chars[i]]);
            ans = Math.max(ans, preLength);
            lastAppearLocate[chars[i]] = i;//更新chars[i]字符最后出现的位置
        }
        return ans;
    }
}
