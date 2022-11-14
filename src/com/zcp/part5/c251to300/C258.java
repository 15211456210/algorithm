package com.zcp.part5.c251to300;

/**
 * @author ZCP
 * @title: C258
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/add-digits/
 * @date 2022/9/23 8:59
 */
public class C258 {

    public int addDigits(int num) {
        while (num >= 10) {
            int cn = num;
            num = 0;
            while (cn > 0) {
                num += cn % 10;
                cn /= 10;
            }
        }
        return num;
    }
}
