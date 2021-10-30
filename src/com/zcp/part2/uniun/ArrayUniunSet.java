package com.zcp.part2.uniun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/14 10:10
 * @description：数组实现的 并查集 （常数操作更优秀）
 * @version:
 */
public class ArrayUniunSet<V> {

    private HashMap<V, Integer> nodes;

    private int[] parents;

    private int[] sizes;

    public ArrayUniunSet(List<V> list) {
        int size = list.size();
        nodes = new HashMap<>();
        parents = new int[size];
        sizes = new int[size];
        for (int i = 0; i < list.size(); i++) {
            nodes.put(list.get(i), i);
            parents[i] = i;
            sizes[i] = 1;
        }
    }

    public void uniun(V a, V b) {
        int aHead = findHead(a);
        int bHead = findHead(b);
        if (aHead == bHead) {
            return;
        }
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

    public int sets(){
        int sets = 0;
        for (int i = 0; i < sizes.length; i++) {
            if (sizes[i]>0){
                sets++;
            }
        }
        return sets;
    }

    public boolean isSameSet(V a, V b) {
        return findHead(a) == findHead(b);
    }

    public int findHead(V a) {
        Integer index = nodes.get(a);
        ArrayList<Integer> tempList = new ArrayList<>();
        while (parents[index] != index) {
            tempList.add(index);
            index = parents[index];
        }
        Integer finalIndex = index;
        tempList.forEach(item -> {
            parents[item] = finalIndex;
        });
        return index;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
        ArrayUniunSet<String> uniunSet = new ArrayUniunSet<>(list);
        System.out.println(uniunSet.sets());//7
        uniunSet.uniun("a", "b");
        System.out.println(uniunSet.sets());//6
        uniunSet.uniun("e", "b");
        System.out.println(uniunSet.sets());//5
        System.out.println(uniunSet.isSameSet("a", "e"));//true
        System.out.println(uniunSet.isSameSet("a", "d"));//false
    }

}
