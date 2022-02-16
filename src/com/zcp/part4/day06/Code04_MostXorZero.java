package com.zcp.part4.day06;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 数组中所有数都异或起来的结果，叫做异或和 给定一个数组arr，可以任意切分成若干个不相交的子数组 其中一定存在一种最优方案，使得切出异或和为0的子数组最多 返回这个最多数量
 */
public class Code04_MostXorZero {


    /**
     * 思路：
     * 动态规划，假设成立（难）
     *
     * @param arr
     * @return
     */
    public static int mostXor2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        //用来保存异或和为key的值，最晚出现的arr数组下标位置+1,因为要把0存进去（可以理解成 数组的第几个数）
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        indexMap.put(0, 0);
        int[] xorSum = new int[N + 1];
        //生成异或和前缀数组，方便操作
        for (int i = 0; i < N; i++) {
            xorSum[i + 1] = xorSum[i] ^ arr[i];
        }
        //含义：dp[i]   数字前i个数 最优切分下的异或和为0的子数组数量
        int[] dp = new int[N + 1];
        for (int i = 0; i < N; i++) {
            //p1: 在最优切分的情况下：arr[i]位置所在区域异或和为0
            //假设0-i 异或和为x，那么最后一刀一定是切在了 异或和为x 且 离i位置最近的点 ,加入记作lastIndex
            //那么这种情况下的最优解就是 dp[lastIndex] + 1
            int p1 = 0;
            Integer lastIndex = indexMap.get(xorSum[i + 1]);//最晚出现curXor值的数组下标
            if (lastIndex != null) {
                p1 = dp[lastIndex] + 1;
            }

            //p2:在最优切分的情况下：arr[i]位置所在区域异或和 不为0
            //这种情况最优解应该是dp[i-1]
            int p2 = dp[i];
            dp[i + 1] = Math.max(p1, p2);
            //将异或和-下标 更新到map
            indexMap.put(xorSum[i + 1], i+1);
        }
        return dp[N];
    }


    /**
     * 暴力方法
     *
     * @param arr
     * @return
     */
    public static int comparator2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        return process2(arr, 1, list);
    }

    /**
     * 暴力方法枚举所有可能的切法
     * 当前来到位置i，list中存放切点位置（比如存了1，说明在0，1之间切了一刀）
     *
     * @param arr
     * @param i
     * @param list
     * @return
     */
    private static int process2(int[] arr, int i, ArrayList<Integer> list) {
        int ans = 0;
        if (i == arr.length) {
            list.add(i);
            ans = maxORZeroCnt(arr, list);
            list.remove(list.size() - 1);
        } else {
            //情况1 在i-1 ,i 中间切一刀
            list.add(i);
            int p1 = process2(arr, i + 1, list);
            list.remove(list.size() - 1);
            //情况2  不切
            int p2 = process2(arr, i + 1, list);
            ans = Math.max(p1, p2);
        }
        return ans;

    }

    /**
     * 根据切点计算 异或和为0的子数组数量
     *
     * @param arr
     * @param list
     * @return
     */
    private static int maxORZeroCnt(int[] arr, ArrayList<Integer> list) {
        int ans = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            //每一段的范围 [ list(i),list(i+1) )
            //开始位置
            int begin = list.get(i);
            //注意 这里是开区间
            int end = list.get(i + 1);
            int xorSum = 0;
            for (int index = begin; index < end; index++) {
                xorSum ^= arr[index];
            }
            ans += (xorSum == 0 ? 1 : 0);
        }
        return ans;
    }


    // 暴力方法
    public static int comparator(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[] eor = new int[N];
        eor[0] = arr[0];
        for (int i = 1; i < N; i++) {
            eor[i] = eor[i - 1] ^ arr[i];
        }
        return process(eor, 1, new ArrayList<>());
    }

    // index去决定：前一坨部分，结不结束！
    // 如果结束！就把index放入到parts里去
    // 如果不结束，就不放
    public static int process(int[] eor, int index, ArrayList<Integer> parts) {
        int ans = 0;
        if (index == eor.length) {
            parts.add(eor.length);
            ans = eorZeroParts(eor, parts);
            parts.remove(parts.size() - 1);
        } else {
            int p1 = process(eor, index + 1, parts);
            parts.add(index);
            int p2 = process(eor, index + 1, parts);
            parts.remove(parts.size() - 1);
            ans = Math.max(p1, p2);
        }
        return ans;
    }

    public static int eorZeroParts(int[] eor, ArrayList<Integer> parts) {
        int L = 0;
        int ans = 0;
        for (Integer end : parts) {
            if ((eor[end - 1] ^ (L == 0 ? 0 : eor[L - 1])) == 0) {
                ans++;
            }
            L = end;
        }
        return ans;
    }

    // 时间复杂度O(N)的方法
    public static int mostXor(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[] dp = new int[N];

        // key 某一个前缀异或和
        // value 这个前缀异或和上次出现的位置(最晚！)
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        // 0~i整体的异或和
        int xor = 0;
        for (int i = 0; i < N; i++) {
            xor ^= arr[i];
            if (map.containsKey(xor)) { // 可能性2
                int pre = map.get(xor);
                dp[i] = pre == -1 ? 1 : (dp[pre] + 1);
            }
            if (i > 0) {
                dp[i] = Math.max(dp[i - 1], dp[i]);
            }
            map.put(xor, i);
        }
        return dp[N - 1];
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
//        System.out.println(comparator2(new int[]{3, 3}));
        int testTime = 150000;
        int maxSize = 12;
        int maxValue = 5;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int res = mostXor(arr);
            int comp = comparator(arr);
            int comp2 = comparator2(arr);
            int res2 = mostXor2(arr);
            if (res != comp || comp != comp2 || res != res2) {
                succeed = false;
                printArray(arr);
                System.out.println(res);
                System.out.println(comp);
                System.out.println(comp2);
                System.out.println(res2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

}
