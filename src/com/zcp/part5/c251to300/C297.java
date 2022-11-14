package com.zcp.part5.c251to300;

import com.zcp.common.TreeNode;

/**
 * @author ZCP
 * @title: C297
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/submissions/
 * @date 2022/9/27 13:21
 */
public class C297 {

    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            return process(root, "");
        }

        public String process(TreeNode root, String path) {
            if (root == null) {
                return path + "n#";
            }
            path += root.val + "#";
            path = process(root.left, path);
            path = process(root.right, path);
            return path;
        }


        class Pair {
            TreeNode root;
            int idx;

            public Pair(TreeNode root, int idx) {
                this.root = root;
                this.idx = idx;
            }
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] path = data.split("#");
            return process(path, 0).root;
        }

        public Pair process(String[] path, int idx) {
            if (idx >= path.length) {
                return new Pair(null, idx);
            }
            if (path[idx].equals("n")) {
                return new Pair(null, idx);
            }
            int val = Integer.valueOf(path[idx]);
            TreeNode head = new TreeNode(val);

            Pair leftPair = process(path, idx + 1);
            Pair rightPair = process(path, leftPair.idx + 1);
            head.left = leftPair.root;
            head.right = rightPair.root;
            return new Pair(head, rightPair.idx);
        }
    }
}
