package com.zcp.part5.c301to350;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ZCP
 * @title: C345
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/reverse-vowels-of-a-string/description/
 * @date 2022/10/20 9:30
 */
public class C345 {

    // 'a'、'e'、'i'、'o'、'u'
    static Set<Character> set = new HashSet<>();

    static {
        set.add('a');
        set.add('A');
        set.add('e');
        set.add('E');
        set.add('i');
        set.add('I');
        set.add('o');
        set.add('O');
        set.add('u');
        set.add('U');
    }

    public String reverseVowels(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();

        int len = chars.length;
        int l = 0, r = len - 1;
        char tmp;
        while (l < r) {
            while (l < len && !set.contains(chars[l])) {
                l++;
            }
            while (r > l && !set.contains(chars[r])) {
                r--;
            }

            if (l < r) {
                tmp = chars[l];
                chars[l++] = chars[r];
                chars[r--] = tmp;
            }
        }
        return new String(chars);

    }
}
