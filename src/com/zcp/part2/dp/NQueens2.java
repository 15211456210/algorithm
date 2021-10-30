package com.zcp.part2.dp;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/26
 * @description：N皇后问题 https://leetcode-cn.com/problems/n-queens-ii/
 * @version:
 */
public class NQueens2 {


    public int totalNQueens(int n) {
        if (n < 1) {
            return 0;
        }
        //表示皇后的坐标  index 表示行，value表示列
        int[] mask = new int[n];
        return process(n,0,mask);
    }


    public int process(int n, int index, int[] mask) {
        if (index == n) {
            return 1;
        }
        int ansCnt = 0;
        for (int i = 0; i < n; i++) {
            if (check(i, index, mask)) {
                //在该点放置皇后
                mask[index] = i;
                ansCnt += process(n, index + 1, mask);
            }
        }
        return ansCnt;
    }

    /**
     * 判断当前[index,i]位置是否可以放置皇后
     *
     * @param i
     * @param index
     * @return
     */
    private boolean check(int i, int index, int[] mask) {
        for (int k = 0; k < index; k++) {
            if (mask[k] == i || (Math.abs(k - index) == Math.abs(mask[k] - i))) {
                return false;
            }
        }
        return true;
    }
}
