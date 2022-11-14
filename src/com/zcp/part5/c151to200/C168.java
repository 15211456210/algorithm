package com.zcp.part5.c151to200;

/**
 * @author ZCP
 * @title: C168
 * @projectName algorithm
 * @description: https://leetcode.com/problems/excel-sheet-column-title/
 * @date 2022/9/9 9:22
 */
public class C168 {

    public String convertToTitle(int columnNumber) {
        String ans = "";
        while (columnNumber != 0) {
            ans = (char) ('A' + (columnNumber - 1) % 26) + ans;
            columnNumber = (columnNumber - 1) / 26;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new C168().convertToTitle(701));
    }
}
