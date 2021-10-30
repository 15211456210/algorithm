package com.zcp.part2.slidewin;

import javax.swing.text.ViewFactory;
import java.util.LinkedList;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/28
 * @description：[[加油站的良好出发点问题]] https://leetcode-cn.com/problems/gas-station/
 * @version:
 */
public class GasStation {

    /**
     * gas  = [1,2,3,4,5]
     * cost = [3,4,5,1,2]
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || cost == null || gas.length < 1 || cost.length < 1) {
            return -1;
        }

        int N = gas.length;
        int[] subTab = new int[N];
        for (int i = 0; i < N; i++) {
            subTab[i] = gas[i] - cost[i];//[-2,-2,-2,3,3]
        }

        int[] dubTab = new int[N << 1];
        dubTab[0] = subTab[0];
        for (int i = 1; i < dubTab.length; i++) {
            dubTab[i] = dubTab[i - 1] + subTab[i % N];//[-2,-4,-6,-3,0,-2,-4,-6,-3,0]
        }

        //问题转化为dubTab[L...L+N-1]每个元素-dubTab[L-1]后有没有出现负数的情况，如果N个数都没有出现负数表示，可以从L点出发跑完一圈回到L点
        //进一步转化为dubTab窗口N大小下的最小值问题
        int[] ans = new int[N];
        LinkedList<Integer> minWinQueue = new LinkedList<>();
        for (int R = 0; R < dubTab.length; R++) {
            while (!minWinQueue.isEmpty() && dubTab[minWinQueue.peekLast()] >= dubTab[R]) {
                minWinQueue.pollLast();
            }
            minWinQueue.addLast(R);
            if (R < N - 1) {
                continue;
            }

            int offset = R - N < 0 ? 0 : dubTab[R - N];
            Integer peek = minWinQueue.peekFirst();
            if (R - N + 1 < N) {
                ans[R - N + 1] = dubTab[peek] - offset;
            }
            if (peek == R - N + 1) {
                minWinQueue.pollFirst();
            }
        }
        for (int i = 0; i < ans.length; i++) {
            if (ans[i] >= 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        GasStation gasStation = new GasStation();
        System.out.println(gasStation.canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
    }
}
