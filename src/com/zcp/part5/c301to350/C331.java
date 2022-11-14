package com.zcp.part5.c301to350;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZCP
 * @title: C331
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/verify-preorder-serialization-of-a-binary-tree/description/
 * @date 2022/10/17 17:01
 */
public class C331 {

    public boolean isValidSerialization(String preorder) {
        if (preorder == null || preorder.length() == 0) {
            return false;
        }
        String[] nodes = preorder.split(",");
        int len = nodes.length;

        List<Integer> spIndexList = new ArrayList<>();

        for (int i = 1; i < len; i++) {
            if (nodes[i - 1].equals("#") && nodes[i].equals("#")) {
                spIndexList.add(i);
            }
        }
        int[][] dp = new int[len][len];
        return process(nodes, 0, len - 1, dp, spIndexList);

    }

    private boolean process(String[] nodes, int begin, int end, int[][] dp, List<Integer> spIndexList) {
        if (begin > end) {
            return false;
        }
        if (dp[begin][end] != 0) {
            return dp[begin][end] > 0;
        }
        if (nodes[begin].equals("#")) {
            return begin == end;
        }
        if (begin + 2 == end) {
            return nodes[begin + 1].equals("#") && nodes[begin + 2].equals("#");
        }
        if (begin + 1 < nodes.length && nodes[begin + 1].equals("#")) {
            // 左子树为空
            dp[begin][end] = process(nodes, begin + 2, end, dp, spIndexList) ? 1 : -1;
            return dp[begin][end] > 0;
        }
        boolean ans = false;
        for (int i = 0; i < spIndexList.size(); i++) {
            Integer spIdx = spIndexList.get(i);
            if (spIdx >= begin + 1) {
                ans = ans || (process(nodes, begin + 1, spIdx, dp, spIndexList) && process(nodes, spIdx + 1, end, dp, spIndexList));
            }
        }
        dp[begin][end] = ans ? 1 : -1;
        return ans;
    }
}
