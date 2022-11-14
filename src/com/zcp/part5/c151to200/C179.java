package com.zcp.part5.c151to200;

import java.util.Comparator;
import java.util.Stack;

/**
 * @author ZCP
 * @title: C179
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/largest-number/submissions/
 * @date 2022/9/10 12:34
 */
public class C179 {

    static class MyComparator<Integer> implements Comparator<Integer> {
        public int compare(Integer o1, Integer o2) {
            String s1 = o1.toString();
            String s2 = o2.toString();
            return (int) (Long.parseLong(s2 + s1) - Long.parseLong(s1 + s2));
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // arr[L...R] 玩荷兰国旗问题的划分，以arr[R]做划分值
    //  <arr[R]  ==arr[R]  > arr[R]
    // 返回等于区域左边界与右边界
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        MyComparator<Integer> myComp = new MyComparator<Integer>();
        int less = L - 1; // < 区 右边界
        int more = R;     // > 区 左边界
        int index = L;
        while (index < more) {
            if (myComp.compare(arr[index], arr[R]) == 0) {
                index++;
            } else if (myComp.compare(arr[index], arr[R]) < 0) {
                swap(arr, index++, ++less);
            } else { // >
                swap(arr, index, --more);
            }
        }
        // L...less   less+1...more-1    more...R-1        R
        // L...less   less+1.............more  more+1...   R
        swap(arr, more, R);
        return new int[]{less + 1, more};
    }

    // 快排非递归版本需要的辅助类
    // 要处理的是什么范围上的排序
    public static class Op {
        public int l;
        public int r;

        public Op(int left, int right) {
            l = left;
            r = right;
        }
    }

    // 快排3.0 非递归版本
    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        swap(arr, (int) (Math.random() * N), N - 1);
        int[] equalArea = netherlandsFlag(arr, 0, N - 1);
        int el = equalArea[0];
        int er = equalArea[1];
        Stack<Op> stack = new Stack<>();
        stack.push(new Op(0, el - 1));
        stack.push(new Op(er + 1, N - 1));
        while (!stack.isEmpty()) {
            Op op = stack.pop(); // op.l  ... op.r
            if (op.l < op.r) {
                swap(arr, op.l + (int) (Math.random() * (op.r - op.l + 1)), op.r);
                equalArea = netherlandsFlag(arr, op.l, op.r);
                el = equalArea[0];
                er = equalArea[1];
                stack.push(new Op(op.l, el - 1));
                stack.push(new Op(er + 1, op.r));
            }
        }
    }


    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        quickSort2(nums);
        String ans = "";
        for (int i = 0; i < nums.length; i++) {
            ans += nums[i];
        }
        int idx = 0;
        while (idx < ans.length() - 1 && ans.charAt(idx) == '0') {
            idx++;
        }
        return ans.substring(idx);
    }
}
