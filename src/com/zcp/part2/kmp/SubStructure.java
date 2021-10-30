package com.zcp.part2.kmp;

/**
 * @author ：ZCP
 * @date ：Created in 2021/10/15
 * @description：
 * @version:
 */
public class SubStructure {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        return getIn(A, B);
    }


    /**
     * @param root
     * @return
     */
    private boolean getIn(TreeNode root, TreeNode p) {
        if (root == null) {
            return false;
        }
        if (root.val == p.val) {
            //依次匹配子节点
            if (pattern(root, p)) {
                return true;
            }
        }
        boolean left = getIn(root.left, p);
        boolean right = getIn(root.right, p);
        return left || right;
    }

    private boolean pattern(TreeNode root, TreeNode p) {
        if (p == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        if (root.val != p.val) {
            return false;
        }
        boolean left = pattern(root.left, p.left);
        boolean right = pattern(root.right, p.right);
        return left && right;
    }

    public static void main(String[] args) {
        SubStructure structure = new SubStructure();
//        structure.isSubStructure()
    }
}
