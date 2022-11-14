package com.zcp.part5.c251to300;

import javafx.collections.transformation.SortedList;

import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * @author ZCP
 * @title: C295
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/find-median-from-data-stream/submissions/
 * @date 2022/9/26 17:16
 */
public class C295 {

//    public static class MedianFinder {
//        class TreeNode {
//            int val;
//            int count;
//            TreeNode left;
//            TreeNode right;
//
//            public TreeNode(int val, int count) {
//                this.val = val;
//                this.count = count;
//            }
//        }
//
//        TreeNode root;
//
//        public MedianFinder() {
//
//        }
//
//        public void addNum(int num) {
//            if (root == null) {
//                root = new TreeNode(num, 1);
//                return;
//            }
//            TreeNode cur = root;
//            while (cur != null) {
//                cur.count++;
//                if (cur.val == num) {
//                    break;
//                }
//                if (cur.val > num) {
//                    if (cur.left == null) {
//                        cur.left = new TreeNode(num, 1);
//                        break;
//                    } else {
//                        cur = cur.left;
//                        continue;
//                    }
//                }
//                if (cur.val < num) {
//                    if (cur.right == null) {
//                        cur.right = new TreeNode(num, 1);
//                        break;
//                    } else {
//                        cur = cur.right;
//                        continue;
//                    }
//                }
//            }
//        }
//
//        public double findMedian() {
//            int count = root == null ? 0 : root.count;
//            int mid;
//            int leftCount, rightCount, curCount;
//            if (count % 2 == 1) {
//                return findIndexNum(count / 2 + 1);
//            } else {
//                return (double) (findIndexNum(count / 2) + findIndexNum(count / 2 + 1)) / 2;
//            }
//        }
//
//        /**
//         * 查询第n个数
//         *
//         * @param n
//         * @return
//         */
//        private int findIndexNum(int n) {
//            int leftCount, rightCount, curCount;
//            TreeNode cur = root;
//            while (cur != null) {
//                leftCount = cur.left != null ? cur.left.count : 0;
//                rightCount = cur.right != null ? cur.right.count : 0;
//                curCount = cur.count - leftCount - rightCount;
//                if (n <= leftCount) {
//                    cur = cur.left;
//                    continue;
//                } else if (n <= leftCount + curCount) {
//                    return cur.val;
//                } else {
//                    n = n - leftCount - curCount;
//                    cur = cur.right;
//                    continue;
//                }
//            }
//            return 0;
//        }
//    }

    class MedianFinder {
        PriorityQueue<Integer> queMin;
        PriorityQueue<Integer> queMax;

        public MedianFinder() {
            queMin = new PriorityQueue<Integer>((a, b) -> (b - a));
            queMax = new PriorityQueue<Integer>((a, b) -> (a - b));
        }

        public void addNum(int num) {
            if (queMin.isEmpty() || num <= queMin.peek()) {
                queMin.offer(num);
                if (queMax.size() + 1 < queMin.size()) {
                    queMax.offer(queMin.poll());
                }
            } else {
                queMax.offer(num);
                if (queMax.size() > queMin.size()) {
                    queMin.offer(queMax.poll());
                }
            }
        }

        public double findMedian() {
            if (queMin.size() > queMax.size()) {
                return queMin.peek();
            }
            return (queMin.peek() + queMax.peek()) / 2.0;
        }
    }

//    public static void main(String[] args) {
//        C295 c295 = new C295();
//
//        MedianFinder medianFinder = new MedianFinder();
//
//        medianFinder.addNum(6);
//        medianFinder.addNum(10);
//        medianFinder.addNum(2);
//        medianFinder.addNum(6);
//        medianFinder.addNum(5);
//        medianFinder.addNum(0);
//
//        System.out.println(medianFinder.findMedian());
//
//
//    }
}
