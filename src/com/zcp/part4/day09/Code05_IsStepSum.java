package com.zcp.part4.day09;


import java.util.HashMap;

public class Code05_IsStepSum {


    /**
     * 思路：
     * 二分查找
     *
     * @param stepSum
     * @return
     */
    public static boolean isStepSum2(int stepSum) {
        int left = stepSum / 2;
        int right = stepSum;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int curStepSum = calc(mid);
            if (curStepSum < stepSum) {
                left = mid + 1;
            } else if (curStepSum > stepSum) {
                right = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 计算 stepSum的值返回
     *
     * @param num
     * @return
     */
    private static int calc(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num;
            num /= 10;
        }
        return sum;
    }

    public static boolean isStepSum(int stepSum) {
        int L = 0;
        int R = stepSum;
        int M = 0;
        int cur = 0;
        while (L <= R) {
            M = L + ((R - L) >> 1);
            cur = stepSum(M);
            if (cur == stepSum) {
                return true;
            } else if (cur < stepSum) {
                L = M + 1;
            } else {
                R = M - 1;
            }
        }
        return false;
    }

    public static int stepSum(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num;
            num /= 10;
        }
        return sum;
    }

    // for test
    public static HashMap<Integer, Integer> generateStepSumNumberMap(int numMax) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= numMax; i++) {
            map.put(stepSum(i), i);
        }
        return map;
    }

    // for test
    public static void main(String[] args) {
        int max = 1000000;
        int maxStepSum = stepSum(max);
        HashMap<Integer, Integer> ans = generateStepSumNumberMap(max);
        System.out.println("测试开始");
        for (int i = 0; i <= maxStepSum; i++) {
            if (isStepSum(i) ^ ans.containsKey(i) || isStepSum2(i) ^ ans.containsKey(i)) {
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");
    }

}
