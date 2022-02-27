package com.zcp.part4.day18;

/**
 * 给定一个数组arr，长度为N，arr中的值只有1，2，3三种
 * arr[i] == 1，代表汉诺塔问题中，从上往下第i个圆盘目前在左
 * arr[i] == 2，代表汉诺塔问题中，从上往下第i个圆盘目前在中
 * arr[i] == 3，代表汉诺塔问题中，从上往下第i个圆盘目前在右
 * 那么arr整体就代表汉诺塔游戏过程中的一个状况 如果这个状况不是汉诺塔最优解运动过程中的状况，返回-1 如果这个状况是汉诺塔最优解运动过程中的状况，返回它是第几个状况
 */
public class Code01_HanoiProblem {


    /**
     * 思路：
     * 将N层汉诺塔从1移到3  整体大思路是：
     * 将N-1层汉诺塔从1->2
     * 将第N层汉诺塔从1->3
     * 将N-1层汉诺塔从2->3
     * <p>
     * 在移动的期间，判断是否满足某一步移动后的汉诺塔状态正好与arr各个位置状态相等
     *
     * @param arr
     * @return
     */
    public static int step3(int[] arr) {
        if (arr == null) {
            return -1;
        }
        return process3(arr, arr.length - 1, 1, 3, 2);
    }

    /**
     * 返回当前位置是 第index+1层汉诺塔 从from移动到to位置,需要到arr状态的移动次数
     *
     * @param arr
     * @param index
     * @param from
     * @param to
     * @param other
     * @return
     */
    public static int process3(int[] arr, int index, int from, int to, int other) {
        if (index < 0) {
            return 0;
        }
        if (arr[index] == other) {
            //期间只要出现 index+1层 要从 from -> to 过程中，比不可能出现在other位置，出现了说明arr状态不是最优状态
            return -1;
        }

        if (arr[index] == from) {
            //说明arr的状态应该在 index+1层移动之前 ，即答案在 index层 from ->  other过程中
            return process3(arr, index - 1, from, other, to);
        } else {
            //arr[index] == to 说明arr的状态应该在 index层 other->to 范围中
            //先算上之前 index层汉诺塔移动的次数 2^n-1
            int step = (1 << index) - 1;
            //再算上 index  from->to位置的一次移动
            step += 1;
            int next = process3(arr, index - 1, other, to, from);
            if (next == -1) {
                //如果后续返回-1，说明arr不能是最优状态
                return -1;
            } else {
                //否则的话返回 step + next
                return step + next;
            }

        }

    }


    public static int step1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        return process(arr, arr.length - 1, 1, 2, 3);
    }

    // 目标是: 把0~i的圆盘，从from全部挪到to上
    // 返回，根据arr中的状态arr[0..i]，它是最优解的第几步？
    // f(i, 3 , 2, 1) f(i, 1, 3, 2) f(i, 3, 1, 2)
    public static int process(int[] arr, int i, int from, int other, int to) {
        if (i == -1) {
            return 0;
        }
        if (arr[i] != from && arr[i] != to) {
            return -1;
        }
        if (arr[i] == from) { // 第一大步没走完
            return process(arr, i - 1, from, to, other);
        } else { // arr[i] == to
            // 已经走完1，2两步了，
            int rest = process(arr, i - 1, other, from, to); // 第三大步完成的程度
            if (rest == -1) {
                return -1;
            }
            return (1 << i) + rest;
        }
    }

    public static int step2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int from = 1;
        int mid = 2;
        int to = 3;
        int i = arr.length - 1;
        int res = 0;
        int tmp = 0;
        while (i >= 0) {
            if (arr[i] != from && arr[i] != to) {
                return -1;
            }
            if (arr[i] == to) {
                res += 1 << i;
                tmp = from;
                from = mid;
            } else {
                tmp = to;
                to = mid;
            }
            mid = tmp;
            i--;
        }
        return res;
    }

    public static int kth(int[] arr) {
        int N = arr.length;
        return step(arr, N - 1, 1, 3, 2);
    }

    // 0...index这些圆盘，arr[0..index] index+1层塔
    // 在哪？from 去哪？to 另一个是啥？other
    // arr[0..index]这些状态，是index+1层汉诺塔问题的，最优解第几步
    public static int step(int[] arr, int index, int from, int to, int other) {
        if (index == -1) {
            return 0;
        }
        if (arr[index] == other) {
            return -1;
        }
        // arr[index] == from arr[index] == to;
        if (arr[index] == from) {
            return step(arr, index - 1, from, other, to);
        } else {
            int p1 = (1 << index) - 1;
            int p2 = 1;
            int p3 = step(arr, index - 1, other, to, from);
            if (p3 == -1) {
                return -1;
            }
            return p1 + p2 + p3;
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 3, 2, 1};
        System.out.println(step1(arr));
        System.out.println(step2(arr));
        System.out.println(kth(arr));
        System.out.println(step3(arr));
    }
}
