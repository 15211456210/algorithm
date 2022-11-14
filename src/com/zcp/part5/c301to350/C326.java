package com.zcp.part5.c301to350;

import java.util.Arrays;

/**
 * @author ZCP
 * @title: C326
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/power-of-three/description/
 * @date 2022/10/15 16:52
 */
public class C326 {


    static int[] arr = new int[]{
            1
            , 3
            , 9
            , 27
            , 81
            , 243
            , 729
            , 2187
            , 6561
            , 19683
            , 59049
            , 177147
            , 531441
            , 1594323
            , 4782969
            , 14348907
            , 43046721
            , 129140163
            , 387420489
            , 1162261467
    };

    public boolean isPowerOfThree(int n) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == n) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int x = 1;
        int i = 0;
        for (; i < 31; i++) {
            System.out.println(x);
            if (Integer.MAX_VALUE / 3 >= x) {
                x *= 3;
            } else {
                break;
            }
        }
        System.out.println(i);


    }
}
