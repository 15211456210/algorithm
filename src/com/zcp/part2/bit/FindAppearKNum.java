package com.zcp.part2.bit;

/**
 * 一个数组中只有一个数出现K（K<M）次，其余的数都出现了M次
 * 找出这个出现K次的数
 */
public class FindAppearKNum {

    public static void main(String[] args) {

        int[] arr = {2, 3, 5, 6, 2, 3, 5, 3, 6, 5, 2};

        System.out.println(solution(arr, 2, 3));
    }

    public static int solution(int[] arr, int K, int M) {
        int[] mask = new int[32];
        for (int i : arr) {
            for (int k = 0; k < 32; k++) {
                if (((1 << k) & i) != 0) {
                    mask[k]++;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (mask[i] % M == K) {
                //说明那个数 在第I位上是1
                ans += (1 << i);
            }
        }
        return ans;

    }

}
