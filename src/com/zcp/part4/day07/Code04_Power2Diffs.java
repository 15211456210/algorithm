package com.zcp.part4.day07;

import org.omg.CORBA.ARG_IN;

import java.io.LineNumberReader;
import java.util.Arrays;
import java.util.HashSet;

public class Code04_Power2Diffs {


    /**
     * 思路：
     * 双指针
     *
     * @param arr
     * @return
     */
    public static int diff3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int ans = 0;
        int N = arr.length;
        int left = 0;
        int right = N - 1;
        while (left <= right) {
            if (Math.abs(arr[left]) == Math.abs(arr[right])) {
                left = move(arr, left, true);
                right = move(arr, right, false);
            } else if (Math.abs(arr[left]) > Math.abs(arr[right])) {
                left = move(arr, left, true);
            } else {
                right = move(arr, right, false);
            }
            ans++;
        }
        return ans;
    }

    /**
     * arr[index]往direction方向移动，之道不相等的第一个数停，返回该数下标
     *
     * @param arr
     * @param index
     * @param direction true 往右  false 往左
     * @return
     */
    private static int move(int[] arr, int index, boolean direction) {
        if (direction) {
            int ans = index + 1;
            for (; ans < arr.length && arr[ans] == arr[index]; ans++) {

            }
            return ans;
        } else {
            int ans = index - 1;
            for (; ans >= 0 && arr[ans] == arr[index]; ans--) {

            }
            return ans;
        }
    }

    /*
     * 给定一个有序数组arr，其中值可能为正、负、0。 返回arr中每个数都平方之后不同的结果有多少种？
     *
     * 给定一个数组arr，先递减然后递增，返回arr中有多少个绝对值不同的数字？
     *
     */

    // 时间复杂度O(N)，额外空间复杂度O(N)
    public static int diff1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int cur : arr) {
            set.add(cur * cur);
        }
        return set.size();
    }

    // 时间复杂度O(N)，额外空间复杂度O(1)
    public static int diff2(int[] arr) {
        int N = arr.length;
        int L = 0;
        int R = N - 1;
        int count = 0;
        int leftAbs = 0;
        int rightAbs = 0;
        while (L <= R) {
            count++;
            leftAbs = Math.abs(arr[L]);
            rightAbs = Math.abs(arr[R]);
            if (leftAbs < rightAbs) {
                while (R >= 0 && Math.abs(arr[R]) == rightAbs) {
                    R--;
                }
            } else if (leftAbs > rightAbs) {
                while (L < N && Math.abs(arr[L]) == leftAbs) {
                    L++;
                }
            } else {
                while (L < N && Math.abs(arr[L]) == leftAbs) {
                    L++;
                }
                while (R >= 0 && Math.abs(arr[R]) == rightAbs) {
                    R--;
                }
            }
        }
        return count;
    }

    // for test
    public static int[] randomSortedArray(int len, int value) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * value) - (int) (Math.random() * value);
        }
        Arrays.sort(ans);
        return ans;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int cur : arr) {
            System.out.print(cur + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 100;
        int value = 500;
        int testTimes = 200000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = randomSortedArray(len, value);
            int ans1 = diff1(arr);
            int ans2 = diff2(arr);
            int ans3 = diff3(arr);
            if (ans1 != ans2 || ans2 != ans3) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("test finish");
    }

}
