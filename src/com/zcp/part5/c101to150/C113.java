package com.zcp.part5.c101to150;

import com.zcp.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ZCP
 * @title: C113
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/path-sum-ii/submissions/
 * @date 2022/9/1 16:22
 */
public class C113 {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> list = new LinkedList<List<Integer>>();
        if (root == null) {
            return list;
        }
        pathSum(root, 0, targetSum, new LinkedList<Integer>(), list);
        return list;
    }


    public void pathSum(TreeNode root, int preSum, int targetSum, List<Integer> preList, List<List<Integer>> list) {
        if (root.left == null && root.right == null && root.val + preSum == targetSum) {
            List<Integer> newList = copy(preList);
            newList.add(root.val);
            list.add(newList);
        }

        preList.add(root.val);
        preSum += root.val;
        if (root.left != null) {
            pathSum(root.left, preSum, targetSum, preList, list);
        }
        if (root.right != null) {
            pathSum(root.right, preSum, targetSum, preList, list);
        }
        //返回父节点是需要先把自己删除
        preList.remove(preList.size() - 1);
    }

    public List<Integer> copy(List<Integer> src) {
        List newList = new ArrayList<Integer>();
        for (Integer i : src) {
            newList.add(i);
        }
        return newList;
    }
}
