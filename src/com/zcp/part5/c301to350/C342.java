package com.zcp.part5.c301to350;

/**
 * @author ZCP
 * @title: C342
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/power-of-four/description/
 * @date 2022/10/20 9:22
 */
public class C342 {


    static int[] map = {
            1
            , 4
            , 16
            , 64
            , 256
            , 1024
            , 4096
            , 16384
            , 65536
            , 262144
            , 1048576
            , 4194304
            , 16777216
            , 67108864
            , 268435456
            , 1073741824
    };

    public boolean isPowerOfFour(int n) {
        for (int i = 0; i < map.length; i++) {
            if (n == map[i]) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        int n = 1;
        for (int i = 0; i < 30; i++) {
            System.out.println(n);
            if (Integer.MAX_VALUE / 4 < n) {
                break;
            }
            n *= 4;
        }


    }


}
