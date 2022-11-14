package com.zcp.part5.c051to100;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @author ZCP
 * @title: c056
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/merge-intervals/submissions/
 * @date 2022/8/28 16:35
 */
public class c056 {

    class Interval {
        int begin;
        int end;

        public Interval(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }
    }


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

    public int[][] merge(int[][] intervals) {
        MyHeap<Interval> heap = new MyHeap<Interval>((o1, o2) -> {
            return o1.begin - o2.begin;
        });
        int n = intervals.length;
        for (int i = 0; i < n; i++) {
            heap.push(new Interval(intervals[i][0], intervals[i][1]));
        }

        List<Interval> list = new ArrayList<Interval>();
        while (!heap.isEmpty()) {
            Interval pop = heap.pop();
            int begin = pop.begin;
            int end = pop.end;
            while (!heap.isEmpty() && heap.peek().begin <= end) {
                end = Math.max(end, heap.pop().end);
            }
            list.add(new Interval(begin, end));
        }
        int[][] ans = new int[list.size()][2];
        for (int i = 0; i < ans.length; i++) {
            ans[i][0] = list.get(i).begin;
            ans[i][1] = list.get(i).end;
        }
        return ans;
    }
}
