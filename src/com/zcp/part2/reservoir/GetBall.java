package com.zcp.part2.reservoir;

import java.util.Arrays;

/**
 * @author ：ZCP
 * @date ：Created in 2021/10/18
 * @description： [[机器所有吐出的球都等概率放进袋子里]]
 * 要求从N个元素中随机的抽取k个元素，其中N无法确定。
 * 这种应用的场景一般是数据流的情况下，由于数据只能被读取一次，而且数据量很大，并不能全部保存，因此数据量N是无法在抽样开始时确定的；但又要保持随机性，于是有了这个问题。所以搜索网站有时候会问这样的问题。
 * 这里的核心问题就是“随机”，怎么才能是随机的抽取元素呢？我们设想，买彩票的时候，由于所有彩票的中奖概率都是一样的，所以我们才是“随机的”买彩票。那么要使抽取数据也随机，必须使每一个数据被抽样出来的概率都一样。
 * @version:
 */
public class GetBall {


    public static void solution() {

        int testTime = 1000;
        int range = 10000;
        int bagSize = 1000;
        int[] ans = new int[range];
        for (int i = 0; i < testTime; i++) {
            int[] bag = new int[bagSize];
            for (int k = 0; k < range; k++) {
                if (k < bagSize) {
                    bag[k] = k;
                } else {
                    if (Math.random() < (double) bagSize / k) {
                        bag[(int) (Math.random() * bagSize)] = k;
                    }
                }
            }
            for (int num : bag) {
                ans[num]++;
            }
        }
        System.out.println(Arrays.toString(ans));
    }

    public static void main(String[] args) {
        solution();
    }

}
