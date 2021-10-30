package com.zcp.part2.structure;

import java.util.HashMap;
import java.util.Objects;

public class HashMapD {

    public static class Node{
        int val;

        public Node(int val) {
            this.val = val;
        }

    }
    public static void main(String[] args) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        Integer a = 128;
        Integer b = 128;
        System.out.println(a == b);//false
        hashMap.put(a,"我是a");
        System.out.println(hashMap.containsKey(b));//true

        Node node = new Node(1);
        Node node2 = new Node(2);
        HashMap<Node, String> hashMap1 = new HashMap<>();
        hashMap1.put(node,"我是node");
        System.out.println(hashMap1.containsKey(node2));//false

        HashMap<String, String> hashMap2 = new HashMap<>();
        String s1= "a";
        String s2= "a";
        hashMap2.put(s1,"我是s1");
        System.out.println(hashMap2.containsKey(s2));//true
        // 结论：基础类型 Key值比较是按值比较
        // 其他自定义类型 按引用传递
        // String 类型按值传递
    }
}
