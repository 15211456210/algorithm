package com.zcp.part5.c051to100;

/**
 * @author ZCP
 * @title: c065
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/valid-number/submissions/
 * @date 2022/8/29 11:11
 */
public class c065 {


    public boolean isNumber(String s) {
        int eCnt = 0;
        int eIndex = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'e' || chars[i] == 'E') {
                eCnt++;
                eIndex = i;
                if (eCnt > 1) {
                    return false;
                }
            }
        }

        if (eCnt == 0) {
            return isADecimal(s) || isAnInteger(s);
        }

        if (eCnt == 1) {
            return (isAnInteger(s.substring(0, eIndex)) || isADecimal(s.substring(0, eIndex)))
                    && isAnInteger(s.substring(eIndex + 1, s.length()));
        }
        return false;

    }

    /**
     * 是小数
     *
     * @param s
     * @return
     */
    public boolean isADecimal(String s) {
        if (s == null || "".equals(s)) {
            return false;
        }
        int idx = 0;
        if (s.charAt(0) == '-' || s.charAt(0) == '+') {
            idx++;
        }
        int dotCnt = 0;
        int numCnt = 0;
        for (; idx < s.length(); idx++) {
            if (s.charAt(idx) == '.') {
                dotCnt++;
                continue;
            }
            if (s.charAt(idx) < '0' || s.charAt(idx) > '9') {
                return false;
            } else {
                numCnt++;
            }
        }
        return dotCnt == 1 && numCnt > 0;

    }

    /**
     * 是整数
     *
     * @param s
     * @return
     */
    public boolean isAnInteger(String s) {
        if (s == null || "".equals(s)) {
            return false;
        }
        int idx = 0;
        int numCnt = 0;
        if (s.charAt(0) == '-' || s.charAt(0) == '+') {
            idx++;
        }
        for (; idx < s.length(); idx++) {
            if (s.charAt(idx) < '0' || s.charAt(idx) > '9') {
                return false;
            } else {
                numCnt++;
            }
        }
        return numCnt > 0;
    }
}
