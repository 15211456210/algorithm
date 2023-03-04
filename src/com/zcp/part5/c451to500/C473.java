package com.zcp.part5.c451to500;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZCP
 * @title: C473
 * @projectName algorithm
 * @description: https://leetcode.com/problems/matchsticks-to-square/
 * @date 2023/2/17 10:17
 */
public class C473 {
    class Solution {
        public boolean makesquare(int[] matchsticks) {
            int totalLen = Arrays.stream(matchsticks).sum();
            if (totalLen % 4 != 0) {
                return false;
            }
            Arrays.sort(matchsticks);
            for (int i = 0, j = matchsticks.length - 1; i < j; i++, j--) {
                int temp = matchsticks[i];
                matchsticks[i] = matchsticks[j];
                matchsticks[j] = temp;
            }

            int[] edges = new int[4];
            return dfs(0, matchsticks, edges, totalLen / 4);
        }

        public boolean dfs(int index, int[] matchsticks, int[] edges, int len) {
            if (index == matchsticks.length) {
                return true;
            }
            for (int i = 0; i < edges.length; i++) {
                edges[i] += matchsticks[index];
                if (edges[i] <= len && dfs(index + 1, matchsticks, edges, len)) {
                    return true;
                }
                edges[i] -= matchsticks[index];
            }
            return false;
        }
    }


}
