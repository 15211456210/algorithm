package com.zcp.part4.day19;

import java.util.HashMap;

/**
 * @author ZCP
 * @title: LRUCache
 * @projectName algorithm
 * @description: TODO
 * @date 2022/2/27 21:43
 */
public class LRUCache {

    public class Node{
        int key;
        int value;
        Node pre;
        Node next;

        public Node(int key,int value){
            this.key = key;
            this.value = value;
        }
    }

    Node head;
    Node tail;

    HashMap<Integer,Node> map = new HashMap<>();

    int capacity;
    int size;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }else{
            Node node = map.get(key);
            updateNode(node);
            return node.value;
        }
    }

    public void put(int key, int value) {
        if(!map.containsKey(key)){
            Node node = new Node(key,value);
            map.put(key,node);
            if(size == 0){
                head = node;
                tail = node;
                size++;
                return;
            }

            tail.next = node;
            node.pre = tail;
            tail=node;

            if(size==capacity){
                map.remove(head.key);
                Node newHead = head.next;
                head.next = null;
                newHead.pre = null;
                head = newHead;
            }else{
                size++;
            }
        }else{
            Node node = map.get(key);
            node.value = value;
            updateNode(node);
        }

    }

    public void updateNode(Node node){
        if(node == tail || size == 1){
            return;
        }
        if(node == head){
            node.next.pre = null;
            head = node.next;
        }else{
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        node.next = null;
        node.pre = tail;
        tail.next = node;
        tail = node;
        return;
    }


    /**
     * ["LRUCache","put","put","get","put","get","put","get","get","get"]
     * [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
     * @param args
     */
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        System.out.println(lruCache.get(1));
        lruCache.put(3,3);
        System.out.println(lruCache.get(2));
        lruCache.put(4,4);
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));


    }
}
