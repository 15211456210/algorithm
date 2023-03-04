package com.zcp.part5.c451to500;

/**
 * @author ZCP
 * @title: C461
 * @projectName algorithm
 * @description: https://leetcode.com/problems/hamming-distance/submissions/
 * @date 2023/2/8 9:53
 */
public class C461 {

    public int hammingDistance(int x, int y) {
        int c = 0;
        for (int i = 0; i < 31; i++) {
            c += ((x & (1 << i)) == (y & (1 << i))) ? 0 : 1;
        }
        return c;
    }
}
