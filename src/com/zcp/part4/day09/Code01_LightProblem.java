package com.zcp.part4.day09;

import java.util.Arrays;

/*
 * 给定一个数组arr，长度为N，arr中的值不是0就是1
 * arr[i]表示第i栈灯的状态，0代表灭灯，1代表亮灯
 * 每一栈灯都有开关，但是按下i号灯的开关，会同时改变i-1、i、i+2栈灯的状态
 * 问题一：
 * 如果N栈灯排成一条直线,请问最少按下多少次开关,能让灯都亮起来
 * 排成一条直线说明：
 * i为中间位置时，i号灯的开关能影响i-1、i和i+1
 * 0号灯的开关只能影响0和1位置的灯
 * N-1号灯的开关只能影响N-2和N-1位置的灯
 *
 * 问题二：
 * 如果N栈灯排成一个圈,请问最少按下多少次开关,能让灯都亮起来
 * 排成一个圈说明：
 * i为中间位置时，i号灯的开关能影响i-1、i和i+1
 * 0号灯的开关能影响N-1、0和1位置的灯
 * N-1号灯的开关能影响N-2、N-1和0位置的灯
 *
 * */
public class Code01_LightProblem {


    /**
     * 有环版本优化
     * 迭代版本
     *
     * @param arr
     * @return
     */
    public static int loopTraceRight2(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] == 1 ? 0 : 1;
        }
        if (arr.length == 2) {
            //只有2个位置数字想同才有可能全部点亮
            return ((arr[0] ^ arr[1]) == 0) ? (arr[0] == 1 ? 0 : 1) : Integer.MAX_VALUE;
        }
        if (arr.length == 3) {
            //只有3个位置数字想同才有可能全部点亮
            if (arr[0] == arr[1] && arr[1] == arr[2]) {
                return arr[0] == 1 ? 0 : 1;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        //p1 0:点灯，1点灯
        int p1 = process7(arr, 2, arr[1], arr[2] ^ 1, arr[0], arr[arr.length - 1] ^ 1);
        p1 = (p1 == Integer.MAX_VALUE ? Integer.MAX_VALUE : (p1 + 2));
        //p2 0:不点灯，1点灯
        int p2 = process7(arr, 2, arr[1] ^ 1, arr[2] ^ 1, arr[0] ^ 1, arr[arr.length - 1]);
        p2 = (p2 == Integer.MAX_VALUE ? Integer.MAX_VALUE : (p2 + 1));
        //p3 0:点灯，1不点灯
        int p3 = process7(arr, 2, arr[1] ^ 1, arr[2], arr[0] ^ 1, arr[arr.length - 1] ^ 1);
        p3 = (p3 == Integer.MAX_VALUE ? Integer.MAX_VALUE : (p3 + 1));
        //p4 0:不点灯，1不点灯
        int p4 = process7(arr, 2, arr[1], arr[2], arr[0], arr[arr.length - 1]);

        return Math.min(Math.min(p1, p2), Math.min(p3, p4));
    }

    /**
     * 返回从beginIndex开始 所有位置亮灯最小开关次数
     * 如果无论如何都无法实现灯全亮，返回int最大值
     *
     * @param arr
     * @param beginIndex 开始下标
     * @param pre        前面一个位置的亮灯状态
     * @param cur        当前位置的亮灯状态
     * @param first      第一个元素的状态
     * @param last       最后一个元素的状态
     * @return
     */
    private static int process7(int[] arr, int beginIndex, int pre, int cur, int first, int last) {
        int ans = 0;
        int len = arr.length;
        int cIndex = beginIndex;
        //先把next值设置好
        int next = arr[beginIndex + 1];
        if (beginIndex + 2 == len) {
            //说明next是最后一个元素
            next = last;
        }
        for (; cIndex < len - 1; cIndex++) {
            if (pre == 0) {
                ans++;
                pre = cur ^ 1;//设置下一步的pre
                cur = next ^ 1;//设置下一步的cur
            } else {
                pre = cur;
                cur = next;
            }
            //设置下一步的next
            if (cIndex == len - 3) {
                next = last;
            } else if (cIndex < len - 2) {
                next = arr[cIndex + 2];
            }
        }
        // cIndex==len-1
        if (pre == cur && cur == first) {
            return ans + (cur == 1 ? 0 : 1);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    /**
     * 无环非递归优化版本
     *
     * @param arr
     * @return
     */
    public static int noLoopTraceRight2(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] == 1 ? 0 : 1;
        }
        if (arr.length == 2) {
            //只有连个位置数字想同才有可能全部点亮
            return ((arr[0] ^ arr[1]) == 0) ? (arr[0] == 1 ? 0 : 1) : Integer.MAX_VALUE;
        }

        // p1 不按下第0个按钮
        int p1 = process6(arr, 1, arr[0], arr[1], arr[2]);
        // p2 按下第0个按钮
        int p2 = process6(arr, 1, arr[0] ^ 1, arr[1] ^ 1, arr[2]);
        p2 = (p2 == Integer.MAX_VALUE) ? Integer.MAX_VALUE : (p2 + 1);
        return Math.min(p1, p2);
    }

    /**
     * 迭代优化版本
     * 返回从开始位置，把所有灯都开起来需要最小的开关次数
     * 如果无法全部点亮，返回最大值
     *
     * @param arr
     * @param begin 开始
     * @param pre   上一个位置的开灯状态
     * @param cur   当前位置的开灯状态
     * @param next  下一个位置的开灯状态
     */
    private static int process6(int[] arr, int begin, int pre, int cur, int next) {
        int ans = 0;
        int len = arr.length;
        int cIndex = begin;
        for (; cIndex < len - 1; cIndex++) {
            if (pre == 0) {
                ans++;
                pre = cur ^ 1;//设置下一步的pre
                cur = next ^ 1;//设置下一步的cur
            } else {
                pre = cur;
                cur = next;
            }
            //设置下一步的next
            if (cIndex < len - 2) {
                next = arr[cIndex + 2];
            }
        }
        // cIndex==len-1
        return pre == cur ? ans + (cur == 1 ? 0 : 1) : Integer.MAX_VALUE;
    }

    /**
     * 思路：
     * 动态规划
     *
     * @param arr
     * @return
     */
    public static int loopRight2(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] == 1 ? 0 : 1;
        }
        if (arr.length == 2) {
            //只有连个位置数字想同才有可能全部点亮
            return ((arr[0] ^ arr[1]) == 0) ? (arr[0] == 1 ? 0 : 1) : Integer.MAX_VALUE;
        }

        int[] p1 = new int[arr.length];
        int[] p2 = new int[arr.length];
        int[] p3 = new int[arr.length];
        int[] p4 = new int[arr.length];
        System.arraycopy(arr, 0, p1, 0, arr.length);
        System.arraycopy(arr, 0, p2, 0, arr.length);
        System.arraycopy(arr, 0, p3, 0, arr.length);
        System.arraycopy(arr, 0, p4, 0, arr.length);
        //p1 第1，2盏灯点上,最终会影响2个位置状态
        p1[2] ^= 1;
        p1[p1.length - 1] ^= 1;
        int ans1 = process5(p1, 2);
        ans1 = (ans1 == Integer.MAX_VALUE) ? Integer.MAX_VALUE : ans1 + 2;

        //p2 第1盏灯点上,第2盏灯不点，影响3个位置
        p2[0] ^= 1;
        p2[1] ^= 1;
        p2[p2.length - 1] ^= 1;
        int ans2 = process5(p2, 2);
        ans2 = (ans2 == Integer.MAX_VALUE) ? Integer.MAX_VALUE : ans2 + 1;

        //p3 第1盏灯不点,第2盏灯点上，影响3个位置
        p3[0] ^= 1;
        p3[1] ^= 1;
        p3[2] ^= 1;
        int ans3 = process5(p3, 2);
        ans3 = (ans3 == Integer.MAX_VALUE) ? Integer.MAX_VALUE : ans3 + 1;

        //p4 第1，2盏灯都不点,最终会影响0个位置状态
        int ans4 = process5(p4, 2);
        //返回4中情况中最小值
        return Math.min(Math.min(ans1, ans2), Math.min(ans3, ans4));
    }

    /**
     * 有环问题
     * 返回点亮全部灯对最小花费次数
     * 如果不能全部点亮返回int最大值
     *
     * @param arr
     * @param index 当前来到的位置
     * @return
     */
    private static int process5(int[] arr, int index) {
        if (index == arr.length - 1) {
            //当前来到最后一个位置
            //判断最后两位置和第一个位置的灯状态是否一致，如果不一致，说明怎么都点亮不了全部的灯
            if (arr[index - 1] == arr[index] && arr[index] == arr[0]) {
                return arr[0] == 1 ? 0 : 1;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        int pre = arr[index - 1];
        if (pre == 0) {
            //如果当前位置的上一个位置为0，那么当前位置必须点灯
            arr[index - 1] ^= 1;
            arr[index] ^= 1;
            arr[index + 1] ^= 1;
            int ans = process5(arr, index + 1);
            return (ans == Integer.MAX_VALUE) ? Integer.MAX_VALUE : ans + 1;
        } else {
            //如果当前位置的上一个位置为1，不能点灯
            return process5(arr, index + 1);
        }
    }


    /**
     * 思路：
     * 动态规划
     *
     * @param arr
     * @return
     */
    public static int noLoopRight2(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] == 1 ? 0 : 1;
        }
        if (arr.length == 2) {
            //只有连个位置数字想同才有可能全部点亮
            return ((arr[0] ^ arr[1]) == 0) ? (arr[0] == 1 ? 0 : 1) : Integer.MAX_VALUE;
        }
        int[] p1 = new int[arr.length];
        int[] p2 = new int[arr.length];
        System.arraycopy(arr, 0, p1, 0, arr.length);
        System.arraycopy(arr, 0, p2, 0, arr.length);
        //p1 第一盏灯点上
        p1[0] ^= 1;
        p1[1] ^= 1;
        int ans = process4(p1, 1);
        ans = (ans == Integer.MAX_VALUE) ? Integer.MAX_VALUE : ans + 1;
        //p2 第一盏灯不点
        ans = Math.min(ans, process4(p2, 1));
        return ans;
    }

    /**
     * 返回点亮全部灯对最小花费次数
     * 如果不能全部点亮返回int最大值
     *
     * @param arr
     * @param index 当前来到的位置
     * @return
     */
    public static int process4(int[] arr, int index) {
        if (index == arr.length - 1) {
            //最后一盏灯
            //最后两灯数字不同：表示不可能全部点亮了，返回Integer.MAX_VALUE
            return (arr[index - 1] == arr[index]) ? (arr[index] == 1 ? 0 : 1) : Integer.MAX_VALUE;
        }
        int pre = arr[index - 1];
        if (pre == 0) {
            //如果当前位置的上一个位置为0，那么当前位置必须点灯
            arr[index - 1] ^= 1;
            arr[index] ^= 1;
            arr[index + 1] ^= 1;
            int ans = process4(arr, index + 1);
            return (ans == Integer.MAX_VALUE) ? Integer.MAX_VALUE : ans + 1;
        } else {
            //如果当前位置的上一个位置为1，不能点灯
            return process4(arr, index + 1);
        }
    }

    // 无环改灯问题的暴力版本
    public static int noLoopRight(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] == 1 ? 0 : 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        return f1(arr, 0);
    }

    public static int f1(int[] arr, int i) {
        if (i == arr.length) {
            return valid(arr) ? 0 : Integer.MAX_VALUE;
        }
        int p1 = f1(arr, i + 1);
        change1(arr, i);
        int p2 = f1(arr, i + 1);
        change1(arr, i);
        p2 = (p2 == Integer.MAX_VALUE) ? p2 : (p2 + 1);
        return Math.min(p1, p2);
    }

    public static void change1(int[] arr, int i) {
        if (i == 0) {
            arr[0] ^= 1;
            arr[1] ^= 1;
        } else if (i == arr.length - 1) {
            arr[i - 1] ^= 1;
            arr[i] ^= 1;
        } else {
            arr[i - 1] ^= 1;
            arr[i] ^= 1;
            arr[i + 1] ^= 1;
        }
    }

    public static boolean valid(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                return false;
            }
        }
        return true;
    }

    // 无环改灯问题的递归版本
    public static int noLoopMinStep1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] ^ 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        // 不变0位置的状态
        int p1 = process1(arr, 2, arr[0], arr[1]);
        // 改变0位置的状态
        int p2 = process1(arr, 2, arr[0] ^ 1, arr[1] ^ 1);
        if (p2 != Integer.MAX_VALUE) {
            p2++;
        }
        return Math.min(p1, p2);
    }

    // 当前在哪个位置上，做选择，nextIndex - 1 = cur ，当前！
    // cur - 1 preStatus
    // cur  curStatus
    // 0....cur-2  全亮的！
    public static int process1(int[] arr, int nextIndex, int preStatus, int curStatus) {
        if (nextIndex == arr.length) { // 当前来到最后一个开关的位置
            return preStatus != curStatus ? (Integer.MAX_VALUE) : (curStatus ^ 1);
        }
        // 没到最后一个按钮呢！
        // i < arr.length
        if (preStatus == 0) { // 一定要改变
            curStatus ^= 1;
            int cur = arr[nextIndex] ^ 1;
            int next = process1(arr, nextIndex + 1, curStatus, cur);
            return next == Integer.MAX_VALUE ? next : (next + 1);
        } else { // 一定不能改变
            return process1(arr, nextIndex + 1, curStatus, arr[nextIndex]);
        }
    }

    // 无环改灯问题的迭代版本
    public static int noLoopMinStep2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] == 1 ? 0 : 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        int p1 = traceNoLoop(arr, arr[0], arr[1]);
        int p2 = traceNoLoop(arr, arr[0] ^ 1, arr[1] ^ 1);
        p2 = (p2 == Integer.MAX_VALUE) ? p2 : (p2 + 1);
        return Math.min(p1, p2);
    }

    public static int traceNoLoop(int[] arr, int preStatus, int curStatus) {
        int i = 2;
        int op = 0;
        while (i != arr.length) {
            if (preStatus == 0) {
                op++;
                preStatus = curStatus ^ 1;
                curStatus = arr[i++] ^ 1;
            } else {
                preStatus = curStatus;
                curStatus = arr[i++];
            }
        }
        return (preStatus != curStatus) ? Integer.MAX_VALUE : (op + (curStatus ^ 1));
    }

    // 有环改灯问题的暴力版本
    public static int loopRight(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] == 1 ? 0 : 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        return f2(arr, 0);
    }

    public static int f2(int[] arr, int i) {
        if (i == arr.length) {
            return valid(arr) ? 0 : Integer.MAX_VALUE;
        }
        int p1 = f2(arr, i + 1);
        change2(arr, i);
        int p2 = f2(arr, i + 1);
        change2(arr, i);
        p2 = (p2 == Integer.MAX_VALUE) ? p2 : (p2 + 1);
        return Math.min(p1, p2);
    }

    public static void change2(int[] arr, int i) {
        arr[lastIndex(i, arr.length)] ^= 1;
        arr[i] ^= 1;
        arr[nextIndex(i, arr.length)] ^= 1;
    }

    public static int lastIndex(int i, int N) {
        return i == 0 ? (N - 1) : (i - 1);
    }

    public static int nextIndex(int i, int N) {
        return i == N - 1 ? 0 : (i + 1);
    }

    // 有环改灯问题的递归版本
    public static int loopMinStep1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] == 1 ? 0 : 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        if (arr.length == 3) {
            return (arr[0] != arr[1] || arr[0] != arr[2]) ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        // 0不变，1不变
        int p1 = process2(arr, 3, arr[1], arr[2], arr[arr.length - 1], arr[0]);
        // 0改变，1不变
        int p2 = process2(arr, 3, arr[1] ^ 1, arr[2], arr[arr.length - 1] ^ 1, arr[0] ^ 1);
        // 0不变，1改变
        int p3 = process2(arr, 3, arr[1] ^ 1, arr[2] ^ 1, arr[arr.length - 1], arr[0] ^ 1);
        // 0改变，1改变
        int p4 = process2(arr, 3, arr[1], arr[2] ^ 1, arr[arr.length - 1] ^ 1, arr[0]);
        p2 = p2 != Integer.MAX_VALUE ? (p2 + 1) : p2;
        p3 = p3 != Integer.MAX_VALUE ? (p3 + 1) : p3;
        p4 = p4 != Integer.MAX_VALUE ? (p4 + 2) : p4;
        return Math.min(Math.min(p1, p2), Math.min(p3, p4));
    }


    // 下一个位置是，nextIndex
    // 当前位置是，nextIndex - 1 -> curIndex
    // 上一个位置是, nextIndex - 2 -> preIndex   preStatus
    // 当前位置是，nextIndex - 1, curStatus
    // endStatus, N-1位置的状态
    // firstStatus, 0位置的状态
    // 返回，让所有灯都亮，至少按下几次按钮

    // 当前来到的位置(nextIndex - 1)，一定不能是1！至少从2开始
    // nextIndex >= 3
    public static int process2(int[] arr,
                               int nextIndex, int preStatus, int curStatus,
                               int endStatus, int firstStatus) {

        if (nextIndex == arr.length) { // 最后一按钮！
            return (endStatus != firstStatus || endStatus != preStatus) ? Integer.MAX_VALUE : (endStatus ^ 1);
        }
        // 当前位置，nextIndex - 1
        // 当前的状态，叫curStatus
        // 如果不按下按钮，下一步的preStatus, curStatus
        // 如果按下按钮，下一步的preStatus, curStatus ^ 1
        // 如果不按下按钮，下一步的curStatus, arr[nextIndex]
        // 如果按下按钮，下一步的curStatus, arr[nextIndex] ^ 1
        int noNextPreStatus = 0;
        int yesNextPreStatus = 0;
        int noNextCurStatus = 0;
        int yesNextCurStatus = 0;
        int noEndStatus = 0;
        int yesEndStatus = 0;
        if (nextIndex < arr.length - 1) {// 当前没来到N-2位置
            noNextPreStatus = curStatus;
            yesNextPreStatus = curStatus ^ 1;
            noNextCurStatus = arr[nextIndex];
            yesNextCurStatus = arr[nextIndex] ^ 1;
        } else if (nextIndex == arr.length - 1) {// 当前来到的就是N-2位置
            noNextPreStatus = curStatus;
            yesNextPreStatus = curStatus ^ 1;
            noNextCurStatus = endStatus;
            yesNextCurStatus = endStatus ^ 1;
            noEndStatus = endStatus;
            yesEndStatus = endStatus ^ 1;
        }
        if (preStatus == 0) {
            int next = process2(arr, nextIndex + 1, yesNextPreStatus, yesNextCurStatus,
                    nextIndex == arr.length - 1 ? yesEndStatus : endStatus, firstStatus);
            return next == Integer.MAX_VALUE ? next : (next + 1);
        } else {
            return process2(arr, nextIndex + 1, noNextPreStatus, noNextCurStatus,
                    nextIndex == arr.length - 1 ? noEndStatus : endStatus, firstStatus);

        }
//		int curStay = (nextIndex == arr.length - 1) ? endStatus : arr[nextIndex];
//		int curChange = (nextIndex == arr.length - 1) ? (endStatus ^ 1) : (arr[nextIndex] ^ 1);
//		int endChange = (nextIndex == arr.length - 1) ? curChange : endStatus;
//		if (preStatus == 0) {
//			int next = process2(arr, nextIndex + 1, curStatus ^ 1, curChange, endChange, firstStatus);
//			return next == Integer.MAX_VALUE ? next : (next + 1);
//		} else {
//			return process2(arr, nextIndex + 1, curStatus, curStay, endStatus, firstStatus);
//		}
    }


    // 有环改灯问题的迭代版本
    public static int loopMinStep2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] == 1 ? 0 : 1;
        }
        if (arr.length == 2) {
            return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        if (arr.length == 3) {
            return (arr[0] != arr[1] || arr[0] != arr[2]) ? Integer.MAX_VALUE : (arr[0] ^ 1);
        }
        // 0不变，1不变
        int p1 = traceLoop(arr, arr[1], arr[2], arr[arr.length - 1], arr[0]);
        // 0改变，1不变
        int p2 = traceLoop(arr, arr[1] ^ 1, arr[2], arr[arr.length - 1] ^ 1, arr[0] ^ 1);
        // 0不变，1改变
        int p3 = traceLoop(arr, arr[1] ^ 1, arr[2] ^ 1, arr[arr.length - 1], arr[0] ^ 1);
        // 0改变，1改变
        int p4 = traceLoop(arr, arr[1], arr[2] ^ 1, arr[arr.length - 1] ^ 1, arr[0]);
        p2 = p2 != Integer.MAX_VALUE ? (p2 + 1) : p2;
        p3 = p3 != Integer.MAX_VALUE ? (p3 + 1) : p3;
        p4 = p4 != Integer.MAX_VALUE ? (p4 + 2) : p4;
        return Math.min(Math.min(p1, p2), Math.min(p3, p4));
    }

    public static int traceLoop(int[] arr, int preStatus, int curStatus, int endStatus, int firstStatus) {
        int i = 3;
        int op = 0;
        while (i < arr.length - 1) {
            if (preStatus == 0) {
                op++;
                preStatus = curStatus ^ 1;
                curStatus = (arr[i++] ^ 1);
            } else {
                preStatus = curStatus;
                curStatus = arr[i++];
            }
        }
        if (preStatus == 0) {
            op++;
            preStatus = curStatus ^ 1;
            endStatus ^= 1;
            curStatus = endStatus;
        } else {
            preStatus = curStatus;
            curStatus = endStatus;
        }
        return (endStatus != firstStatus || endStatus != preStatus) ? Integer.MAX_VALUE : (op + (endStatus ^ 1));
    }

    // 生成长度为len的随机数组，值只有0和1两种值
    public static int[] randomArray(int len) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 2);
        }
        return arr;
    }


