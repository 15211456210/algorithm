package com.zcp.part2.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/11 14:13
 * @description：最大快乐值问题 从一颗N叉树中选择一些节点，直接相连的父子节点不能同时被选择，返回所有选中节点值和最大的数
 * @version:
 */
public class MaxHappyVal {

    public static class TreeNode {
        int val;
        List<TreeNode> childrens;

        public TreeNode(int val) {
            this.val = val;
            this.childrens = new ArrayList<>();
        }
    }


    public static int solution(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Info info = process(root);
        return Math.max(info.yes, info.no);

    }

    public static class Info {
        int yes;
        int no;

        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }

    public static Info process(TreeNode TreeNode) {
        if (TreeNode == null) {
            return new Info(0, 0);
        }
        int yes = TreeNode.val;
        int no = 0;
        for (TreeNode children : TreeNode.childrens) {
            Info info = process(children);
            yes += info.no;
            no += Math.max(info.yes, info.no);
        }
        return new Info(yes, no);
    }

    public static void main(String[] args) {
        TreeNode treeNode = generateRandomTree(10, 10);
        int solution = solution(treeNode);
        System.out.println(solution);
    }

    public static TreeNode generateRandomTree(int range, int size) {
        int sz = (int) (Math.random() * size);
        ArrayList<TreeNode> TreeNodes = new ArrayList<>();
        TreeNode root = new TreeNode((int) (Math.random() * range));
        TreeNodes.add(root);
        for (int i = 0; i < size; i++) {
            TreeNode TreeNode = new TreeNode((int) (Math.random() * range));
            TreeNode rand = TreeNodes.get((int) (Math.random() * TreeNodes.size()));
            if (rand.childrens == null){
                rand.childrens = new ArrayList<>();
            }
            rand.childrens.add(TreeNode);
            TreeNodes.add(TreeNode);
        }
        return root;
    }
}
