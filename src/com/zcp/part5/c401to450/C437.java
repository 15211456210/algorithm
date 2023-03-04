package com.zcp.part5.c401to450;

import com.zcp.common.TreeNode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author ZCP
 * @title: C437
 * @projectName algorithm
 * @description: https://leetcode.com/problems/path-sum-iii/
 * @date 2023/1/30 19:39
 */
public class C437 {

    int count = 0;

    public int pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum);
        return count;
    }

    public HashMap<Long, Integer> dfs(TreeNode root, int targetSum) {
        HashMap<Long, Integer> map = new HashMap<>();
        if (root == null) {
            return map;
        }
        HashMap<Long, Integer> total = new HashMap<>();
        HashMap<Long, Integer> leftMap = dfs(root.left, targetSum);
        HashMap<Long, Integer> rightMap = dfs(root.right, targetSum);
        total.put((long) root.val, 1);
        for (Long key : leftMap.keySet()) {
            long sum = key + (long) root.val;
            total.put(sum, total.getOrDefault(sum, 0) + leftMap.getOrDefault(key, 0));
        }
        for (Long key : rightMap.keySet()) {
            long sum = key + (long) root.val;
            total.put(sum, total.getOrDefault(sum, 0) + rightMap.getOrDefault(key, 0));
        }
        count += total.getOrDefault((long) targetSum, 0);
        return total;
    }

}
