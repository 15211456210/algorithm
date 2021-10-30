package com.zcp.part2.singlestack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/30
 * @description：单调栈 给出一个数组，返回每个位置的数左边、右边离他最近的小于他的数的下标
 * 例子：
 * 输入：[2，5，1，4]
 * 输出：[[-1,2],[0,2],[-1,-1],[2,-1]]
 * @version:
 */
public class SingleStack {

    /**
     * 对照函数
     *
     * @param array
     * @return
     */
    public static int[][] checkFun(int[] array) {
        if (array == null || array.length < 1) {
            return null;
        }
        int[][] ans = new int[array.length][2];

        for (int i = 0; i < array.length; i++) {
            ans[i][0] = -1;
            ans[i][1] = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (array[j] < array[i]) {
                    ans[i][0] = j;
                    break;
                }
            }
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[i]) {
                    ans[i][1] = j;
                    break;
                }
            }
        }
        return ans;
    }

    /**
     * 单调栈实现
     *
     * @param array
     * @return
     */
    public static int[][] solution(int[] array) {
        if (array == null || array.length < 1) {
            return null;
        }
        int[][] ans = new int[array.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < array.length; i++) {
            while (!stack.isEmpty() && array[stack.peek().get(0)] > array[i]) {
                List<Integer> pop = stack.pop();
                for (Integer popIndex : pop) {
                    //每次弹出设置值
                    ans[popIndex][1] = i;
                    //左边取栈顶链表最后面的值
                    List<Integer> l = null;
                    ans[popIndex][0] = stack.isEmpty() ? -1 : (l = stack.peek()).get(l.size() - 1);
                }
            }
            if (!stack.isEmpty() && array[stack.peek().get(0)] == array[i]) {
                //和栈顶元素对应数组值相等，添加到链表尾部
                stack.peek().add(i);
            } else {
                //如果大于栈顶元素,或则栈为空，创建新链表push
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        //最后处理站内剩下的元素
        while (!stack.isEmpty()) {
            List<Integer> pop = stack.pop();
            for (Integer popIndex : pop) {
                ans[popIndex][1] = -1;
                List<Integer> l = null;
                ans[popIndex][0] = stack.isEmpty() ? -1 : (l = stack.peek()).get(l.size() - 1);
            }
        }
        return ans;
    }

    private static int[] generateArray(int length, int range) {
        int len = (int) (Math.random() * length);
        int[] arr = new int[len];

        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * range) - (int) (Math.random() * range);
        }
        return arr;
    }

    public static boolean check(int[][] ans1, int[][] ans2) {
        if (ans1 == null && ans2 == null) {
            return true;
        }
        if (ans1.length != ans2.length) {
            return false;
        }
        for (int i = 0; i < ans1.length; i++) {
            if (ans1[i][0] != ans2[i][0] || ans1[i][1] != ans2[i][1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int range = 100;
        int size = 10;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] array = generateArray(size, range);
            int[][] ans = checkFun(array);
            int[][] ans1 = solution(array);
            if (!check(ans1, ans)) {
                System.out.println("出错了");
                System.out.println(Arrays.toString(array));
                printArray(ans);
                System.out.println();
                printArray(ans1);
                break;
            }
        }
        System.out.println("测试结束");
    }

    public static void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(Arrays.toString(array[i]));
        }
    }

}
