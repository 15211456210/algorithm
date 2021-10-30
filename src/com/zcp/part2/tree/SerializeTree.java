package com.zcp.part2.tree;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/7 16:16
 * @description：二叉树的序列化与反序列化
 * @version:
 */
public class SerializeTree {


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    /**
     * 先序 序列化 二叉树
     *
     * @param root
     * @return
     */
    public static String preSerialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        ArrayList<Object> list = new ArrayList<>();
        pre(root, list);
        return JSON.toJSONString(list);
    }

    private static void pre(TreeNode root, ArrayList<Object> list) {
        if (root == null) {
            list.add("#");//#表示空
            return;
        }
        list.add(root.val);
        pre(root.left, list);
        pre(root.right, list);
    }

    /**
     * 前序 反序列化 二叉树
     *
     * @param s
     * @return
     */
    public static TreeNode preDeSerialize(String s) {
        if (s == null || "".equals(s)) {
            return null;
        }
        count = 0;
        List<String> list = JSON.parseArray(s, String.class);
        return preDe(list);
    }

    static int count = 0;

    private static TreeNode preDe(List<String> list) {
        if ("#".equals(list.get(count))) {
            count++;
            return null;
        }
        TreeNode treeNode = new TreeNode(Integer.valueOf(list.get(count)), null, null);
        count++;
        treeNode.left = preDe(list);
        treeNode.right = preDe(list);
        return treeNode;
    }


    public static void main(String[] args) {
        int range = 10;
        int size = 100;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            TreeNode treeNode = generateRandomTree(range, size);
            if (!check(treeNode, preDeSerialize(preSerialize(treeNode)))) {
                System.out.println("出错了");
            }
        }
        System.out.println("测试结束");
    }

    private static boolean check(TreeNode tree1, TreeNode tree2) {
        if (tree1 == null && tree2 == null) {
            return true;
        }
        if ((tree1 == null && tree2 != null) || (tree2 == null && tree1 != null) || (tree1.val != tree2.val)) {
            return false;
        }
        return check(tree1.left, tree2.left) && check(tree1.right, tree2.right);
    }


    /**
     * 根据 range szie 生成随机二叉树
     *
     * @param range
     * @param size
     * @return
     */
    public static TreeNode generateRandomTree(int range, int size) {
        int sz = (int) (Math.random() * size) + 1;
        List<TreeNode> list = new ArrayList<>();
        TreeNode root = new TreeNode((int) (Math.random() * range), null, null);
        list.add(root);
        for (int i = 0; i < sz; i++) {
            TreeNode node = new TreeNode((int) (Math.random() * range), null, null);
            TreeNode parentNode = null;
            if (Math.random() < 0.5) {
                do {
                    parentNode = list.get((int) (Math.random() * list.size()));
                } while (parentNode.left != null);
                parentNode.left = node;
            } else {
                do {
                    parentNode = list.get((int) (Math.random() * list.size()));
                } while (parentNode.right != null);
                parentNode.right = node;
            }
            list.add(node);
        }
        return root;
    }


}
