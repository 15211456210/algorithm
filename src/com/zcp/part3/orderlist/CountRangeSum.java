package com.zcp.part3.orderlist;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: https://leetcode-cn.com/problems/count-of-range-sum/
 * 给你一个整数数组 nums 以及两个整数 lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。
 * <p>
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 * @projectName:algorithm
 * @see:com.zcp.part2.orderlist
 * @author:ZCP
 * @createTime:2021/11/18
 * @version:1.0
 */
public class CountRangeSum {

    public static class SizeBalanceTree {
        TreeNode root;

        int total;

        public static class TreeNode {
            long key;
            Object value;
            int count;//个数
            int size;//树节点个数
            TreeNode left;
            TreeNode right;

            public TreeNode() {
            }

            public TreeNode(long key, Object value, int size, int count) {
                this.key = key;
                this.value = value;
                this.size = size;
                this.count = count;
            }
        }

        public SizeBalanceTree() {
        }

        /**
         * 添加元素
         *
         * @param key
         * @param val
         */
        public void add(long key, Object val) {
            if (root == null) {
                total++;
                root = new TreeNode(key, val, 1, 1);
            } else {
                root = insert(root, key, val);
            }
        }

        /**
         * 获取树中val小于 l 的个数（count）
         *
         * @param maxkey
         */
        public int getSmallThan(long maxkey) {

            int sumCount = 0;
            TreeNode cur = root;
            while (cur != null) {
                if (cur.key == maxkey) {
                    if (cur.left != null) {
                        sumCount += cur.left.count;
                    }
                    break;
                } else if (cur.key > maxkey) {
                    cur = cur.left;
                } else if (cur.key < maxkey) {
                    int cutNum = cur.count;
                    if (cur.right != null) {
                        cutNum -= cur.right.count;
                        sumCount += cutNum;
                        cur = cur.right;
                    } else {
                        sumCount += cutNum;
                        break;
                    }
                }
            }
            return sumCount;
        }


        /**
         * 是否包含某个Key
         *
         * @param key
         * @return
         */
        public boolean containsKey(int key) {
            return get(key) != null;
        }

        /**
         * 获取某个key
         *
         * @param key
         * @return
         */
        public TreeNode get(int key) {
            TreeNode cur = root;
            while (cur != null) {
                if (cur.key == key) {
                    return cur;
                } else if (cur.key < key) {
                    cur = cur.right;
                } else {
                    cur = cur.left;
                }
            }
            return null;
        }

        /**
         * 插入 返回新的头节点（可能会换头）
         *
         * @param root
         * @param key
         * @param val
         * @return
         */
        private TreeNode insert(TreeNode root, long key, Object val) {
            root.count++;
            if (root.key == key) {
                root.value = val;
                return balance(root);
            } else if (root.key < key) {
                if (root.right != null) {
                    TreeNode right = insert(root.right, key, val);
                    root.right = balance(right);
                    return balance(root);
                } else {
                    total++;
                    root.size++;
                    root.right = new TreeNode(key, val, 1, 1);
                    return root;
                }
            } else {
                if (root.left != null) {
                    TreeNode left = insert(root.left, key, val);
                    root.left = balance(left);
                    return balance(root);
                } else {
                    total++;
                    root.size++;
                    root.left = new TreeNode(key, val, 1, 1);
                    return root;
                }
            }
        }

        /**
         * 树平衡调整
         * * ll：根右旋
         * * lr：l左旋，根右旋
         * * rr：根左旋
         * * rl：r右旋，根左旋
         *
         * @param root
         * @return
         */
        private TreeNode balance(TreeNode root) {
            int l = root.left == null ? 0 : root.left.size;
            int r = root.right == null ? 0 : root.right.size;
            int ll = root.left == null ? 0 : root.left.left == null ? 0 : root.left.left.size;
            int lr = root.left == null ? 0 : root.left.right == null ? 0 : root.left.right.size;
            int rr = root.right == null ? 0 : root.right.right == null ? 0 : root.right.right.size;
            int rl = root.right == null ? 0 : root.right.left == null ? 0 : root.right.left.size;
            if (l < rr) {
                TreeNode head = leftRotation(root);
                TreeNode rHead = balance(head);
                rHead.left = balance(rHead.left);
                return rHead;
            } else if (l < rl) {
                root.right = rightRotation(root.right);
                TreeNode head = leftRotation(root);
                TreeNode rHead = balance(head);
                rHead.left = balance(rHead.left);
                rHead.right = balance(rHead.right);
                return rHead;
            } else if (r < ll) {
                TreeNode head = rightRotation(root);
                TreeNode rHead = balance(head);
                rHead.right = balance(rHead.right);
                return rHead;
            } else if (r < lr) {
                root.left = leftRotation(root.left);
                TreeNode head = rightRotation(root);
                TreeNode rHead = balance(head);
                rHead.left = balance(rHead.left);
                rHead.right = balance(rHead.right);
                return rHead;
            } else {
                //就算是无需调整也应该把size大小调整对
                root.size = (root.left == null ? 0 : root.left.size) + (root.right == null ? 0 : root.right.size) + 1;
                return root;
            }
        }

