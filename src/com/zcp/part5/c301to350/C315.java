package com.zcp.part5.c301to350;

import com.zcp.common.ListNode;

import java.util.*;

/**
 * @author ZCP
 * @title: C315
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/count-of-smaller-numbers-after-self/
 * @date 2022/9/29 16:48
 */
public class C315 {

//    public static class SBTNode {
//        public long key;
//        public SBTNode l;
//        public SBTNode r;
//        public long size; // 不同key的size
//        public long all; // 总的size
//
//        public SBTNode(long k) {
//            key = k;
//            size = 1;
//            all = 1;
//        }
//    }
//
//    public static class SizeBalancedTreeSet {
//        private SBTNode root;
//        private HashSet<Long> set = new HashSet<>();
//
//        private SBTNode rightRotate(SBTNode cur) {
//            long same = cur.all - (cur.l != null ? cur.l.all : 0) - (cur.r != null ? cur.r.all : 0);
//            SBTNode leftNode = cur.l;
//            cur.l = leftNode.r;
//            leftNode.r = cur;
//            leftNode.size = cur.size;
//            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
//            // all modify
//            leftNode.all = cur.all;
//            cur.all = (cur.l != null ? cur.l.all : 0) + (cur.r != null ? cur.r.all : 0) + same;
//            return leftNode;
//        }
//
//        private SBTNode leftRotate(SBTNode cur) {
//            long same = cur.all - (cur.l != null ? cur.l.all : 0) - (cur.r != null ? cur.r.all : 0);
//            SBTNode rightNode = cur.r;
//            cur.r = rightNode.l;
//            rightNode.l = cur;
//            rightNode.size = cur.size;
//            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
//            // all modify
//            rightNode.all = cur.all;
//            cur.all = (cur.l != null ? cur.l.all : 0) + (cur.r != null ? cur.r.all : 0) + same;
//            return rightNode;
//        }
//
//        private SBTNode matain(SBTNode cur) {
//            if (cur == null) {
//                return null;
//            }
//            if (cur.l != null && cur.l.l != null && cur.r != null && cur.l.l.size > cur.r.size) {
//                cur = rightRotate(cur);
//                cur.r = matain(cur.r);
//                cur = matain(cur);
//            } else if (cur.l != null && cur.l.r != null && cur.r != null && cur.l.r.size > cur.r.size) {
//                cur.l = leftRotate(cur.l);
//                cur = rightRotate(cur);
//                cur.l = matain(cur.l);
//                cur.r = matain(cur.r);
//                cur = matain(cur);
//            } else if (cur.r != null && cur.r.r != null && cur.l != null && cur.r.r.size > cur.l.size) {
//                cur = leftRotate(cur);
//                cur.l = matain(cur.l);
//                cur = matain(cur);
//            } else if (cur.r != null && cur.r.l != null && cur.l != null && cur.r.l.size > cur.l.size) {
//                cur.r = rightRotate(cur.r);
//                cur = leftRotate(cur);
//                cur.l = matain(cur.l);
//                cur.r = matain(cur.r);
//                cur = matain(cur);
//            }
//            return cur;
//        }
//
//        private SBTNode add(SBTNode cur, long key, boolean contains) {
//            if (cur == null) {
//                return new SBTNode(key);
//            } else {
//                cur.all++;
//                if (key == cur.key) {
//                    return cur;
//                } else { // 还在左滑或者右滑
//                    if (!contains) {
//                        cur.size++;
//                    }
//                    if (key < cur.key) {
//                        cur.l = add(cur.l, key, contains);
//                    } else {
//                        cur.r = add(cur.r, key, contains);
//                    }
//                    return matain(cur);
//                }
//            }
//        }
//
//        public void add(long sum) {
//            boolean contains = set.contains(sum);
//            root = add(root, sum, contains);
//            set.add(sum);
//        }
//
//        public long lessKeySize(long key) {
//            SBTNode cur = root;
//            long ans = 0;
//            while (cur != null) {
//                if (key == cur.key) {
//                    return ans + (cur.l != null ? cur.l.all : 0);
//                } else if (key < cur.key) {
//                    cur = cur.l;
//                } else {
//                    ans += cur.all - (cur.r != null ? cur.r.all : 0);
//                    cur = cur.r;
//                }
//            }
//            return ans;
//        }
//
//        // > 7 8...
//        // <8 ...<=7
//        public long moreKeySize(long key) {
//            return root != null ? (root.all - lessKeySize(key + 1)) : 0;
//        }
//
//
//    }
//
//    public List<Integer> countSmaller(int[] nums) {
//        int len = nums.length;
//        SizeBalancedTreeSet orderTree = new SizeBalancedTreeSet();
//
//        orderTree.add(nums[len - 1]);
//        int[] ans = new int[len];
//
//        for (int i = len - 2; i >= 0; i--) {
//            ans[i] = (int) orderTree.lessKeySize(nums[i]);
//            orderTree.add(nums[i]);
//        }
//        ArrayList<Integer> ansList = new ArrayList<>(len);
//        for (int num : ans) {
//            ansList.add(num);
//        }
//        return ansList;
//    }

    /**
     * 归并 算法实现
     *
     * @param nums
     * @return
     */
    public List<Integer> countSmaller(int[] nums) {

        int[] ans = new int[nums.length];

        // index[i] 表示 排序后 nums[i] 对应原来的数值下标
        int[] index = new int[nums.length];
        for (int i = 0; i < index.length; i++) {
            index[i] = i;
        }

        merge(nums, 0, nums.length - 1, ans, index);

        ArrayList<Integer> list = new ArrayList<>();

        for (int num : ans) {
            list.add(num);
        }
        return list;

    }

    private void merge(int[] nums, int l, int r, int[] ans, int[] index) {
        if (l >= r) {
            return;
        }
        int len = r - l + 1;
        // 存放排序后的数组
        int[] nextNum = new int[len];
        int[] nextIndex = new int[len];
        int curIndex = 0;
        int mid = l + (r - l) / 2;
        merge(nums, l, mid, ans, index);
        merge(nums, mid + 1, r, ans, index);
        int lIdx = l;
        int rIdx = mid + 1;

        while (lIdx <= mid || rIdx <= r) {
            if (lIdx > mid) {
                nextNum[curIndex] = nums[rIdx];
                nextIndex[curIndex++] = index[rIdx++];
                continue;
            } else if (rIdx > r) {
                ans[index[lIdx]] += r - mid;
                nextNum[curIndex] = nums[lIdx];
                nextIndex[curIndex++] = index[lIdx++];
                continue;
            }
            if (nums[lIdx] <= nums[rIdx]) {
                ans[index[lIdx]] += rIdx - mid - 1;
                nextNum[curIndex] = nums[lIdx];
                nextIndex[curIndex++] = index[lIdx++];
            } else {
//                nums[lIdx] > nums[rIdx]
                nextNum[curIndex] = nums[rIdx];
                nextIndex[curIndex++] = index[rIdx++];
            }
        }
        System.arraycopy(nextNum, 0, nums, l, len);
        System.arraycopy(nextIndex, 0, index, l, len);
    }

    public static void main(String[] args) {
        System.out.println(new C315().countSmaller(new int[]{2, 5, 6, 8, 4, 6, 5}));
    }
}
