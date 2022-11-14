package com.zcp.part5.c101to150;

import com.zcp.common.TreeNode;

/**
 * @author ZCP
 * @title: C129
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/sum-root-to-leaf-numbers/submissions/
 * @date 2022/9/5 15:49
 */
public class C129 {

    class Info {
        int sum;
    }

    public int sumNumbers(TreeNode root) {
        Info info = new Info();
        process(root, 0, info);
        return info.sum;

    }

    public void process(TreeNode node, int path, Info info) {
        if (node == null){
            return;
        }
        if (node.left == null && node.right == null) {
            info.sum += path * 10 + node.val;
            return;
        }
        process(node.left, path * 10 + node.val, info);
        process(node.right, path * 10 + node.val, info);
    }

}
