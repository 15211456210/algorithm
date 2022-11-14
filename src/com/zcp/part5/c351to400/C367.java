package com.zcp.part5.c351to400;

/**
 * @author ZCP
 * @title: C367
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/valid-perfect-square/description/
 * @date 2022/10/25 15:57
 */
public class C367 {

    public boolean isPerfectSquare(int num) {
        int l = 1, r = num;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (mid * mid == num) {
                return true;
            } else if (num / mid >= mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }
}
