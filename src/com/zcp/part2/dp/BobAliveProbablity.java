package com.zcp.part2.dp;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/23
 * @description：生还概率问题 给定 n,m,x,y,k 5个参数
 * n,m代表棋盘大小n*m
 * <p>
 * x，y表示Bob起始位置
 * k表示Bob要走K步
 * <p>
 * 每次随机 上下左右方向走一格
 * 要求返回走完K步后落在棋盘中的概率（一旦走出棋盘范围就算出局）
 * @version:
 */
public class BobAliveProbablity {


    public static double solution(int n, int m, int x, int y, int k) {
        if (n < 1 || m < 1 || x < 1 || x > n - 1 || y < 1 || y > m - 1) {
            return 0;
        }
        //总数位4的K次幂
        double pow = Math.pow(4, k);
        int process = process(n, m, x, y, k);
        return process / pow;
    }

    /**
     * 落在棋盘上的次数
     *
     * @return
     */
    public static int process(int n, int m, int x, int y, int rest) {
        if (x < 0 || y < 0 || x > n - 1 || y > m - 1) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }
        int count = 0;
        count += process(n, m, x + 1, y, rest - 1);
        count += process(n, m, x, y + 1, rest - 1);
        count += process(n, m, x - 1, y, rest - 1);
        count += process(n, m, x, y - 1, rest - 1);
        return count;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 14; i++) {
            System.out.println(solution(9,8,3,3,i));
        }

    }
}
