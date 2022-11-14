package com.zcp.part5.c351to400;

import java.util.Arrays;

/**
 * @author ZCP
 * @title: C378
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/kth-smallest-element-in-a-sorted-matrix/description/
 * @date 2022/10/29 11:22
 */
public class C378 {

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
     * treemap
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

        MyHeap myHeap = new MyHeap(m * n);


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
}
