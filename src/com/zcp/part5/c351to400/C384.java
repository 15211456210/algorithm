package com.zcp.part5.c351to400;

import java.util.Random;

/**
 * @author ZCP
 * @title: C384
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/shuffle-an-array/
 * @date 2022/10/31 10:59
 */
public class C384 {

    class Solution {


        private int[] src;
        private int[] shuffle;
        private Random random;

        public Solution(int[] nums) {
            this.src = nums;
            this.shuffle = new int[nums.length];
            System.arraycopy(src, 0, shuffle, 0, src.length);
            this.random = new Random();
        }

        public int[] reset() {
            return src;
        }

        public int[] shuffle() {
            int length = src.length;
            for (int i = 0; i < length; i++) {
                int rand = random.nextInt(i + 1);
                swap(rand, i);
            }
            return shuffle;
        }

        private void swap(int a, int b) {
            int tmp = shuffle[a];
            shuffle[a] = shuffle[b];
            shuffle[b] = tmp;
        }
    }
}
