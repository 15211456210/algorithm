package com.zcp.part5.c301to350;

import com.zcp.common.TreeNode;

/**
 * @author ZCP
 * @title: C337
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/house-robber-iii/submissions/
 * @date 2022/10/19 17:04
 */
public class C337 {

    public int rob(TreeNode root) {
        int[] res = get(root);
        return Math.max(res[0], res[1]);
    }

    public int[] get(TreeNode root) {
        //int[]{select最优解,notSelect最优解}
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] l = get(root.left);
        int[] r = get(root.right);
        int select = root.val + l[1] + r[1];
        int notSelect = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        return new int[]{select, notSelect};
    }
}
