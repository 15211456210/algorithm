package com.zcp.part4.day06;

import java.util.Arrays;

// 测试链接 : https://leetcode.com/problems/maximum-xor-with-an-element-from-array/
public class Code03_MaximumXorWithAnElementFromArray {


    /**
     * 前缀树 数据结构
     */
    public static class TreeNode {
        /**
         * 存放 当前节点下 最小的值
         */
        int minVal = Integer.MAX_VALUE;

        /**
         * 分别代表0，1两个方向
         */
        TreeNode[] nexts = new TreeNode[2];

        /**
         * 往前缀树中添加int值
         * 将num的二进制值 从高位-地位的顺序添加到前缀树
         * 同时更新minVal值
         *
         * @param num
         */
        public void add(int num) {
            TreeNode cur = this;
            for (int i = 31; i >= 0; i--) {
                int bitVal = ((num >> i) & 1);
                if (cur.nexts[bitVal] == null) {
                    cur.nexts[bitVal] = new TreeNode();
                    cur.nexts[bitVal].minVal = num;
                }
                cur.minVal = Math.min(cur.minVal, num);
                cur = cur.nexts[bitVal];
            }
        }

        /**
         * 返回前缀树中 和 num值 异或后的最大结果，并且匹配的值不大于max
         *
         * @param num
         * @param max 允许的最大值
         * @return
         */
        public int maxXor(int num, int max) {
            TreeNode cur = this;
            int ans = 0;
            for (int i = 31; i >= 0; i--) {
                //31 位单独考虑（因为是符号位）
                //如果是 1 表示负数，要想异或结果尽可能大，需要匹配的值应该是 1
                //如果是其他普遍位置 0 匹配 1，1匹配0
                int bestBitVal = (i == 31 ? ((num >> i) & 1) : (1 ^ ((num >> i) & 1)));
                //求出预期的最佳选择后，还需要判断前缀树中是否有这条路经，如果没有只能选择有的路径走下去
                bestBitVal = cur.nexts[bestBitVal] == null ? (1 ^ bestBitVal) : bestBitVal;
                //选择最佳路线后 还需要判断max的条件
                if(cur.nexts[bestBitVal].minVal > max){
                    if(cur.nexts[(1^bestBitVal)] == null || cur.nexts[(1^bestBitVal)].minVal > max){
                        return -1;
                    }else{
                        bestBitVal = (1 ^ bestBitVal);
                    }
                }
                cur = cur.nexts[bestBitVal];
                //选择好了后 方法还没返回 说明 还能继续往下走  将值或上去
                ans |= (num ^ (bestBitVal << i)) & (1 << i);
            }
            return ans;
        }
    }


    /**
     * 思路：
     * 前缀树
     *
     * @param nums
     * @param queries
     * @return
     */
    public int[] maximizeXor1(int[] nums, int[][] queries) {
        if (nums == null || nums.length < 1) {
            return null;
        }
        TreeNode treeNode = new TreeNode();

        for (int i = 0; i < nums.length; i++) {
            treeNode.add(nums[i]);
        }
        int N = queries.length;
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            ans[i] = treeNode.maxXor(queries[i][0], queries[i][1]);
        }
        return ans;
    }


    public static int[] maximizeXor(int[] nums, int[][] queries) {
        int N = nums.length;
        NumTrie trie = new NumTrie();
        for (int i = 0; i < N; i++) {
            trie.add(nums[i]);
        }
        int M = queries.length;
        int[] ans = new int[M];
        for (int i = 0; i < M; i++) {
            ans[i] = trie.maxXorWithXBehindM(queries[i][0], queries[i][1]);
        }
        return ans;
    }

    public static class Node {
        public int min;
        public Node[] nexts;

        public Node() {
            min = Integer.MAX_VALUE;
            nexts = new Node[2];
        }
    }

    public static class NumTrie {
        public Node head = new Node();

        public void add(int num) {
            Node cur = head;
            head.min = Math.min(head.min, num);
            for (int move = 30; move >= 0; move--) {
                int path = ((num >> move) & 1);
                cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
                cur = cur.nexts[path];
                cur.min = Math.min(cur.min, num);
            }
        }

        // 这个结构中，已经收集了一票数字
        // 请返回哪个数字与X异或的结果最大，返回最大结果
        // 但是，只有<=m的数字，可以被考虑
        public int maxXorWithXBehindM(int x, int m) {
            if (head.min > m) {
                return -1;
            }
            // 一定存在某个数可以和x结合
            Node cur = head;
            int ans = 0;
            for (int move = 30; move >= 0; move--) {
                int path = (x >> move) & 1;
                // 期待遇到的东西
                int best = (path ^ 1);
                best ^= (cur.nexts[best] == null || cur.nexts[best].min > m) ? 1 : 0;
                // best变成了实际遇到的
                ans |= (path ^ best) << move;
                cur = cur.nexts[best];
            }
            return ans;
        }
    }

}
