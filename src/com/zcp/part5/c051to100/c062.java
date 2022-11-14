package com.zcp.part5.c051to100;

/**
 * @author ZCP
 * @title: c062
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/unique-paths/submissions/
 * @date 2022/8/29 10:53
 */
public class c062 {

    public int uniquePaths(int m, int n) {
        double ans = 1;
        for (int x = 1, y = m; x < n; ++x, ++y) {
            ans = ans * y / x;
        }
        return (int) ans;
    }
}
