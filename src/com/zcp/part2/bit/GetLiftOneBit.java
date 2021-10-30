package com.zcp.part2.bit;

/**
 * 获取一个数 二进制 最右侧的 1
 */
public class GetLiftOneBit {

    public static void main(String[] args) {
        System.out.println(solution(12));
    }

    public static int solution(int a) {
        for (int i = 0; i < 32; i++) {
            if (((1 << i) & (-a)) != 0) {
                return 1 << i;
            }
        }
        return -1;
    }

}
