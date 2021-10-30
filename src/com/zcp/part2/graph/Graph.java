package com.zcp.part2.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/15
 * @description：图: 点和边 的集合
 * @version:
 */
public class Graph {
    List<Edge> edges;
    List<Node> nodes;

    public Graph() {
        this.edges = new ArrayList<>();
        this.nodes = new ArrayList<>();
    }

    public Graph(List<Edge> edges, List<Node> nodes) {
        this.edges = edges;
        this.nodes = nodes;
    }

    public static class Edge {

        int weight;//权重
        Node from;
        Node to;

        public Edge(int weight, Node from, Node to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }

    public static class Node {

        Object val;
        //指向当前节点的边的集合
        List<Edge> fromEdges;

        //从该点出发的边的集合
        List<Edge> toEdges;

        //从该节点出发的直接能到达邻居节点（不包含进来的边）
        List<Node> neighbors;

        public Node(Object val) {
            this.val = val;
            fromEdges = new ArrayList<>();
            toEdges = new ArrayList<>();
            neighbors = new ArrayList<>();
        }
    }
}
