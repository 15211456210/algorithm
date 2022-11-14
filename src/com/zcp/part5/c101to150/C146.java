package com.zcp.part5.c101to150;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ZCP
 * @title: C146
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/lru-cache/submissions/
 * @date 2022/9/7 10:24
 */
public class C146 {

//    class Cache<K,V> extends LinkedHashMap<K,V> {
//        private int capacity;
//
//        public Cache(int capacity){
//            super(16,0.75f,true);
//            this.capacity = capacity;
//        }
//
//        @Override
//        public boolean removeEldestEntry(Map.Entry<K,V> eldest){
//            return super.size() > capacity;
//        }
//    }
//
//    private Cache<Integer,Integer> cache;
//
//    public LRUCache(int capacity) {
//        this.cache = new Cache<Integer,Integer>(capacity);
//    }
//
//    public int get(int key) {
//        return cache.get(key) == null ? -1 : cache.get(key);
//    }
//
//    public void put(int key, int value) {
//        cache.put(key,value);
//    }
}
