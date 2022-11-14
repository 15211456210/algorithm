package com.zcp.part5.c001to050;

/**
 * @author ZCP
 * @title: c012
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/integer-to-roman/
 * @date 2022/8/25 13:32
 */
public class c012 {

    static String[] map = {"", "I", "II", "III", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
    static int[] map2 = {0, 1, 2, 3, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};

    public String intToRoman(int num) {
        int idx = map.length - 1;
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            int s = map2[idx];
            int cnt = num / s;
            for (int i = 0; i < cnt; i++) {
                sb.append(map[idx]);
            }
            idx--;
            num %= s;
        }

        return sb.toString();
    }
}