        /**
         * 右旋
         *
         * @param root
         * @return
         */
        private TreeNode rightRotation(TreeNode root) {
            TreeNode head = root.left;
            //调整的时候需要重新计算count
            int rootCount = root.count - (root.left == null ? 0 : root.left.count) - (root.right == null ? 0 : root.right.count);
            int headCount = head.count - (head.left == null ? 0 : head.left.count) - (head.right == null ? 0 : head.right.count);
            root.left = head.right;
            head.right = root;
            root.size = (root.left == null ? 0 : root.left.size) + (root.right == null ? 0 : root.right.size) + 1;
            head.size = (head.left == null ? 0 : head.left.size) + (head.right == null ? 0 : head.right.size) + 1;
            root.count = rootCount + (root.left == null ? 0 : root.left.count) + (root.right == null ? 0 : root.right.count);
            head.count = headCount + (head.left == null ? 0 : head.left.count) + (head.right == null ? 0 : head.right.count);
            return head;
        }

        /**
         * 左旋
         *
         * @param root
         * @return
         */
        private TreeNode leftRotation(TreeNode root) {
            TreeNode head = root.right;
            //调整的时候需要重新计算count
            int rootCount = root.count - (root.left == null ? 0 : root.left.count) - (root.right == null ? 0 : root.right.count);
            int headCount = head.count - (head.left == null ? 0 : head.left.count) - (head.right == null ? 0 : head.right.count);
            root.right = head.left;
            head.left = root;
            root.size = (root.left == null ? 0 : root.left.size) + (root.right == null ? 0 : root.right.size) + 1;
            head.size = (head.left == null ? 0 : head.left.size) + (head.right == null ? 0 : head.right.size) + 1;
            root.count = rootCount + (root.left == null ? 0 : root.left.count) + (root.right == null ? 0 : root.right.count);
            head.count = headCount + (head.left == null ? 0 : head.left.count) + (head.right == null ? 0 : head.right.count);
            return head;
        }


    }



    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        //求前缀和数组
        long[] preSum = new long[nums.length];
        preSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }

        SizeBalanceTree sbTree = new SizeBalanceTree();
        int ans = 0;
        //为什么要加一个0先？
        //这里加0表示一种情况  ：左边界为下标为0
        sbTree.add(0, null);
        for (int i = 0; i < preSum.length; i++) {
            long max = preSum[i] - lower;
            long min = preSum[i] - upper;
            //等到树中小于某个数的 节点count值
            ans += (sbTree.getSmallThan(max + 1) - sbTree.getSmallThan(min));
            sbTree.add(preSum[i], null);
            System.out.println();
        }

        return ans;
    }

    public static void main(String[] args) {
//        SearchTree searchTree = new SearchTree();
//        for (int i = 0; i < 10; i++) {
//            searchTree.add(i);
//        }
//        System.out.println(searchTree.getSmallThan(1));
        //nums = [-2,5,-1], lower = -2, upper = 2
        CountRangeSum countRangeSum = new CountRangeSum();
        System.out.println(countRangeSum.countRangeSum(new int[]{5,-23,-5,-1,-21,13,15,7,18,4,7,26,29,-7,-28,11,-20,-29,19,22,15,25,17,-13,11,-15,19,-8,3,12,-1,2,-1,-21,-10,-7,14,-12,-14,-8,-1,-30,19,-27,16,2,-15,23,6,14,23,2,-4,4,-9,-8,10,20,-29,29}, -19, 10));
        int[] nums0 = {5, -23, -5, -1, -21, 13, 15, 7, 18, 4, 7, 26, 29, -7, -28, 11, -20, -29, 19, 22, 15, 25, 17, -13, 11, -15, 19, -8, 3, 12, -1, 2, -1, -21, -10, -7, 14, -12, -14, -8, -1, -30, 19, -27, 16, 2, -15, 23, 6, 14, 23, 2, -4, 4, -9, -8, 10, 20, -29, 29};
        int[] nums = new int[nums0.length];
//        int[] nums = {-1,-2,-3,-4,-6,63,25};
        nums[0] = nums0[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i - 1] + nums0[i];
        }
        for (int m = 0; m < 1000; m++) {
//            int[] nums = generateArray(10, 10);
            SizeBalanceTree sizeBalanceTree = new SizeBalanceTree();
            sizeBalanceTree.add(0,null);
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                sizeBalanceTree.add(nums[i], null);
                int count = 1;
                if (map.get(nums[i]) != null) {
                    count += map.get(nums[i]);
                }
                map.put(nums[i], count);

            }
            if(!checkNum(sizeBalanceTree, map)){
                System.out.println("出错了");
                break;
            }
        }


    }

    private static boolean checkNum(SizeBalanceTree sizeBalanceTree, HashMap<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            SizeBalanceTree.TreeNode treeNode = sizeBalanceTree.get(entry.getKey());
            int count = treeNode.count - (treeNode.left == null ? 0 : treeNode.left.count) - (treeNode.right == null ? 0 : treeNode.right.count);
            if (count != entry.getValue()) {
                return false;
            }
        }
        return true;


    }

    private static int[] generateArray(int length, int range) {
        int len = (int) (Math.random() * length);
        int[] arr = new int[len];

        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * range) - (int) (Math.random() * range);
        }
        return arr;
    }

}
