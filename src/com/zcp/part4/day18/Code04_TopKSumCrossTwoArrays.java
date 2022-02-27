package com.zcp.part4.day18;

// 牛客的测试链接：
// https://www.nowcoder.com/practice/7201cacf73e7495aa5f88b223bbbf6d1
// 不要提交包信息，把import底下的类名改成Main，提交下面的代码可以直接通过
// 因为测试平台会卡空间，所以把set换成了动态加和减的结构

import com.zcp.part4.day04.Code08_TheSkylineProblem;

import java.util.Scanner;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Code04_TopKSumCrossTwoArrays {


    static class Node2 {
        int i;
        int j;
        int value;

        public Node2(int i, int j, int value) {
            this.i = i;
            this.j = j;
            this.value = value;
        }
    }

    static class NodeComparator implements Comparator<Node2> {
        @Override
        public int compare(Node2 o1, Node2 o2) {
            return o2.value - o1.value;
        }
    }

    /**
     * 思路：
     * 大根堆+动态规划
     *
     * @param arr1
     * @param arr2
     * @param K
     * @return
     */
    public static int[] topKSum2(int[] arr1, int[] arr2, int K) {
        if (arr1 == null || arr2 == null) {
            return new int[0];
        }
        int n = arr1.length;
        if (n * n < K) {
            return new int[0];
        }
        int[] ans = new int[K];

        PriorityQueue<Node2> maxHeap = new PriorityQueue<>(new NodeComparator());
        HashSet<Long> tmpSet = new HashSet<>();
        maxHeap.add(new Node2(n - 1, n - 1, arr1[n - 1] + arr2[n - 1]));
        tmpSet.add((long) (n * n - 1));
        for (int i = 0; i < K; i++) {
            Node2 poll = maxHeap.poll();
            ans[i] = poll.value;
            if (poll.i > 0 && !tmpSet.contains((long) ((poll.i - 1) * n + poll.j))) {
                maxHeap.add(new Node2(poll.i - 1, poll.j, arr1[poll.i - 1] + arr2[poll.j]));
                tmpSet.add((long) ((poll.i - 1) * n + poll.j));
            }
            if (poll.j > 0 && !tmpSet.contains((long) ((poll.i) * n + poll.j - 1))) {
                maxHeap.add(new Node2(poll.i, poll.j - 1, arr1[poll.i] + arr2[poll.j - 1]));
                tmpSet.add((long) ((poll.i) * n + poll.j - 1));
            }

        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] arr1 = new int[N];
        int[] arr2 = new int[N];
        for (int i = 0; i < N; i++) {
            arr1[i] = sc.nextInt();
        }
        for (int i = 0; i < N; i++) {
            arr2[i] = sc.nextInt();
        }
//        int[] topK = topKSum(arr1, arr2, K);
        int[] topK = topKSum2(arr1, arr2, K);
        for (int i = 0; i < K; i++) {
            System.out.print(topK[i] + " ");
        }
        System.out.println();
        sc.close();
    }

    // 放入大根堆中的结构
    public static class Node {
        public int index1;// arr1中的位置
        public int index2;// arr2中的位置
        public int sum;// arr1[index1] + arr2[index2]的值

        public Node(int i1, int i2, int s) {
            index1 = i1;
            index2 = i2;
            sum = s;
        }
    }

    // 生成大根堆的比较器
    public static class MaxHeapComp implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.sum - o1.sum;
        }
    }

    public static int[] topKSum(int[] arr1, int[] arr2, int topK) {
        if (arr1 == null || arr2 == null || topK < 1) {
            return null;
        }
        int N = arr1.length;
        int M = arr2.length;
        topK = Math.min(topK, N * M);
        int[] res = new int[topK];
        int resIndex = 0;
        PriorityQueue<Node> maxHeap = new PriorityQueue<>(new MaxHeapComp());
        HashSet<Long> set = new HashSet<>();
        int i1 = N - 1;
        int i2 = M - 1;
        maxHeap.add(new Node(i1, i2, arr1[i1] + arr2[i2]));
        set.add(x(i1, i2, M));
        while (resIndex != topK) {
            Node curNode = maxHeap.poll();
            res[resIndex++] = curNode.sum;
            i1 = curNode.index1;
            i2 = curNode.index2;
            set.remove(x(i1, i2, M));
            if (i1 - 1 >= 0 && !set.contains(x(i1 - 1, i2, M))) {
                set.add(x(i1 - 1, i2, M));
                maxHeap.add(new Node(i1 - 1, i2, arr1[i1 - 1] + arr2[i2]));
            }
            if (i2 - 1 >= 0 && !set.contains(x(i1, i2 - 1, M))) {
                set.add(x(i1, i2 - 1, M));
                maxHeap.add(new Node(i1, i2 - 1, arr1[i1] + arr2[i2 - 1]));
            }
        }
        return res;
    }

    public static long x(int i1, int i2, int M) {
        return (long) i1 * (long) M + (long) i2;
    }

}
