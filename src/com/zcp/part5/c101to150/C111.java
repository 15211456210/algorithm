package com.zcp.part5.c101to150;

import com.zcp.common.TreeNode;

/**
 * @author ZCP
 * @title: C111
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/minimum-depth-of-binary-tree/submissions/
 * @date 2022/9/1 16:20
 */
public class C111 {

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return 1;
        }

        int minDepth = Integer.MAX_VALUE;
        int curLevel = 1;
        int preLevel = -1;
        TreeNode cur = root;
        while (cur != null) {
            TreeNode mostSubRight = cur.left;
            if (mostSubRight != null) {
                int subDeep = 1;
                while (mostSubRight.right != null && mostSubRight.right != cur) {
                    mostSubRight = mostSubRight.right;
                    subDeep++;
                }
                if (mostSubRight.right != cur) {
                    mostSubRight.right = cur;
                    cur = cur.left;
                    preLevel = curLevel++;
                    continue;
                }
                //从最右子节点跳上来的情况 mostSubRight.right == cur
                mostSubRight.right = null;
                curLevel = preLevel - subDeep;
                if (mostSubRight.left == null) {
                    //说明是上一个是叶子节点
                    minDepth = Math.min(preLevel, minDepth);
                }
            }
            preLevel = curLevel++;
            cur = cur.right;
        }
        TreeNode right = root.right;
        if (right != null) {
            curLevel = 2;
            while (right.right != null) {
                right = right.right;
                curLevel++;
            }
            if (right.left == null) {
                minDepth = Math.min(curLevel, minDepth);
            }
        }

        return minDepth;
    }
}
