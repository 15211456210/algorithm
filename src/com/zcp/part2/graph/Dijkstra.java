package com.zcp.part2.graph;

import com.zcp.part2.bucket.PreTree;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/16
 * @description：Dijkstra算法(路由算法) 1）Dijkstra算法必须指定一个源点
 * 2）生成一个源点到各个点的最小距离表，一开始只有一条记录，即原点到自己的最小距离为0，源点到其他所有点的最小距离都为正无穷大
 * 3）从距离表中拿出没拿过记录里的最小记录，通过这个点发出的边，更新源点到各个点的最小距离表，不断重复这一步
 * 4）源点到所有的点记录如果都被拿过一遍，过程停止，最小距离表得到了
 * @version:
 */
public class Dijkstra {

    public static HashMap<Graph.Node, Integer> solution(Graph graph, Graph.Node root) {
        if (graph == null || graph.edges == null || graph.edges.size() < 1) {
            return null;
        }
        HashMap<Graph.Node, Integer> ans = new HashMap<>();
        HashMap<Graph.Node, Info> map = new HashMap<>();
        Heap<Info> infoHeap = new Heap<Info>((o1, o2) -> o1.far - o2.far);
        HashSet<Graph.Node> fixed = new HashSet<>();
        Info info = new Info(root, 0);
        map.put(root, info);
        infoHeap.add(info);
        while (!infoHeap.isEmpty()) {
            Info poll = infoHeap.poll();
            for (Graph.Edge toEdge : poll.dest.toEdges) {
                if (fixed.contains(toEdge.to)) {
                    continue;
                }
                int far = toEdge.weight + poll.far;
                if (!map.containsKey(toEdge.to)) {
                    Info dest = new Info(toEdge.to, far);
                    map.put(toEdge.to, dest);
                    infoHeap.add(dest);
                } else {
                    //已经有了,更新距离
                    if (far < map.get(toEdge.to).far) {
                        Info dest = map.get(toEdge.to);
                        dest.far = far;
                        infoHeap.reSort(dest);//重新调整堆
                    }
                }
            }
            //当前节点固定了
            fixed.add(poll.dest);
        }

        map.forEach((key, value) -> {
            ans.put(key, value.far);
        });
        return ans;
    }

    public static void main(String[] args) {
        Graph graph = generanteRandomGraph(5, 10);
        HashMap<Graph.Node, Integer> solution = solution(graph, graph.nodes.get(0));
        System.out.println();
    }

    /**
     * 根据大小\权重随机生成无向图
     *
     * @param size
     * @return
     */
    public static Graph generanteRandomGraph(int size, int weight) {
        int sz = (int) (Math.random() * size);
        List<Graph.Node> nodes = new ArrayList<>();
        List<Graph.Edge> edges = new ArrayList<>();
        nodes.add(new Graph.Node(0));
        for (int i = 0; i < sz; i++) {
            Graph.Node node = new Graph.Node(i + 1);
            int edgeSize = (int) (Math.random() * nodes.size()) + 1;
            HashSet<Graph.Node> set = new HashSet<>();
            for (int k = 0; k < edgeSize; k++) {
                //随机选择一个已有的节点连接
                Graph.Node randTo = null;
                do {
                    randTo = nodes.get((int) (Math.random() * nodes.size()));
                } while (set.contains(randTo));
                int ranWeight = (int) (Math.random() * weight) + 1;
                Graph.Edge edge = new Graph.Edge(ranWeight, node, randTo);
                Graph.Edge edge2 = new Graph.Edge(ranWeight, randTo, node);
                node.toEdges.add(edge);
                randTo.toEdges.add(edge2);
                edges.add(edge);
                edges.add(edge2);
                node.neighbors.add(randTo);
                randTo.neighbors.add(node);
                set.add(randTo);
            }
            nodes.add(node);
        }
        return new Graph(edges, nodes);
    }


    /**
     * 加强堆：
     * 增加了元素所在的索引，当某个元素改变时，可以对指定元数进行重排序，使得堆一直保持堆结构
     *
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
            if (index != null) {
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

        public boolean contains(T key) {
            return indexMap.containsKey(key);
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


    public static class Info {
        Graph.Node dest;
        Integer far;

        public Info(Graph.Node dest, Integer far) {
            this.dest = dest;
            this.far = far;
        }
    }


}
