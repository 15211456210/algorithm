package com.zcp.part5.c351to400;

/**
 * @author ZCP
 * @title: C393
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/utf-8-validation/description/
 * @date 2022/11/11 10:27
 */
public class C393 {

    public boolean validUtf8(int[] data) {
        int count = 0;
        for (int i = 0; i < data.length; i++) {
            int num = data[i];
            if ((count > 0 && (num >> 6) != 2) || (count == 0 && (num >> 6) == 2)) {
                return false;
            }
            if (num >> 7 != 0) {
                if (num >> 3 == 30) {
                    count = 3;
                } else if (num >> 4 == 14) {
                    count = 2;
                } else if (num >> 5 == 6) {
                    count = 1;
                } else if (num >> 6 == 2) {
                    count--;
                } else {
                    return false;
                }
            }
        }
        return count == 0;
    }
}
