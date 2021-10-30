package com.zcp.part2.structure;

/**
 * 递归方法找到数组中最大的值
 */
public class FindMaxRecursion {

    /**
     * 返回arr[L...R]中最大的数
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int solution(int[] arr, int L, int R) {
        if (arr == null || arr.length < 1) {
            return Integer.MIN_VALUE;
        }
        if (L == R) {
            return arr[R];
        }
        int mid = (L + R)/2;
        int leftMax = solution(arr, L, mid);
        int rightMax = solution(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);
    }

    /**
     * 对照函数（通解）
     *
     * @param arr
     * @return
     */
    public static int solution2(int[] arr) {
        if (arr == null ) {
            return Integer.MIN_VALUE;
        }
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = Math.max(max, i);
        }
        return max;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int length = 500;
        int range = 500;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] array = generateArray(length, range);
            if(solution(array,0,array.length-1) != solution2(array)){
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
            arr[i] = (int) (Math.random() * range) - (int) (Math.random() * range);
        }
        return arr;
    }

    private static int[] copyArray(int[] arr){
        int len = arr.length;
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = ans[i];
        }
        return ans;
    }

}
