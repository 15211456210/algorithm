package com.zcp.part2.structure;

import java.util.TreeMap;

/**
 * 有序表
 */
public class TreeMapD {

    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();

        treeMap.put(5,"5");
        treeMap.put(6,"6");
        treeMap.put(3,"3");
        treeMap.put(1,"51");
        treeMap.put(8,"8");
        treeMap.put(4,"4");
        System.out.println(treeMap.firstKey());//1
        System.out.println(treeMap.lastKey());//8
        System.out.println(treeMap.floorKey(7));//<=7 6
        System.out.println(treeMap.ceilingKey(7));//>=7 6

    }
}
