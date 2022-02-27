package com.zcp.part4.day17;

import java.util.*;

// 本题测试链接 : https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
public class Code02_KthSmallestElementInSortedMatrix {



    public static class MyHeap {
        int[] heap;//数组表示的完全二叉树
        int size;


        public MyHeap(int size) {
            this.heap = new int[size];
            this.size = 0;
        }

        public void add(int val) {
            heapInsert(val);
        }

        public int poll() {
            if (size == 0) {
                throw new RuntimeException("heap is empty");
            }
            heapfiy();
            return heap[size];
        }

        public int peek() {
            if (size == 0) {
                throw new RuntimeException("heap is empty");
            }
            return heap[0];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        /**
         * 把值放入数组正确位置
         *
         * @param val
         */
        public void heapInsert(int val) {
            if (size == heap.length) {
                //数组满了，动态扩容
                int[] newHeap = Arrays.copyOf(heap, size << 1);
                heap = newHeap;
            }
            heap[size] = val;
            int index = size;
            size++;
            while (heap[index] < heap[(index - 1) / 2]) {
                //如果当前节点小于父节点，和父节点交换
                swap(index, (index - 1) >> 1);
                index = (index - 1) >> 1;
            }
        }

        /**
         * 设置数组
         *
         * @return
         */
        public void heapfiy() {
            swap(0, size - 1);
            size--;
            int index = 0;
            int left = (index << 1) + 1;
            while (left < size) {
                int right = left + 1;
                if (right < size && heap[right] < heap[left] && heap[index] > heap[right]) {
                    swap(index, right);
                    index = right;
                } else if (heap[index] > heap[left]) {
                    swap(index, left);
                    index = left;
                } else {
                    break;
                }
                left = (index << 1) + 1;
            }
        }

        public void swap(int index1, int index2) {
            int tmp = heap[index1];
            heap[index1] = heap[index2];
            heap[index2] = tmp;
        }

    }

    /**
     * 思路：
     * 堆
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null) {
            return -1;
        }
        int m = matrix.length;
        int n = matrix[0].length;

        if (m * n < k) {
            return -1;
        }

        MyHeap myHeap = new MyHeap(m*n);


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                myHeap.add(matrix[i][j]);
            }
        }

        int result = 0;

        for (int i = 0; i < k; i++) {
            result = myHeap.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        new Code02_KthSmallestElementInSortedMatrix().kthSmallest(new int[][]{{2,2},{2,2}},2);
    }

    // 堆的方法
    public static int kthSmallest1(int[][] matrix, int k) {
        int N = matrix.length;
        int M = matrix[0].length;
        PriorityQueue<Node> heap = new PriorityQueue<>(new NodeComparator());
        boolean[][] set = new boolean[N][M];
        heap.add(new Node(matrix[0][0], 0, 0));
        set[0][0] = true;
        int count = 0;
        Node ans = null;
        while (!heap.isEmpty()) {
            ans = heap.poll();
            if (++count == k) {
                break;
            }
            int row = ans.row;
            int col = ans.col;
            if (row + 1 < N && !set[row + 1][col]) {
                heap.add(new Node(matrix[row + 1][col], row + 1, col));
                set[row + 1][col] = true;
            }
            if (col + 1 < M && !set[row][col + 1]) {
                heap.add(new Node(matrix[row][col + 1], row, col + 1));
                set[row][col + 1] = true;
            }
        }
        return ans.value;
    }

    public static class Node {
        public int value;
        public int row;
        public int col;

        public Node(int v, int r, int c) {
            value = v;
            row = r;
            col = c;
        }

    }

    public static class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.value - o2.value;
        }

    }

    // 二分的方法
    public static int kthSmallest2(int[][] matrix, int k) {
        int N = matrix.length;
        int M = matrix[0].length;
        int left = matrix[0][0];
        int right = matrix[N - 1][M - 1];
        int ans = 0;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            // <=mid 有几个 <= mid 在矩阵中真实出现的数，谁最接近mid
            Info info = noMoreNum(matrix, mid);
            if (info.num < k) {
                left = mid + 1;
            } else {
                ans = info.near;
                right = mid - 1;
            }
        }
        return ans;
    }

    public static class Info {
        public int near;
        public int num;

        public Info(int n1, int n2) {
            near = n1;
            num = n2;
        }
    }

    public static Info noMoreNum(int[][] matrix, int value) {
        int near = Integer.MIN_VALUE;
        int num = 0;
        int N = matrix.length;
        int M = matrix[0].length;
        int row = 0;
        int col = M - 1;
        while (row < N && col >= 0) {
            if (matrix[row][col] <= value) {
                near = Math.max(near, matrix[row][col]);
                num += col + 1;
                row++;
            } else {
                col--;
            }
        }
        return new Info(near, num);
    }

}
