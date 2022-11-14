package com.zcp.part5.c251to300;

import com.zcp.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ZCP
 * @title: C257
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/binary-tree-paths/submissions/
 * @date 2022/9/23 8:52
 */
public class C257 {

    public List<String> binaryTreePaths(TreeNode root) {
        ArrayList<String> ans = new ArrayList<>();

        if (root == null) {
            return ans;
        }

        process(root, "" + root.val, ans);
        return ans;
    }

    public void process(TreeNode root, String path, List<String> ans) {
        if (root.left == null && root.right == null) {
            ans.add(path);
            return;
        }
        if (root.left != null) {
            process(root.left, path + "->" + root.left.val, ans);
        }
        if (root.right != null) {
            process(root.right, path + "->" + root.right.val, ans);
        }

    }
}
