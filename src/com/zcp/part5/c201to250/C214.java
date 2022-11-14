package com.zcp.part5.c201to250;

/**
 * @author ZCP
 * @title: C214
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/shortest-palindrome/submissions/
 * @date 2022/9/17 17:26
 */
public class C214 {

    public String shortestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        char[] str = getCompareStr(s);
        int maxRIndex = 0;//扩到最右侧的点
        int maxTargetIndex = 0;//半径最大的且包含开头字符的最右侧点
        int R = -1;
        int[] rads = new int[str.length];
        for (int i = 0; i < rads.length; i++) {
            int l = i;
            int r = i;
            int rad = R <= i ? 1 : Math.min(rads[2 * maxRIndex - i], R - i);
            while ((l = i - rad) >= 0 && (r = i + rad) < rads.length && str[l] == str[r]) {
                rad++;
            }
            rads[i] = rad;
            if (i + rad > R) {
                R = i + rad;
                maxRIndex = i;
                if (rad - i - 1 == 0) {
                    maxTargetIndex = i + rad;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = str.length - 1; i >= maxTargetIndex; i--) {
            sb.append(str[i]);
        }
        sb.append(str);
        return new String(sb).replace("#", "");
    }

    /**
     * 将原字符串转变成操作字符串
     * “abc” -> “#a#b#c#”
     *
     * @param s
     * @return
     */
    private char[] getCompareStr(String s) {
        char[] charArray = s.toCharArray();
        int newLength = charArray.length * 2 + 1;
        char[] compareArr = new char[newLength];

        for (int i = 0; i < newLength; i++) {
            if (i % 2 == 0) {
                compareArr[i] = '#';
            } else {
                compareArr[i] = charArray[(i - 1) >> 1];
            }
        }
        return compareArr;
    }
}
