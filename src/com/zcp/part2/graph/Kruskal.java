package com.zcp.part2.graph;

import java.util.*;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/15
 * @description：最小生成树Kruskal算法
 * 1）总是从权值最小的边开始考虑，依次考察权值依次变大的边
 * 2）当前的边要么进入最小生成树的集合，要么丢弃
 * 3）如果当前的边进入最小生成树的集合中不会形成环，就要当前边
 * 4）如果当前的边进入最小生成树的集合中会形成环，就不要当前边
 * 5）考察完所有边之后，最小生成树的集合也得到了
 * @version:
 */
public class Kruskal {

    public static List<Graph.Edge> solution(Graph graph) {
        if (graph == null || graph.edges == null || graph.edges.size() < 1) {
            return null;
        }
        UniunSet<Graph.Node> nodeUniunSet = new UniunSet<Graph.Node>(graph.nodes);
        PriorityQueue<Graph.Edge> edgesQueue = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        for (Graph.Edge edge : graph.edges) {
            edgesQueue.offer(edge);
        }

        ArrayList<Graph.Edge> ans = new ArrayList<>();
        while (!edgesQueue.isEmpty()) {
            Graph.Edge poll = edgesQueue.poll();
            if (nodeUniunSet.isSameSet(poll.from, poll.to)) {
                continue;
            }
            ans.add(poll);
            nodeUniunSet.uniun(poll.from, poll.to);
        }
        return ans;
    }

    public static void main(String[] args) {
        Graph graph = generanteRandomGraph(10, 10);
        List<Graph.Edge> solution = solution(graph);
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
                Graph.Edge edge = new Graph.Edge((int) (Math.random() * weight), node, randTo);
                node.toEdges.add(edge);
                edges.add(edge);
                node.neighbors.add(randTo);
                set.add(randTo);
            }
            nodes.add(node);
        }
        return new Graph(edges, nodes);
    }

    /**
     * 并查集解决连通性问题
     *
     * @param <T>
     */
    public static class UniunSet<T> {
        HashMap<T, Integer> nodes;
        int[] parents;
        int[] sizes;

        public UniunSet(List<T> list) {
            nodes = new HashMap<>();
            parents = new int[list.size()];
            sizes = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                nodes.put(list.get(i), i);
                parents[i] = i;
            }

        }

        public void uniun(T a, T b) {
            if (isSameSet(a, b)) {
                return;
            }
            int aHead = findHead(nodes.get(a));
            int bHead = findHead(nodes.get(b));
            if (sizes[aHead] > sizes[bHead]) {
                parents[bHead] = aHead;
                sizes[aHead] = sizes[aHead] + sizes[bHead];
                sizes[bHead] = 0;
            } else {
                parents[aHead] = bHead;
                sizes[bHead] = sizes[aHead] + sizes[bHead];
                sizes[aHead] = 0;
            }
        }

        public boolean isSameSet(T a, T b) {
            return findHead(nodes.get(a)) == findHead(nodes.get(b));
        }

        public int findHead(int index) {
            while (parents[index] != index) {
                index = parents[index];
            }
            return index;
        }

    }


}
