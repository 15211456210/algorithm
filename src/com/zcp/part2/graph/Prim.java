package com.zcp.part2.graph;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/16
 * @description：最小生成树Prim算法 1）可以从任意节点出发来寻找最小生成树
 * 2）某个点加入到被选取的点中后，解锁这个点出发的所有新的边
 * 3）在所有解锁的边中选最小的边，然后看看这个边会不会形成环
 * 4）如果会，不要当前边，继续考察剩下解锁的边中最小的边，重复3）
 * 5）如果不会，要当前边，将该边的指向点加入到被选取的点中，重复2）
 * 6）当所有点都被选取，最小生成树就得到了
 * @version:
 */
public class Prim {

    public static List<Graph.Edge> solution(Graph graph) {
        if (graph == null || graph.edges == null || graph.edges.size() < 1) {
            return null;
        }
        HashSet<Graph.Node> nodes = new HashSet<>();
        PriorityQueue<Graph.Edge> edges = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        ArrayList<Graph.Edge> ans = new ArrayList<>();
        Graph.Node first = graph.nodes.get(0);
        nodes.add(first);
        for (Graph.Edge toEdge : first.toEdges) {
            edges.add(toEdge);
        }
        while (!edges.isEmpty()) {
            Graph.Edge pop = edges.poll();
            Graph.Node to = pop.to;
            if (nodes.contains(to)) {
                continue;
            }
            nodes.add(to);
            ans.add(pop);
            for (Graph.Edge toEdge : to.toEdges) {
                edges.offer(toEdge);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int testTime = 10000;
        int size = 100;
        int weight = 10;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            Graph graph = generanteRandomGraph(size, weight);
            List<Graph.Edge> solutionK = Kruskal.solution(graph);
            List<Graph.Edge> solutionP = Prim.solution(graph);
            if (!check(solutionK, solutionP)) {
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }

    private static boolean check(List<Graph.Edge> solutionK, List<Graph.Edge> solutionP) {
        if (solutionK == null && solutionP == null) {
            return true;
        }
        if (solutionK.size() != solutionP.size()) {
            return false;
        }

        int sumK = 0;
        for (Graph.Edge edge : solutionK) {
            sumK += edge.weight;
        }

        int sumP = 0;
        for (Graph.Edge edge : solutionP) {
            sumP += edge.weight;
        }

        return sumK == sumP;
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


}
