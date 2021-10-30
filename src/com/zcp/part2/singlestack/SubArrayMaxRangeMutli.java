package com.zcp.part2.singlestack;

import java.util.Stack;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/30
 * @description：[[正数数组中子数组累加和乘最小值的最大值]] 例子：
 * 输入：[2,4,5]
 * 输出：[4,5] 4*9 = 32
 * @version:
 */
public class SubArrayMaxRangeMutli {

    /**
     * 对照函数
     *
     * @param array
     * @return
     */
    public static int checkFun(int[] array) {
        if (array == null || array.length < 1) {
            return -1;
        }
        int maxAns = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                int min = array[i];
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    min = Math.min(min, array[k]);
                    sum += array[k];
                }
                maxAns = Math.max(maxAns, sum * min);
            }
        }
        return maxAns;
    }

    /**
     * 使用单调栈结构实现
     *
     * @param array
     * @return
     */
    public static int solution(int[] array) {
        if (array == null || array.length < 1) {
            return -1;
        }
        //前缀和数组
        int[] preSum = new int[array.length];
        preSum[0] = array[0];
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + array[i];
        }
        Stack<Integer> stack = new Stack<>();
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            while (!stack.isEmpty() && array[stack.peek()] >= array[i]) {
                Integer pop = stack.pop();
                if (array[pop] > array[i]) {
                    //计算结果
                    int leftIndex = stack.isEmpty() ? -1 : stack.peek();
                    int sum = leftIndex == -1 ? preSum[i - 1] : preSum[i - 1] - preSum[leftIndex];
                    ans = Math.max(ans, sum * array[pop]);
                }
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            int leftIndex = stack.isEmpty() ? -1 : stack.peek();
            int sum = preSum[array.length - 1] - (leftIndex == -1 ? 0 : preSum[leftIndex]);
            ans = Math.max(ans, sum * array[pop]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int size = 100;
        int range = 100;
        int testTime = 1000000;
        System.out.println("开始测试");
        for (int i = 0; i < testTime; i++) {
            int[] array = generateArray(size, range);
            int ans1 = checkFun(array);
            int ans2 = solution(array);
            if (ans1 != ans2) {
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }

    private static int[] generateArray(int length, int range) {
        int len = (int) (Math.random() * length);
        int[] arr = new int[len];

        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * range) + 1;
        }
        return arr;
    }
}
