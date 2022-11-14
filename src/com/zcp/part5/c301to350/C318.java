package com.zcp.part5.c301to350;

/**
 * @author ZCP
 * @title: C318
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/maximum-product-of-word-lengths/
 * @date 2022/10/3 23:20
 */
public class C318 {

    public int maxProduct(String[] words) {
        int len = words.length;

        int[] charMap = new int[len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                charMap[i] = charMap[i] | (1 << (words[i].charAt(j) - 'a'));
            }
        }
        int ans = 0;


        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if ((charMap[i] & charMap[j]) == 0 && words[i].length() * words[j].length() > ans) {
                    ans = words[i].length() * words[j].length();
                }
            }
        }
        return ans;
    }
}
