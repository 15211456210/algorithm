package com.zcp.part2.uniun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/14 10:40
 * @description：朋友圈问题 https://leetcode-cn.com/problems/bLyHh0/
 * @version:
 */
public class FindCircleNum {

    public int solution(int[][] isConnected) {
        if (isConnected == null || isConnected.length < 1) {
            return 0;
        }
        ArrayUniunSet uniunSet = new ArrayUniunSet(isConnected);
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = i + 1; j < isConnected.length; j++) {
                if (isConnected[i][j] == 1){
                    uniunSet.uniun(i,j);
                }
            }
        }
        return uniunSet.sets();
    }

    public static class ArrayUniunSet {

        private int[] parents;

        private int[] sizes;

        public ArrayUniunSet(int[][] isConnected) {
            int size = isConnected.length;
            parents = new int[size];
            sizes = new int[size];
            for (int i = 0; i < parents.length; i++) {
                parents[i] = i;
            }
            Arrays.fill(sizes, 1);
        }

        public void uniun(int a, int b) {
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

        public int findHead(int index) {
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
    }


}
