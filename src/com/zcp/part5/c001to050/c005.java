package com.zcp.part5.c001to050;

/**
 * @author ZCP
 * @title: c005
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/longest-palindromic-substring/submissions/
 * @date 2022/8/25 11:31
 */
public class c005 {

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return null;
        }
        char[] str = getCompareStr(s);
        int maxRIndex = 0;//扩到最右侧的点
        int maxRad = Integer.MIN_VALUE;
        int maxRadIndex = 0;//半径最大的点
        int R = -1;
        int[] rads = new int[str.length];
        for (int i = 0; i < rads.length; i++) {
//            int rad = 1;
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
                if(maxRad < rad){
                    maxRad = rad;
                    maxRadIndex = i;
                }
            }
        }

        int beginIndex = maxRadIndex - maxRad + 1;
        int endIndex = maxRadIndex + maxRad;
        return new String(str).substring(beginIndex,endIndex).replace("#","");
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
