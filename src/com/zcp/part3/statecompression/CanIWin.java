package com.zcp.part3.statecompression;

/**
 * @description:464. [[我能赢吗]]  [M]
 * https://leetcode-cn.com/problems/can-i-win/
 * @projectName:algorithm
 * @see:com.zcp.part3.statecompression
 * @author:ZCP
 * @createTime:2021/12/2
 * @version:1.0
 */
public class CanIWin {

    /**
     * 动态规划+状态压缩+记忆化搜索 版本
     * @param maxChoosableInteger
     * @param desiredTotal
     * @return
     */
    public boolean canIWin2(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= 0) {
            return true;
        }
        if (((maxChoosableInteger + 1) * maxChoosableInteger) / 2 < desiredTotal) {
            return false;
        }
        //记忆化搜索
        int[] map = new int[1 << (maxChoosableInteger + 1)];//0未处理 1：true -1：false
        //使用容器记录数组是否被选择
        int container = (1 << (maxChoosableInteger + 1)) - 1;
        return process2(container, desiredTotal, maxChoosableInteger, map);
    }

    /**
     * 状态压缩，使用整形值 记录每个数有没有被选择过
     * 当前还剩下多少分数
     * 返回先手是否会赢
     *
     * @param container
     * @param rest
     * @param map
     * @return
     */
    private boolean process2(int container, int rest, int maxChoosableInteger, int[] map) {
        if (map[container] != 0) {
            //说明之前算过了
            return map[container] == 1;
        }
        if (rest <= 0) {
            map[container] = -1;
            return false;
        }
        for (int i = 1; i <= maxChoosableInteger; i++) {
            if ((container & (1 << i)) != 0) {
                //说明该位置没有被选过
                if (!process2(container ^ (1 << i), rest - i, maxChoosableInteger, map)) {
                    map[container] = 1;
                    return true;
                }
            }
        }
        //上面所有情况都试过了，输了
        map[container] = -1;
        return false;
    }


    public boolean canIWin1(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= 0) {
            return true;
        }
        if (((maxChoosableInteger + 1) * maxChoosableInteger) / 2 < desiredTotal) {
            return true;
        }

        int[] ints = new int[maxChoosableInteger];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = i + 1;
        }
        return process1(ints, desiredTotal);
    }

    /**
     * 当前还剩下的数存在ints数组中
     * 当前还剩下多少分数
     * 返回先手是否会赢
     *
     * @param ints
     * @param desiredTotal
     * @return
     */
    private boolean process1(int[] ints, int desiredTotal) {
        if (desiredTotal <= 0) {
            return false;
        }
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] != -1) {
                //!=-1说明还没被选
                int score = ints[i];
                ints[i] = -1;
                boolean r = process1(ints, desiredTotal - score);
                ints[i] = score;//恢复现场
                if (!r) {
                    //如果子过程的先手输了 说明 自己赢了
                    return true;
                }
            }
        }
        //上面所有情况都试过了，输了
        return false;
    }

    public static void main(String[] args) {
        CanIWin canIWin = new CanIWin();
        canIWin.canIWin2(18,188);
    }

}
