package com.zcp.part2.kmp;


/**
 * @author ：ZCP
 * @date ：Created in 2021/10/15
 * @description：[[判断两个字符串是否互为旋转词|旋转字符串]] 例子：
 * 输入 ABCDE, CDEAB 返回true
 * 输入 ABCDE, CEABD 返回false
 * 解释：
 * 字符串通过旋转（将开头N个字符截取，放到字符串后面）一次，能到得到字符串2，返回true，否则返回false
 * @version:
 */
public class RotateStr {


    public static boolean isRotateStr(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        String pattern = s1 + s1;
        for (int i = 0; i < s1.length(); i++) {
            if (pattern.substring(i, i + s1.length()).equals(s2)) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(isRotateStr("ABCDE", "CEABD"));
    }
}
