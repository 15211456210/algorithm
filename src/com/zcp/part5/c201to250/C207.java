package com.zcp.part5.c201to250;

import java.util.*;

/**
 * @author ZCP
 * @title: C207
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/course-schedule/submissions/
 * @date 2022/9/16 9:17
 */
public class C207 {

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

    class Node {
        int num;
        Set<Integer> list;

        public Node(int num) {
            this.num = num;
            this.list = new HashSet<Integer>();
        }
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        MyHeap<Node> heap = new MyHeap<Node>((n1, n2) -> {
            return n1.list.size() - n2.list.size();
        });
        HashMap<Integer, Node> map = new HashMap<>();
        HashMap<Integer, Set<Node>> index = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {

            if (!map.containsKey(prerequisites[i][0])) {
                Node node = new Node(prerequisites[i][0]);
                map.put(prerequisites[i][0], node);
                heap.push(node);
            }
            Node node = map.get(prerequisites[i][0]);
            node.list.add(prerequisites[i][1]);
            heap.resign(node);

            if (!index.containsKey(prerequisites[i][1])) {
                index.put(prerequisites[i][1], new HashSet<Node>());
            }
            index.get(prerequisites[i][1]).add(node);
        }

        for (int num : index.keySet()) {
            if (!map.containsKey(num)) {
                Set<Node> set = index.get(num);
                if (set != null) {
                    for (Node node : set) {
                        node.list.remove(num);
                        heap.resign(node);
                    }
                }
            }
        }


        while (!heap.isEmpty() && heap.peek().list.size() == 0) {
            Node pop = heap.pop();
            Set<Node> set = index.get(pop.num);
            if (set != null) {
                for (Node node : set) {
                    node.list.remove(pop.num);
                    heap.resign(node);
                }
            }
        }
        return heap.isEmpty();
    }
}
