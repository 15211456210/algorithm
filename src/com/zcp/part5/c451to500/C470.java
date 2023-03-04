package com.zcp.part5.c451to500;

import java.util.Random;

/**
 * @author ZCP
 * @title: C470
 * @projectName algorithm
 * @description: https://leetcode.com/problems/implement-rand10-using-rand7/submissions/
 * @date 2023/2/15 16:02
 */
public class C470 {

    public int rand10() {
        return rand9() + 1;
    }

    /**
     * 0-9等概率
     */
    public int rand9() {
        int ans = 0;
        do {
            ans = (rand01() << 3) + (rand01() << 2) + (rand01() << 1) + (rand01());
        } while (ans > 9);
        return ans;
    }

    /**
     * 等概率返回0，1
     */
    public int rand01() {
        int ans = 0;
        do {
            ans = rand7();
        } while (ans == 4);

        return ans < 4 ? 0 : 1;
    }

    private int rand7() {
        return (int) (Math.random() * 7) + 1;
    }
}
