package com.zcp.part5.c451to500;

/**
 * @author ZCP
 * @title: C464
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/can-i-win/
 * @date 2023/2/8 11:27
 */
public class C464 {

    class Solution {
        public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
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
    }
}
