package com.zcp.part2.kmp;

import java.util.Arrays;

/**
 * @author ：ZCP
 * @date ：Created in 2021/10/12
 * @description：实现 strStr() https://leetcode-cn.com/problems/implement-strstr/
 * @version:
 */
public class KMP {

    public int strStr(String haystack, String needle) {

        if (haystack.length() == 0 && needle.length() == 0) {
            return 0;
        }
        if (haystack.length() < needle.length()) {
            return -1;
        }
        if (needle.length() < 1) {
            return 0;
        }
        char[] src = haystack.toCharArray();
        char[] target = needle.toCharArray();
        int[] next = generanteNexts(target);

        int srcIndex = 0;
        int targetIndex = 0;

        while (srcIndex < src.length && targetIndex < target.length) {
            if (targetIndex >= 0 && src[srcIndex] == target[targetIndex]) {
                srcIndex++;
                targetIndex++;
            } else if (targetIndex == -1) {
                srcIndex++;
                targetIndex = 0;
            } else {
                targetIndex = next[targetIndex];
            }
        }
        return targetIndex == target.length ? srcIndex - targetIndex : -1;
    }

    /**
     * 给目标字符串生成next数组
     *
     * @param target
     * @return
     */
    private int[] generanteNexts(char[] target) {
        int[] next = new int[target.length];
        if (target.length < 2) {
            next[0] = -1;
            return next;
        }
        next[0] = -1;
        next[1] = 0;
        int index = 2;
        int compareIndex = 0;
        for (; index < next.length; ) {
            if (compareIndex >= 0 && target[index - 1] == target[compareIndex]) {
                next[index++] = ++compareIndex;
            } else if (compareIndex == -1) {
                next[index++] = 0;
                compareIndex = 0;
            } else {
                compareIndex = next[compareIndex];
            }
        }
        return next;
    }


    public static void main(String[] args) {
        KMP kmp = new KMP();
        kmp.strStr("a", "");
    }

}
