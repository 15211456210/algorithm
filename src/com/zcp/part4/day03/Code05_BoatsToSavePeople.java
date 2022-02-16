package com.zcp.part4.day03;

import java.util.Arrays;

// 给定一个正数数组arr，代表若干人的体重
// 再给定一个正数limit，表示所有船共同拥有的载重量
// 每艘船最多坐两人，且不能超过载重
// 想让所有的人同时过河，并且用最好的分配方法让船尽量少
// 返回最少的船数
// 测试链接 : https://leetcode.com/problems/boats-to-save-people/
public class Code05_BoatsToSavePeople {


    /**
     * 贪心
     *
     * @param people
     * @param limit
     * @return
     */
    public int numRescueBoats2(int[] people, int limit) {
        if (people == null || people.length < 1 || limit < 0) {
            return 0;
        }
        //先对人重量进行排序
        Arrays.sort(people);
        int N = people.length;
        //首位指针 同时向对方靠近，这样是最省的
        int L = 0;
        int R = N - 1;
        int pass = 0;//通过的人数
        while (L < R) {
            if (people[L] + people[R] <= limit) {
                pass += 2;
                L++;
                R--;
            } else {
                R--;
            }
        }
        return (pass >> 1) + (N - pass);
    }


    public static int numRescueBoats(int[] arr, int limit) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        Arrays.sort(arr);
        if (arr[N - 1] > limit) {
            return -1;
        }
        int lessR = -1;
        for (int i = N - 1; i >= 0; i--) {
            if (arr[i] <= (limit / 2)) {
                lessR = i;
                break;
            }
        }
        if (lessR == -1) {
            return N;
        }
        int L = lessR;
        int R = lessR + 1;
        int noUsed = 0;
        while (L >= 0) {
            int solved = 0;
            while (R < N && arr[L] + arr[R] <= limit) {
                R++;
                solved++;
            }
            if (solved == 0) {
                noUsed++;
                L--;
            } else {
                L = Math.max(-1, L - solved);
            }
        }
        int all = lessR + 1;
        int used = all - noUsed;
        int moreUnsolved = (N - all) - used;
        return used + ((noUsed + 1) >> 1) + moreUnsolved;
    }

}
