package com.zcp.part5.c351to400;

/**
 * @author ZCP
 * @title: C374
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/guess-number-higher-or-lower/description/
 * @date 2022/10/26 16:24
 */
public class C374 {

    class GuessGame {
        int guess(int num) {
            return 0;
        }
    }

    public class Solution extends GuessGame {
        public int guessNumber(int n) {
            int l = 1, r = n;

            while (l <= r) {
                int mid = l + (r - l) / 2;
                int guess = guess(mid);
                if (guess == 0) {
                    return mid;
                } else if (guess > 0) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            return 0;
        }
    }
}
