package com.zcp.part4.day13;

// 本题测试链接 : https://leetcode.com/problems/super-washing-machines/
public class Code02_SuperWashingMachines {


    /**
     * 思路：
     *
     * @param machines
     * @return
     */
    public int findMinMoves2(int[] machines) {
        if (machines == null || machines.length < 1 || !canAveAssign(machines)) {
            return -1;
        }
        int len = machines.length;
        // help[i][0] 表示当前machines[i]左侧多出来的衣服数（负数表示 少的衣服数量）
        // help[i][1] 表示当前machines[i]的多出来的衣服数量（负数表示 少的衣服数量）
        // help[i][2] 表示当前machines[i]右侧多出来的衣服数（负数表示 少的衣服数量）
        int[][] help = generateHelp(machines);

        int minMove = 0;

        for (int i = 0; i < help.length; i++) {
            if (help[i][0] > 0 && help[i][2] > 0 || help[i][0] > 0 && help[i][2] <= 0 || help[i][2] > 0 && help[i][1] <= 0) {
                minMove = Math.max(minMove, Math.max(Math.abs(help[i][0]), Math.abs(help[i][2])));
            } else if (help[i][0] <= 0 && help[i][2] <= 0) {
                minMove = Math.max(minMove, Math.abs(help[i][0]) + Math.abs(help[i][2]));
            }
        }
        return minMove;
    }

    /**
     * 生成help数组
     *
     * @param machines
     * @return
     */
    private int[][] generateHelp(int[] machines) {
        int len = machines.length;
        int[][] help = new int[len][3];
        int sum = 0;
        for (int i = 0; i < machines.length; i++) {
            sum += machines[i];
        }
        int aveCnt = sum / machines.length;

        sum = 0;
        for (int i = 0; i < machines.length; i++) {
            help[i][0] = sum - i * aveCnt;
            help[i][1] = machines[i] - aveCnt;
            help[i][2] = -help[i][0] - help[i][1];
            sum += machines[i];
        }
        return help;
    }

    public static void main(String[] args) {
        System.out.println(new Code02_SuperWashingMachines().findMinMoves2(new int[]{0, 3, 0}));
    }

    /**
     * 所有衣服数量是否可以平均分配给每一台洗衣机
     *
     * @param machines
     * @return
     */
    private boolean canAveAssign(int[] machines) {
        int sum = 0;
        for (int i = 0; i < machines.length; i++) {
            sum += machines[i];
        }
        return sum % machines.length == 0;
    }


    public static int findMinMoves(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int size = arr.length;
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += arr[i];
        }
        if (sum % size != 0) {
            return -1;
        }
        int avg = sum / size;
        int leftSum = 0;
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            int leftRest = leftSum - i * avg;
            int rightRest = (sum - leftSum - arr[i]) - (size - i - 1) * avg;
            if (leftRest < 0 && rightRest < 0) {
                ans = Math.max(ans, Math.abs(leftRest) + Math.abs(rightRest));
            } else {
                ans = Math.max(ans, Math.max(Math.abs(leftRest), Math.abs(rightRest)));
            }
            leftSum += arr[i];
        }
        return ans;
    }

}
