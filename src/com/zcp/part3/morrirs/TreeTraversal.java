package com.zcp.part3.morrirs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：ZCP
 * @date ：Created in 2021/10/20
 * @description：
 * @version:
 */
public class TreeTraversal {

    public class TreeNode {
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

    /**
     * 前序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        TreeNode cur = root;
        while(cur != null){
            TreeNode mostSubRight = cur.left;
            if(mostSubRight != null){
                while(mostSubRight.right != null && mostSubRight.right != cur){
                    mostSubRight = mostSubRight.right;
                }
                if(mostSubRight.right != cur){
                    mostSubRight.right = cur;
                    list.add(cur.val);
                    cur = cur.left;
                    continue;
                }
                //从最右子节点跳上来的情况 mostSubRight.right == cur
                mostSubRight.right = null;
            }else {
                list.add(cur.val);
            }
            cur = cur.right;
        }
        return list;
    }


    /**
     * 中序遍历
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        TreeNode cur = root;
        while(cur != null){
            TreeNode mostSubRight = cur.left;
            if(mostSubRight != null){
                while(mostSubRight.right != null && mostSubRight.right != cur){
                    mostSubRight = mostSubRight.right;
                }
                if(mostSubRight.right != cur){
                    mostSubRight.right = cur;
                    cur = cur.left;
                    continue;
                }
                //从最右子节点跳上来的情况 mostSubRight.right == cur
                mostSubRight.right = null;
            }
            list.add(cur.val);
            cur = cur.right;
        }
        return list;
    }

    /**
     * 后续遍历
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        TreeNode cur = root;
        while(cur != null){
            TreeNode mostSubRight = cur.left;
            if(mostSubRight != null){
                while(mostSubRight.right != null && mostSubRight.right != cur){
                    mostSubRight = mostSubRight.right;
                }
                if(mostSubRight.right != cur){
                    mostSubRight.right = cur;
                    cur = cur.left;
                    continue;
                }
                //从最右子节点跳上来的情况 mostSubRight.right == cur
                mostSubRight.right = null;
                addNode(cur.left,list);
            }
            cur = cur.right;
        }
        //最后将root右侧加入
        addNode(root,list);
        return list;
    }

    /**
     * 将树右子树倒序加入
     * 先反转链表，添加元素->list ,再反转回来
     * @param cur
     * @param list
     */
    private void addNode(TreeNode cur, ArrayList<Integer> list) {

        TreeNode pre = null;
        while(cur != null){
            TreeNode next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }

        cur = pre;
        pre = null;
        while(cur != null){
            list.add(cur.val);
            TreeNode next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }
    }

}
