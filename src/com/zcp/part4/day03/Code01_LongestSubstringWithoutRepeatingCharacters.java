package com.zcp.part4.day03;

import java.util.Arrays;

// 本题测试链接 : https://leetcode.com/problems/longest-substring-without-repeating-characters/
public class Code01_LongestSubstringWithoutRepeatingCharacters {


    /**
     * 思路：
     * 动态规划
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
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


    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        char[] str = s.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        map[str[0]] = 0;
        int N = str.length;
        int ans = 1;
        int pre = 1;
        for (int i = 1; i < N; i++) {
            pre = Math.min(i - map[str[i]], pre + 1);
            ans = Math.max(ans, pre);
            map[str[i]] = i;
        }
        return ans;
    }

}