//    public static void main(String[] args) {
//        System.out.println(loopRight2(new int[]{1, 1, 0, 0}));
//    }

    public static void main(String[] args) {
        System.out.println("如果没有任何Oops打印，说明所有方法都正确");
        System.out.println("test begin");
        int testTime = 20000;
        int lenMax = 15;
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * lenMax);
            int[] arr = randomArray(len);
            int ans1 = noLoopRight(arr);
            int ans2 = noLoopMinStep1(arr);
            int ans3 = noLoopMinStep2(arr);
            int ans5 = noLoopTraceRight2(arr);
            int[] arr2 = new int[arr.length];
            System.arraycopy(arr, 0, arr2, 0, arr.length);
            int ans4 = noLoopRight2(arr2);
            if (ans1 != ans2 || ans1 != ans3 || ans1 != ans4 || ans1 != ans5) {
                System.out.println("1 Oops!");
                System.out.println(Arrays.toString(arr));
                System.out.println("ans1:" + ans1 + "  ans5:" + ans5);
                break;
            }
        }
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * lenMax);
            int[] arr = randomArray(len);
            int ans1 = loopRight(arr);
            int ans2 = loopMinStep1(arr);
            int ans3 = loopMinStep2(arr);
            int ans4 = loopRight2(arr);
            int ans5 = loopTraceRight2(arr);
            if (ans1 != ans2 || ans1 != ans3 || ans1 != ans4 || ans1 != ans5) {
                System.out.println("2 Oops!");
                System.out.println(Arrays.toString(arr));
                System.out.println("ans1:" + ans1 + "  ans5:" + ans5);
                break;
            }
        }
        System.out.println("test end");

        int len = 100000000;
        System.out.println("性能测试");
        System.out.println("数组大小：" + len);
        int[] arr = randomArray(len);
        long start = 0;
        long end = 0;
        start = System.currentTimeMillis();
        noLoopMinStep2(arr);
        end = System.currentTimeMillis();
        System.out.println("noLoopMinStep2 run time: " + (end - start) + "(ms)");

        start = System.currentTimeMillis();
        noLoopTraceRight2(arr);
        end = System.currentTimeMillis();
        System.out.println("noLoopTraceRight2 run time: " + (end - start) + "(ms)");

        start = System.currentTimeMillis();
        loopMinStep2(arr);
        end = System.currentTimeMillis();
        System.out.println("loopMinStep2 run time: " + (end - start) + "(ms)");
        start = System.currentTimeMillis();
        loopTraceRight2(arr);
        end = System.currentTimeMillis();
        System.out.println("loopTraceRight2 run time: " + (end - start) + "(ms)");
    }

}
