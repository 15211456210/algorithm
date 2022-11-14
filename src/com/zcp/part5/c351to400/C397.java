package com.zcp.part5.c351to400;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZCP
 * @title: C397
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/integer-replacement/description/
 * @date 2022/11/12 11:13
 */
public class C397 {

    public int integerReplacement(int n) {
        return dfs(n, new HashMap<>());
    }


    private int dfs(int n, Map<Integer, Integer> dp) {
        if (n == Integer.MAX_VALUE) {
            return 32;
        }
        if (n == 1) {
            return 0;
        }
        if (dp.containsKey(n)) {
            return dp.get(n);
        }
        int min = Integer.MAX_VALUE;
        // P1
        if (n % 2 == 0) {
            min = Math.min(min, dfs(n / 2, dp));
        } else {
            min = Math.min(min, dfs(n - 1, dp));
            min = Math.min(min, dfs(n + 1, dp));
        }
        dp.put(n, min + 1);
        return min + 1;
    }
}
