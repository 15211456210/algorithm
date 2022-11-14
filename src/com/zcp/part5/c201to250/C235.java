package com.zcp.part5.c201to250;

import com.zcp.common.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ZCP
 * @title: C235
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/submissions/
 * @date 2022/9/21 17:09
 */
public class C235 {

    static Map<TreeNode, TreeNode> parent = new HashMap<>();
    static Set<TreeNode> path = new HashSet<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        parent.clear();
        parent.put(root, null);

        init(root);
        path.clear();
        while (p != null) {
            path.add(p);
            p = parent.get(p);
        }

        while (!path.contains(q)) {
            q = parent.get(q);
        }

        return q;


    }

    public void init(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            parent.put(root.left, root);
            init(root.left);
        }

        if (root.right != null) {
            parent.put(root.right, root);
            init(root.right);
        }


    }
}
