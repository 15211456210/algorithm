package com.zcp.part2.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/15
 * @description：图的深度优先遍历
 * @version:
 */
public class GraphDFS {

    public static void solution(Graph graph) {
        if (graph == null || graph.nodes == null || graph.nodes.size() < 1) {
            return;
        }
        Stack<Graph.Node> nodeStack = new Stack<>();
        HashSet<Graph.Node> set = new HashSet<>();

        nodeStack.push(graph.nodes.get(0));
        while (!nodeStack.isEmpty()){
            Graph.Node pop = nodeStack.pop();
            if (!set.contains(pop)){
                System.out.println(pop.val);
                set.add(pop);
            }
            for (Graph.Node neighbor : pop.neighbors) {
                if (!set.contains(neighbor)){
                    nodeStack.push(pop);
                    nodeStack.push(neighbor);
                    break;
                }
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


    public static void main(String[] args) {
        Graph graph = generanteRandomGraph(10);
        System.out.println("graph size:" + graph.nodes.size());
        solution(graph);
    }
}
