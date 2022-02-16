package com.zcp.part3.morrirs;

/**
 * @author ：ZCP
 * @date ：Created in 2021/10/20
 * @description：【二叉树最小深度】 https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
 * @version:
 */
public class MinDepth {


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

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
        TreeNode mostRightLeef = root.right;
        if (mostRightLeef != null) {
            curLevel = 2;
            while (mostRightLeef.right != null) {
                mostRightLeef = mostRightLeef.right;
                curLevel++;
            }
            if(mostRightLeef.left == null){
                minDepth = Math.min(curLevel, minDepth);
            }
        }
        return minDepth;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        treeNode1.left = treeNode2;
        treeNode2.left = treeNode3;
//        treeNode3.left = treeNode4;
//        treeNode4.left = treeNode5;

        System.out.println(new MinDepth().minDepth(treeNode1));
    }

}
