package com.zcp.part4.day06;

public class Code01_MaxXOR {


    /**
     * 前缀树 数据结构
     */
    public static class TreeNode {
        TreeNode[] nexts = new TreeNode[2];

        /**
         * 往前缀树中添加int值
         * 将num的二进制值 从高位-地位的顺序添加到前缀树
         * @param num
         */
        public void add(int num) {
            TreeNode cur = this;
            for (int i = 31; i >= 0; i--) {
                int bitVal = ((num >> i) & 1);
                if (cur.nexts[bitVal] == null) {
                    cur.nexts[bitVal] = new TreeNode();
                }
                cur = cur.nexts[bitVal];
            }
        }

        /**
         * 返回前缀树中 和 num值 异或后的最大结果
         * @param num
         * @return
         */
        public int maxXor(int num) {
            TreeNode cur = this;
            int ans = 0;
            for (int i = 31; i >= 0; i--) {
                //31 位单独考虑（因为是符号位）
                //如果是 1 表示负数，要想异或结果尽可能大，需要匹配的值应该是 1
                //如果是其他普遍位置 0 匹配 1，1匹配0
                int bestBitVal = (i == 31 ? ((num >> i) & 1) : (1 ^ ((num >> i) & 1)));
                //求出预期的最佳选择后，还需要判断前缀树中是否有这条路经，如果没有只能选择有的路径走下去
                bestBitVal = cur.nexts[bestBitVal] == null ? (1 ^ bestBitVal) : bestBitVal;
                cur = cur.nexts[bestBitVal];
                //选择好了后 将值或上去
                ans |= (num ^ (bestBitVal << i)) & (1 << i);
            }
            return ans;
        }
    }

    /**
     * 思路：
     * 前缀树，贪心
     *
     * @param arr
     * @return
     */
    public static int maxXorSubarray3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        TreeNode root = new TreeNode();
        root.add(0);
        int xorSum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            xorSum ^= arr[i];
            max = Math.max(max, root.maxXor(xorSum));
            root.add(xorSum);
        }
        return max;
    }

    // O(N^2)
    public static int maxXorSubarray1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 准备一个前缀异或和数组eor
        // eor[i] = arr[0...i]的异或结果
        int[] eor = new int[arr.length];
        eor[0] = arr[0];
        // 生成eor数组，eor[i]代表arr[0..i]的异或和
        for (int i = 1; i < arr.length; i++) {
            eor[i] = eor[i - 1] ^ arr[i];
        }
        int max = Integer.MIN_VALUE;
        for (int j = 0; j < arr.length; j++) {
            for (int i = 0; i <= j; i++) { // 依次尝试arr[0..j]、arr[1..j]..arr[i..j]..arr[j..j]
                max = Math.max(max, i == 0 ? eor[j] : eor[j] ^ eor[i - 1]);
            }
        }
        return max;
    }

    // 前缀树的Node结构
    // nexts[0] -> 0方向的路
    // nexts[1] -> 1方向的路
    // nexts[0] == null 0方向上没路！
    // nexts[0] != null 0方向有路，可以跳下一个节点
    // nexts[1] == null 1方向上没路！
    // nexts[1] != null 1方向有路，可以跳下一个节点
    public static class Node {
        public Node[] nexts = new Node[2];
    }

    // 基于本题，定制前缀树的实现
    public static class NumTrie {
        // 头节点
        public Node head = new Node();

        public void add(int newNum) {
            Node cur = head;
            for (int move = 31; move >= 0; move--) {
                int path = ((newNum >> move) & 1);
                cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
                cur = cur.nexts[path];
            }
        }

        // 该结构之前收集了一票数字，并且建好了前缀树
        // num和 谁 ^ 最大的结果（把结果返回）
        public int maxXor(int num) {
            Node cur = head;
            int ans = 0;
            for (int move = 31; move >= 0; move--) {
                // 取出num中第move位的状态，path只有两种值0就1，整数
                int path = (num >> move) & 1;
                // 期待遇到的东西
                int best = move == 31 ? path : (path ^ 1);
                // 实际遇到的东西
                best = cur.nexts[best] != null ? best : (best ^ 1);
                // (path ^ best) 当前位位异或完的结果
                ans |= (path ^ best) << move;
                cur = cur.nexts[best];
            }
            return ans;
        }
    }

    // O(N)
    public static int maxXorSubarray2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        // 0~i整体异或和
        int xor = 0;
        NumTrie numTrie = new NumTrie();
        numTrie.add(0);
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i]; // 0 ~ i
            max = Math.max(max, numTrie.maxXor(xor));
            numTrie.add(xor);
        }
        return max;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
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

//        System.out.println(maxXorSubarray3(new int[]{-33, 3}));
        int testTime = 500000;
        int maxSize = 500;
        int maxValue = 50;
        boolean succeed = true;
        long time1 = 0;
        long time2 = 0;
        long time3 = 0;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            long tl = System.currentTimeMillis();
            int res = maxXorSubarray2(arr);
            long tl1 = System.currentTimeMillis();
            time1 += tl1 - tl;
            int res3 = maxXorSubarray3(arr);
            long tl2 = System.currentTimeMillis();
            time2 += tl2 - tl1;
            int comp = maxXorSubarray1(arr);
            long tl3 = System.currentTimeMillis();
            time3 += tl3 - tl2;
            if (res != comp || res3 != comp) {
                succeed = false;
                printArray(arr);
                System.out.println(res);
                System.out.println(comp);
                System.out.println(res3);
                break;
            }
        }
        System.out.println(time1 + " ," + time2 + " ," + time3);
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
