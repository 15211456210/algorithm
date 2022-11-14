package com.zcp.part5.c001to050;

/**
 * @author ZCP
 * @title: C014
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/longest-common-prefix/
 * @date 2022/8/25 13:47
 */
public class c014 {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int index = 0;
        char match = 'a';
        boolean isEnd = false;
        while (!isEnd) {
            for (int i = 0; i < strs.length; i++) {
                String str = strs[i];
                if (str.length() <= index) {
                    isEnd = true;
                    break;
                }
                if (i == 0) {
                    match = str.charAt(index);
                } else if (str.charAt(index) != match) {
                    isEnd = true;
                    break;
                }
            }
            index++;
        }

        return strs[0].substring(0, index - 1);

    }


}
