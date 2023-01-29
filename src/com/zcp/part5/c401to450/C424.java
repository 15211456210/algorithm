package com.zcp.part5.c401to450;

/**
 * @author ZCP
 * @title: C424
 * @projectName algorithm
 * @description: https://leetcode.com/problems/longest-repeating-character-replacement/
 * @date 2023/1/24 12:00
 */
public class C424 {


    public int characterReplacement(String s, int k) {
        // [left,right]
        int left = 0;
        int right = 0;
        int maxLen = 1;
        int[] charCnt = new int[26];
        charCnt[s.charAt(0) - 'A']++;
        while (right < s.length()) {
            if (right - left + 1 - maxSize(charCnt) > k) {
                charCnt[s.charAt(left++) - 'A']--;
            } else {
                maxLen = Math.max(maxLen, (right++) - left + 1);
                if (right < s.length()) {
                    charCnt[s.charAt(right) - 'A']++;
                }
            }

        }
        return maxLen;
    }

    private int maxSize(int[] charCnt) {
        int maxSize = 0;
        for (int i = 0; i < charCnt.length; i++) {
            maxSize = Math.max(maxSize, charCnt[i]);
        }
        return maxSize;
    }

    public static void main(String[] args) {
        System.out.println(new C424().characterReplacement("ABBB", 2));
    }

//    public int characterReplacement(String s, int k) {
//        //[left,right)
//        char[] chars = s.toCharArray();
//        int maxLen = getMaxLen(chars, k);
//        int length = s.length();
//        for (int i = 0; i < length / 2; i++) {
//            char tmp = chars[i];
//            chars[i] = chars[length - i - 1];
//            chars[length - i - 1] = tmp;
//        }
//        return Math.max(maxLen, getMaxLen(chars, k));
//    }
//
//    private int getMaxLen(char[] s, int k) {
//        int ks = k;
//        int left = 0, right = 1;
//        int maxLen = 1;
//        char curChar = s[0];
//        boolean firstChange = true;
//        int firstIdx = 0;
//        while (right < s.length) {
//            if (curChar == s[right]) {
//                right++;
//                continue;
//            } else {
//                if (k > 0) {
//                    if (firstChange) {
//                        firstIdx = right;
//                        firstChange = false;
//                    }
//                    k--;
//                    right++;
//                } else {
//                    maxLen = Math.max(maxLen, right - left);
//                    k = ks;
//                    left = firstIdx > left ? firstIdx : right;
//                    right = left + 1;
//                    curChar = s[left];
//                    firstChange = true;
//                }
//            }
//        }
//        maxLen = Math.max(maxLen, right - left);
//        return maxLen;
//    }
}
