package com.zcp.part5.c401to450;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ZCP
 * @title: C409
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/longest-palindrome/description/
 * @date 2022/11/21 15:12
 */
public class C409 {

    public int longestPalindrome(String s) {
        Map<Character, Integer> countMap = new HashMap<>();
        int ans;
        for (int i = 0; i < s.length(); i++) {
            int count = countMap.getOrDefault(s.charAt(i), 0);
            countMap.put(s.charAt(i), count + 1);
        }
        ans = countMap.values().stream().filter(v -> v % 2 == 0).mapToInt(v -> v).sum();
        int sum = 0;
        int singleCnt = 0;
        for (Integer v : countMap.values()) {
            if (v % 2 == 1) {
                sum += v - 1;
                singleCnt++;
            }
        }
        return singleCnt > 0 ? ans + sum + 1 : ans;
    }

    public static void main(String[] args) {
        System.out.println(new C409().longestPalindrome("ccasc"));
    }
}
