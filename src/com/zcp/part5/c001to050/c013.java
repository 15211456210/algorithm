package com.zcp.part5.c001to050;

/**
 * @author ZCP
 * @title: c013
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/roman-to-integer/submissions/
 * @date 2022/8/25 13:33
 */
public class c013 {

    // I， V， X， L，C，D 和 M
    static String[] symbols = new String[]{"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
    static int[] values = new int[]{1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};

    public int romanToInt(String s) {
        int num = 0;
        int idx = 0;
        int len = s.length();
        while (idx < len) {
            for (int i = 12; i >= 0; i--) {
                int sbLen = symbols[i].length();
                if(s.startsWith(symbols[i],idx)){
                    num += values[i];
                    idx += sbLen;
                    break;
                }
            }
        }
        return num;
    }
}
