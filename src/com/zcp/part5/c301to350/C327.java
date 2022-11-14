package com.zcp.part5.c301to350;

/**
 * @author ZCP
 * @title: C327
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/count-of-range-sum/description/
 * @date 2022/10/15 16:59
 */
public class C327 {

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
}
