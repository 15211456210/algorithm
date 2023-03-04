package com.zcp.part5.c451to500;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author ZCP
 * @title: C451
 * @projectName algorithm
 * @description: https://leetcode.com/problems/sort-characters-by-frequency/
 * @date 2023/2/3 9:37
 */
public class C451 {

    // 加强堆
    public class MyHeap<T> {
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

        public T get(T key) {
            return contains(key) ? heap.get(indexMap.get(key)) : null;
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

        public void del(T value) {
            if (indexMap.containsKey(value)) {
                Integer index = indexMap.get(value);
                int end = heapSize - 1;
                swap(index, end);
                heap.remove(end);
                indexMap.remove(value);
                heapify(index, --heapSize);
            }
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

    public class Node {
        char c;
        int count;

        public Node(char c, int count) {
            this.c = c;
            this.count = count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node node = (Node) o;
            return c == node.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(c);
        }
    }

    public String frequencySort(String s) {
        MyHeap<Node> heap = new MyHeap<>((o1, o2) -> o2.count - o1.count);

        for (int i = 0; i < s.length(); i++) {
            Node node = new Node(s.charAt(i), 1);
            if (!heap.contains(node)) {
                heap.push(node);
            } else {
                node = heap.get(node);
                node.count++;
                heap.resign(node);
            }
        }
        StringBuffer ans = new StringBuffer();
        while (!heap.isEmpty()) {
            Node pop = heap.pop();
            for (int i = 0; i < pop.count; i++) {
                ans.append(pop.c);
            }
        }
        return ans.toString();
    }
}
