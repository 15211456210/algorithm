package com.zcp.part5.c301to350;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @author ZCP
 * @title: C347
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/top-k-frequent-elements/description/
 * @date 2022/10/20 9:51
 */
public class C347 {

    class Node {
        int value;
        int count;

        public Node(int val, int count) {
            this.value = val;
            this.count = count;
        }
    }

    // 堆
    public static class MyHeap<T> {
        private ArrayList<T> heap;
        // 任何一个样本在堆上的位置
        private HashMap<T, Integer> indexMap;
        private int heapSize;
        private Comparator<? super T> comparator;

        public MyHeap(Comparator<? super T> com) {
            heap = new ArrayList<>();
            indexMap = new HashMap<>();
            heapSize = 0;
            comparator = com;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public int size() {
            return heapSize;
        }

        public boolean contains(T key) {
            return indexMap.containsKey(key);
        }

        public void push(T value) {
            heap.add(value);
            indexMap.put(value, heapSize);
            heapInsert(heapSize++);
        }

        public T pop() {
            T ans = heap.get(0);
            int end = heapSize - 1;
            swap(0, end);
            heap.remove(end);
            indexMap.remove(ans);
            heapify(0, --heapSize);
            return ans;
        }

        public T peek() {
            return heap.get(0);
        }

        public void resign(T value) {
            int valueIndex = indexMap.get(value);
            heapInsert(valueIndex);
            heapify(valueIndex, heapSize);
        }

        private void heapInsert(int index) {
            while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int index, int heapSize) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                int largest = left + 1 < heapSize && (comparator.compare(heap.get(left + 1), heap.get(left)) < 0)
                        ? left + 1
                        : left;
                largest = comparator.compare(heap.get(largest), heap.get(index)) < 0 ? largest : index;
                if (largest == index) {
                    break;
                }
                swap(largest, index);
                index = largest;
                left = index * 2 + 1;
            }
        }

        private void swap(int i, int j) {
            T o1 = heap.get(i);
            T o2 = heap.get(j);
            heap.set(i, o2);
            heap.set(j, o1);
            indexMap.put(o1, j);
            indexMap.put(o2, i);
        }

    }


    public int[] topKFrequent(int[] nums, int k) {
        int[] ans = new int[k];
        MyHeap<Node> heap = new MyHeap<>((o1, o2) -> {
            return o1.count - o2.count;
        });
        HashMap<Integer, Node> cache = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!cache.containsKey(nums[i])) {
                Node node = new Node(nums[i], 0);
                cache.put(nums[i], node);
            }
            Node node = cache.get(nums[i]);
            node.count++;
            if (heap.contains(node)) {
                heap.resign(node);
            } else {
                if (heap.size() < k) {
                    heap.push(node);
                } else {
                    if (heap.peek().count < node.count) {
                        heap.pop();
                        heap.push(node);
                    }
                }

            }
        }


        for (int i = 0; i < k; i++) {
            if (!heap.isEmpty()) {
                ans[i] = heap.pop().value;
            }
        }

        return ans;


    }
}
