package com.zcp.part5.c051to100;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * @author ZCP
 * @title: c077
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/combinations/submissions/
 * @date 2022/8/30 10:02
 */
public class c077 {

    static int[] arr = new int[21];
    static HashSet<Integer> set = new HashSet<>();


    public List<List<Integer>> combine(int n, int k) {

        ArrayList<List<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> nums = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }
        ArrayList<Integer> select = new ArrayList<>();
        process(nums, k, ans, select);
        return ans;
    }

    public void process(ArrayList<Integer> nums, int k, ArrayList<List<Integer>> ans, ArrayList<Integer> select) {
        if (select.size() == k) {
            ans.add(new ArrayList<>(select));
            return;
        }
        int size = nums.size();
        for (int i = 0; i < size; i++) {
            Integer selected = nums.remove(0);
            select.add(selected);
            process(new ArrayList<>(nums), k, ans, select);
            select.remove(select.size() - 1);
        }
    }


//    public List<List<Integer>> combine(int n, int k) {
//        for (int i = 1; i < arr.length; i++) {
//            arr[i] = i;
//        }
//        ArrayList<List<Integer>> ans = new ArrayList<>();
//        set.clear();
//        addSet(n, k);
//
//        process(1, n, k);
//
//        Iterator<Integer> iterator = set.iterator();
//        int m;
//        while (iterator.hasNext()) {
//            ArrayList<Integer> list = new ArrayList<>();
//            m = iterator.next();
//            for (int i = 0; i <= 20; i++) {
//                if (((m >> i) & 1) != 0) {
//                    list.add(i);
//                }
//            }
//            if (list.size() == k) {
//                ans.add(list);
//            }
//
//
//        }
//        return ans;
//
//
//    }

//    /**
//     * 数组全排列
//     *
//     * @param i
//     * @param n
//     * @param k
//     */
//    public void process(int i, int n, int k) {
//        if (i <= n - k) {
//            for (int m = i + 1; m < n - k; m++) {
//                swap(i, m);
//                process(i + 1, n, k);
//                swap(i, m);
//            }
//        } else {
//            addSet(n, k);
//        }
//
//    }
//
//
//    public void addSet(int n, int k) {
//        for (int i = 1; i <= n - k + 1; i++) {
//            int mask = 0;
//            for (int m = 0; m < k; m++) {
//                mask |= (1 << arr[i + m]);
//            }
//            set.add(mask);
//        }
//    }
//
//    public void swap(int i, int j) {
//        int tmp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = tmp;
//    }

    public static void main(String[] args) {
        List<List<Integer>> combine = new c077().combine(4, 2);
    }


}
