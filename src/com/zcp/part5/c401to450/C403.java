package com.zcp.part5.c401to450;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZCP
 * @title: C403
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/frog-jump/description/
 * @date 2022/11/15 15:43
 */
public class C403 {

    public boolean canCross(int[] stones) {
        Map<String, Boolean> dp = new HashMap<>();
        return dfs(stones, 0, 0, dp);
    }

    private boolean dfs(int[] stones, int i, int k, Map<String, Boolean> dp) {
        if (i == stones.length - 1) {
            return true;
        }
        String key = i + "_" + k;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }
        boolean ans = false;
        for (int j = i + 1; j < stones.length && stones[j] <= stones[i] + k + 1; j++) {
            if (stones[j] >= stones[i] + k - 1 && stones[j] <= stones[i] + k + 1) {
                ans |= dfs(stones, j, stones[j] - stones[i], dp);
            }

        }
        dp.put(key, ans);
        return ans;
    }
}
