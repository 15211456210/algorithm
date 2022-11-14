package com.zcp.part5.c201to250;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZCP
 * @title: C216
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/combination-sum-iii/submissions/
 * @date 2022/9/17 17:27
 */
public class C216 {

    static List<Integer> temp;
    static List<List<Integer>> list;

    public List<List<Integer>> combinationSum3(int k, int n) {
        list = new ArrayList<>();
        temp = new ArrayList<>();
        for (int i = 1; i < (1 << 9); i++) {
            if (checkNum(k, n, i)) {
                list.add(new ArrayList<>(temp));
            }
        }
        return list;
    }

    private boolean checkNum(int k, int n, int mask) {
        temp.clear();
        for (int i = 0; i < 9; i++) {
            if (((1 << i) & mask) == (1 << i)) {
                temp.add(i + 1);
            }
        }
        if (temp.size() != k) {
            return false;
        }
        int sum = temp.stream().mapToInt(item -> item).sum();
        if (sum != n) {
            return false;
        }
        return true;
    }
}
