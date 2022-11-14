package com.zcp.part5.c051to100;

/**
 * @author ZCP
 * @title: c069
 * @projectName algorithm
 * @description: https://leetcode.com/problems/sqrtx/
 * @date 2022/8/30 8:42
 */
public class c069 {

    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        long l = 0;
        long r = x;

        while (l < r) {
            long mid = (l + (r - l) / 2);
            if (mid * mid == x) {
                return (int) mid;
            } else if (mid * mid < x) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return (int) (l * l > x ? l - 1 : l);
    }
}
