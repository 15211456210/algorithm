package com.zcp.part5.c401to450;

import com.zcp.common.TreeNode;

import java.util.Arrays;

/**
 * @author ZCP
 * @title: C449
 * @projectName algorithm
 * @description: https://leetcode.com/problems/serialize-and-deserialize-bst/
 * @date 2023/2/2 10:31
 */
public class C449 {

    public static class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            String s = "#";
            if (root == null) {
                return s + "n";
            }
            s += root.val + "";
            s += serialize(root.left);
            s += serialize(root.right);
            return s;
        }

        String[] s;

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] datas = data.split("#");
            s = new String[datas.length - 1];
            for (int i = 0; i < s.length; i++) {
                s[i] = datas[i + 1];
            }
            return dfs(0, s.length - 1);
        }

        private TreeNode dfs(int l, int r) {
            if (l > r) {
                return null;
            }
            if ("n".equals(s[l])) {
                return null;
            }
            TreeNode root = new TreeNode(Integer.valueOf(s[l]));
            if (l == r) {
                return root;
            }

            int mid = r + 1; // 右子树开始位置
            for (int i = l + 1; i <= r; i++) {
                if (!"n".equals(s[i]) && Integer.valueOf(s[i]) > root.val) {
                    mid = i;
                    break;
                }
            }
            root.left = dfs(l + 1, mid - 1);
            root.right = dfs(mid, r);
            return root;
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1, null, new TreeNode(2));
        System.out.println(new Codec().serialize(treeNode));
        System.out.println(Arrays.toString("#1#2#n#n#n".split("#")));

        TreeNode deserialize = new Codec().deserialize("#1#2#n#n#n");

        System.out.println(deserialize);

    }


}
