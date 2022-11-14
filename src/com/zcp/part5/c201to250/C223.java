package com.zcp.part5.c201to250;

/**
 * @author ZCP
 * @title: C223
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/rectangle-area/
 * @date 2022/9/18 18:13
 */
public class C223 {

    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        return (ax2 - ax1) * (ay2 - ay1) + (bx2 - bx1) * (by2 - by1) - overlapLength(ax1, ax2, bx1, bx2) * overlapLength(ay1, ay2, by1, by2);
    }


    public int overlapLength(int a1, int a2, int b1, int b2) {
        if (a1 > a2) {
            int tmp = a1;
            a1 = a2;
            a2 = tmp;
        }
        if (b1 > b2) {
            int tmp = b1;
            b1 = b2;
            b2 = tmp;
        }

        if (a1 <= b1 && a2 >= b2) {
            return b2 - b1;
        }
        if (b1 <= a1 && b2 >= a2) {
            return a2 - a1;
        }
        if (b1 <= a1 && b2 >= a1 && b2 <= a2) {
            return b2 - a1;
        }
        if (a1 <= b1 && a2 >= b1 && a2 <= b2) {
            return a2 - b1;
        }
        return 0;
    }
}
