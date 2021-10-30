package com.zcp.part2.uniun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/13 13:58
 * @description：哈希表实现的 并查集
 * @version:
 */
public class MapUniunSet<V> {

    private HashMap<V, Node<V>> nodes;

    private HashMap<Node<V>, Node<V>> parents;

    private HashMap<Node<V>, Integer> size;

    public static class Node<V> {
        V val;

        public Node(V val) {
            this.val = val;
        }
    }

    /**
     * 初始化
     *
     * @param list
     */
    public MapUniunSet(List<V> list) {
        nodes = new HashMap<>();
        parents = new HashMap<>();
        size = new HashMap<>();
        list.stream().forEach(item -> {
            Node<V> node = new Node<>(item);
            nodes.put(item, node);
            parents.put(node, node);
            size.put(node, 1);
        });
    }

    /**
     * 集合的个数
     *
     * @return
     */
    public int sets() {
        return size.size();
    }

    /**
     * 合并两个集合
     *
     * @param a
     * @param b
     */
    public void uniun(V a, V b) {
        Node<V> aHead = findHead(nodes.get(a));
        Node<V> bHead = findHead(nodes.get(b));
        if (aHead == bHead) {
            //已经是一个集合了，直接返回
            return;
        }
        Integer aSize = size.get(aHead);
        Integer bSize = size.get(bHead);
        if (aSize > bSize) {
            //将b所在集合 合并到 a所在集合
            parents.put(bHead, aHead);
            size.put(aHead, aSize + bSize);
            size.remove(bHead);
        } else {
            //否则 将a所在集合 合并到 b所在集合
            parents.put(aHead, bHead);
            size.put(bHead, aSize + bSize);
            size.remove(aHead);
        }
    }

    public boolean isSameSet(V a, V b) {
        return findHead(nodes.get(a)) == findHead(nodes.get(b));
    }

    public Node<V> findHead(Node<V> node) {
        //优化点，每次将路径上的节点都直接连接到代表节点，（扁平化）
        ArrayList<Node> tempList = new ArrayList<>();
        while (parents.get(node) != node) {
            tempList.add(node);
            node = parents.get(node);
        }
        Node<V> finalNode = node;
        tempList.forEach(item -> {
            parents.put(item, finalNode);
        });
        return node;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
        MapUniunSet<String> uniunSet = new MapUniunSet<>(list);
        System.out.println(uniunSet.sets());//7
        uniunSet.uniun("a", "b");
        System.out.println(uniunSet.sets());//6
        uniunSet.uniun("e", "b");
        System.out.println(uniunSet.sets());//5
        System.out.println(uniunSet.isSameSet("a", "e"));//true
        System.out.println(uniunSet.isSameSet("a", "d"));//false
    }

}
