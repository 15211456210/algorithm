package com.zcp.part3.orderlist;

import java.util.Arrays;

/**
 * @description:https://leetcode-cn.com/problems/sliding-window-median/ 中位数是有序序列最中间的那个数。如果序列的长度是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。
 * <p>
 * 例如：
 * <p>
 * [2,3,4]，中位数是 3
 * [2,3]，中位数是 (2 + 3) / 2 = 2.5
 * 给你一个数组 nums，有一个长度为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
 * @projectName:algorithm
 * @see:com.zcp.part2.orderlist
 * @author:ZCP
 * @createTime:2021/11/19
 * @version:1.0
 */
public class MedianSlidingWindow {

    public static class SizeBalanceTree {

        TreeNode root;

        int total;


        public static class TreeNode {
            double key;
            Object value;
            int size;//树节点个数
            int count = 1;
            TreeNode left;
            TreeNode right;

            public TreeNode() {
            }

            public TreeNode(double key, Object value, int size) {
                this.key = key;
                this.value = value;
                this.size = size;
            }
        }

        public SizeBalanceTree() {
        }

        /**
         * 获取第i位置上的数返回
         *
         * @param index
         * @return
         */
        public double getIndex(int index) {
            TreeNode cur = root;
            while (cur != null) {
                int l = cur.left == null ? 0 : cur.left.count;
                int r = cur.count - (cur.right == null ? 0 : cur.right.count);
                if (l < index && r >= index) {
                    return cur.key;
                } else if (l >= index) {
                    cur = cur.left;
                } else if (r < index) {
                    cur = cur.right;
                    index -= r;
                }
            }
            return -1;
        }

        /**
         * 添加元素
         *
         * @param key
         * @param val
         */
        public void add(double key, Object val) {
            if (root == null) {
                total++;
                root = new TreeNode(key, val, 1);
            } else {
                root = insert(root, key, val);
            }
        }

        /**
         * 删除
         *
         * @param key
         * @return
         */
        public double delete(double key) {
            if (containsKey(key)) {
                total--;
                root = doDelete(root, key);
            }
            return key;
        }


        /**
         * 删除节点
         *
         * @param root
         * @param key
         * @return
         */
        private TreeNode doDelete(TreeNode root, double key) {
            if (root.key == key) {
                int rCount = root.count - (root.left == null ? 0 : root.left.count) - (root.right == null ? 0 : root.right.count);
                if (rCount > 1) {
                    root.count--;
                    return root;
                }
                //删除当前节点，并且返回新的头节点（可能会换头）
                if (root.left == null && root.right == null) {
                    return null;
                } else if (root.left != null && root.right == null) {
                    return root.left;
                } else if (root.left == null && root.left != null) {
                    return root.right;
                } else {
                    //左右子树都不为空，取后继节点
                    TreeNode pre = root;//当前后继节点的父节点
                    pre.size--;
                    TreeNode mostLeft = root.right;
                    TreeNode mostLeftTemp = mostLeft;
                    while (mostLeft.left != null) {
                        pre.size--;
                        pre = mostLeft;
                        mostLeft = mostLeft.left;
                    }
                    int mostLeftCount = mostLeft.count - (mostLeft.left == null ? 0 : mostLeft.count) - (mostLeft.right == null ? 0 : mostLeft.right.count);
                    //将后继节点沿途减去mostLeftCount
                    mostLeftTemp.count -= mostLeftCount;
                    while (mostLeftTemp.left != null) {
                        mostLeftTemp = mostLeftTemp.left;
                        mostLeftTemp.count -= mostLeftCount;
                    }
                    //递归删除后继节点
                    if (pre.left == mostLeft) {
                        pre.left = doDelete(mostLeft, mostLeft.key);
                    } else {
                        pre.right = doDelete(mostLeft, mostLeft.key);
                    }
                    //先把后继节点值赋给当前删除的节点
                    root.key = mostLeft.key;
                    root.value = mostLeft.value;
                    root.count = mostLeftCount + (root.left == null ? 0 : root.left.count) + (root.right == null ? 0 : root.right.count);//更新root节点count
                    return root;
                }
            } else if (key < root.key && root.left != null) {
                root.left = doDelete(root.left, key);
            } else if (key > root.key && root.right != null) {
                root.right = doDelete(root.right, key);
            }
            //沿途父节点数量减一
            root.size = (root.left == null ? 0 : root.left.size) + (root.right == null ? 0 : root.right.size) + 1;
            root.count--;
            return root;
        }

        /**
         * 是否包含某个Key
         *
         * @param key
         * @return
         */
        public boolean containsKey(double key) {
            return get(key) != null;
        }

        /**
         * 获取某个key
         *
         * @param key
         * @return
         */
        public TreeNode get(double key) {
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
        private TreeNode insert(TreeNode root, double key, Object val) {
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
                    root.right = new TreeNode(key, val, 1);
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
                    root.left = new TreeNode(key, val, 1);
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
            int rCount = root.count - (root.left == null ? 0 : root.left.count) - (root.right == null ? 0 : root.right.count);
            int hCount = head.count - (head.left == null ? 0 : head.left.count) - (head.right == null ? 0 : head.right.count);
            root.left = head.right;
            head.right = root;
            root.size = (root.left == null ? 0 : root.left.size) + (root.right == null ? 0 : root.right.size) + 1;
            head.size = (head.left == null ? 0 : head.left.size) + (head.right == null ? 0 : head.right.size) + 1;
            root.count = rCount + (root.left == null ? 0 : root.left.count) + (root.right == null ? 0 : root.right.count);
            head.count = hCount + (head.left == null ? 0 : head.left.count) + (head.right == null ? 0 : head.right.count);
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
            int rCount = root.count - (root.left == null ? 0 : root.left.count) - (root.right == null ? 0 : root.right.count);
            int hCount = head.count - (head.left == null ? 0 : head.left.count) - (head.right == null ? 0 : head.right.count);
            root.right = head.left;
            head.left = root;
            root.size = (root.left == null ? 0 : root.left.size) + (root.right == null ? 0 : root.right.size) + 1;
            head.size = (head.left == null ? 0 : head.left.size) + (head.right == null ? 0 : head.right.size) + 1;
            root.count = rCount + (root.left == null ? 0 : root.left.count) + (root.right == null ? 0 : root.right.count);
            head.count = hCount + (head.left == null ? 0 : head.left.count) + (head.right == null ? 0 : head.right.count);
            return head;
        }

    }


    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return new double[0];
        }
        double[] ans = new double[nums.length - k + 1];
        SizeBalanceTree sbTree = new SizeBalanceTree();
        for (int i = 0; i < k; i++) {
            sbTree.add(nums[i], null);
        }
        int index = 0;

        int l = 0;
        int r = k;
        while (index < ans.length) {
            if (k % 2 == 0) {
                ans[index++] = (sbTree.getIndex(k / 2 + 1) + sbTree.getIndex(k / 2)) / 2;
            } else {
                ans[index++] = sbTree.getIndex(k / 2 + 1);
            }
            if (r < nums.length) {
                //边界处理
                sbTree.delete(nums[l++]);//窗口左侧删除元素
                sbTree.add(nums[r++], nums);//窗口右侧添加元素
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        MedianSlidingWindow window = new MedianSlidingWindow();
        double[] ans = window.medianSlidingWindow(new int[]{7,0,3,9,9,9,1,7,2,3}, 6);
        System.out.println(Arrays.toString(ans));
    }
}
