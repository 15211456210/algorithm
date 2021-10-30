package com.zcp.part2.graph;

import java.util.*;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/15
 * @description：图的广度优先遍历
 * @version:
 */
public class GraphBFS {

    public static void solution(Graph graph) {
        if (graph == null || graph.nodes == null || graph.nodes.size() < 1) {
            return;
        }
        Queue<Graph.Node> queue = new LinkedList<>();
        HashSet<Graph.Node> set = new HashSet<>();

        queue.offer(graph.nodes.get(0));
        set.add(graph.nodes.get(0));
        while (!queue.isEmpty()){
            Graph.Node poll = queue.poll();
            System.out.println(poll.val);
            for (Graph.Node neighbor : poll.neighbors) {
                if (set.contains(neighbor)){
                    continue;
                }
                queue.offer(neighbor);
                set.add(neighbor);
            }
        }

    }

    /**
     * 根据大小随机生成无向图
     * @param size
     * @return
     */
    public static Graph generanteRandomGraph(int size){
        int sz = (int)(Math.random()*size);
        List<Graph.Node> nodes = new ArrayList<>();
        nodes.add(new Graph.Node(0));
        for (int i = 0; i < sz; i++) {
            Graph.Node node = new Graph.Node(i + 1);
            nodes.get((int)(Math.random()*nodes.size())).neighbors.add(node);
            nodes.add(node);
        }
        return new Graph(null,nodes);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        solution(generanteRandomGraph(10));
    }
}
