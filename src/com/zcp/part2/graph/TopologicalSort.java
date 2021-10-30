package com.zcp.part2.graph;

import com.zcp.part2.heap.MyHeapPlus;

import java.util.*;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/15
 * @description：拓扑排序 https://www.lintcode.com/problem/127/
 * @version:
 */
public class TopologicalSort {

    public static class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        if (graph == null || graph.size() < 1) {
            return null;
        }
        HashMap<Integer, Node> map = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            if (!map.containsKey(node.label)) {
                map.put(node.label, new Node(node));
            }
            Node node1 = map.get(node.label);
            for (DirectedGraphNode neighbor : node.neighbors) {
                if (!map.containsKey(neighbor.label)) {
                    map.put(neighbor.label, new Node(neighbor));
                }
                Node neighbor1 = map.get(neighbor.label);
                node1.neighbors.add(neighbor1);
                neighbor1.fromCount++;
            }
        }

        Heap<Node> integerHeap = new Heap<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.fromCount-o2.fromCount;
            }
        });
        map.forEach((key, value) -> {
            integerHeap.add(value);
        });
        ArrayList<DirectedGraphNode> list = new ArrayList<>();
        while (!integerHeap.isEmpty()) {
            Node poll = integerHeap.poll();
            for (Node neighbor : poll.neighbors) {
                //来源指针减1
                neighbor.fromCount--;
                //加强堆重新排一下这个节点
                integerHeap.reSort(neighbor);
            }
            list.add((DirectedGraphNode) poll.val);
        }
        return list;
    }

    /**
     * 加强堆：
     * 增加了元素所在的索引，当某个元素改变时，可以对指定元数进行重排序，使得堆一直保持堆结构
     * @param <T>
     */
    public static class Heap<T> {
        ArrayList<T> heap;
        HashMap<T, Integer> indexMap;
        private Comparator<T> comparator;

        public Heap(Comparator<T> comparator) {
            heap = new ArrayList<>();
            indexMap = new HashMap<>();
            this.comparator = comparator;
        }

        public int size() {
            return heap.size();
        }

        public boolean isEmpty() {
            return heap.size() == 0;
        }

        public void reSort(T val) {
            Integer index = indexMap.get(val);
            if (index!=null){
                heapify(index);
                heapInsert(index);
            }

        }

        /**
         * 加入元素
         *
         * @param val
         */
        public void add(T val) {
            heap.add(val);
            indexMap.put(val, heap.size() - 1);
            heapInsert(heap.size() - 1);
        }


        /**
         * 返回第一个元素，并将该元素删除
         *
         * @return
         */
        public T poll() {
            swap(0, heap.size() - 1);
            T remove = heap.remove(heap.size() - 1);
            indexMap.remove(remove);
            //heapify
            heapify(0);
            return remove;
        }

        public void heapify(int index) {
//            int index = 0;
            int left = index * 2 + 1;
            int size = heap.size();
            while (left < size) {
                int right = left + 1;
                if (right < size && comparator.compare(heap.get(left), heap.get(right)) > 0) {
                    swap(index, right);
                    index = right;
                } else if (comparator.compare(heap.get(left), heap.get(index)) < 0) {
                    swap(index, left);
                    index = left;
                } else {
                    break;
                }
                left = index * 2 + 1;
            }
        }

        public void heapInsert(int index) {
            if (heap.size() <= 1) {
                return;
            }
//            int index = heap.size() - 1;
            while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        /**
         * 交换堆元素，同时交换反向索引表元素
         *
         * @param index
         * @param index2
         */
        private void swap(int index, int index2) {
            T w1 = heap.get(index);
            T w2 = heap.get(index2);
            heap.set(index, w2);
            heap.set(index2, w1);
            indexMap.put(w1, index2);
            indexMap.put(w2, index);
        }
    }


    public static class Node {
        Object val;
        //指向该节点数量
        int fromCount;
        List<Node> neighbors;

        public Node(Object val) {
            this.val = val;
            fromCount = 0;
            neighbors = new ArrayList<>();
        }
    }


    public static void main(String[] args) {
        DirectedGraphNode node0 = new DirectedGraphNode(0);
        DirectedGraphNode node1 = new DirectedGraphNode(1);
        DirectedGraphNode node2 = new DirectedGraphNode(2);
        DirectedGraphNode node3 = new DirectedGraphNode(3);
        DirectedGraphNode node4 = new DirectedGraphNode(4);
//        DirectedGraphNode node5 = new DirectedGraphNode(5);

        node0.neighbors.add(node1);
        node0.neighbors.add(node3);

        node1.neighbors.add(node1);
//        node1.neighbors.add(node4);

        node2.neighbors.add(node0);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node2.neighbors.add(node4);


        node3.neighbors.add(node1);

        node4.neighbors.add(node0);
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);



        ArrayList<DirectedGraphNode> list = new ArrayList<>();
        list.add(node0);
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        ArrayList<DirectedGraphNode> ans = topSort(list);
        System.out.println();
    }

}
