package com.zcp.part5.c401to450;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author ZCP
 * @title: C432
 * @projectName algorithm
 * @description: https://leetcode.com/problems/all-oone-data-structure/
 * @date 2023/1/27 21:08
 */
public class C432 {


    public static class AllOne {

        // 堆
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

        class Node {
            String key;
            int count;

            public Node(String key, int count) {
                this.key = key;
                this.count = count;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) {
                    return true;
                }
                if (o instanceof String) {
                    return Objects.equals(key, o);
                }
                if (o == null || getClass() != o.getClass()) {
                    return false;
                }
                Node node = (Node) o;
                return Objects.equals(key, node.key);
            }

            @Override
            public int hashCode() {
                return Objects.hash(key);
            }
        }

        MyHeap<Node> maxHeap;
        MyHeap<Node> minHeap;

        public AllOne() {
            maxHeap = new MyHeap<>(Comparator.comparingInt(n -> -n.count));
            minHeap = new MyHeap<>(Comparator.comparingInt(n -> n.count));
        }

        public void inc(String key) {
            Node node = new Node(key, 1);
            if (maxHeap.contains(node)) {
                node = maxHeap.get(node);
                node.count++;
                maxHeap.resign(node);
                minHeap.resign(node);
            } else {
                node.key = key;
                node.count = 1;
                maxHeap.push(node);
                minHeap.push(node);
            }

        }

        public void dec(String key) {
            Node node = new Node(key, 1);
            if (maxHeap.contains(node)) {
                node = maxHeap.get(node);
                if (--node.count == 0) {
                    // 删除
                    maxHeap.del(node);
                    minHeap.del(node);
                } else {
                    maxHeap.resign(node);
                    minHeap.resign(node);
                }


            }
        }

        public String getMaxKey() {
            return maxHeap.size() > 0 ? maxHeap.peek().key : "";
        }

        public String getMinKey() {
            return minHeap.size() > 0 ? minHeap.peek().key : "";
        }
    }


    public static void main(String[] args) {
        AllOne allOne = new AllOne();
        allOne.inc("hello");
        allOne.inc("hello");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
        allOne.inc("leet");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
    }

}
