package com.zcp.part2.heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.WeakHashMap;

/**
 * 堆结构
 * 用数组实现的完全二叉树,（并实现数组动态扩容）
 * 小根堆
 */
public class MyHeapD {

    public static class MyHeap {
        int[] heap;//数组表示的完全二叉树
        int size;


        public MyHeap() {
            this.heap = new int[8];
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

    public static void main(String[] args) {
        int testTime = 1000000;
        int range = 500;
        MyHeap myHeap = new MyHeap();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int opt = 0;//0 add,1 poll,2 peek
            do {
                opt = (int) (Math.random() * 3);
            } while ((opt == 1 || opt == 2) && priorityQueue.isEmpty());
            switch (opt) {
                case 0:
                    int val = (int) (Math.random() * range) - (int) (Math.random() * range);
                    myHeap.add(val);
                    priorityQueue.add(val);
                    break;
                case 1:
                    if(myHeap.poll() != priorityQueue.poll()){
                        System.out.println("出错了");
                        return;
                    }
                    break;
                case 2:
                    if(myHeap.peek() != priorityQueue.peek()){
                        System.out.println("出错了");
                        return;
                    }
                    break;
            }
        }
        System.out.println("测试结束");
    }


}
