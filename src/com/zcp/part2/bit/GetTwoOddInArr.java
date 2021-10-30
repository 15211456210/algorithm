package com.zcp.part2.bit;

/**
 * 打印数组中出现奇数次的两个数
 * 假设只有2个数出现了奇数次，其余的数都出现偶数次
 */
public class GetTwoOddInArr {
    public static void main(String[] args) {
        int[] arr = {2, 5, 4, 8, 5, 3, 4, 2, 3, 2, 4, 9, 9, 2};
        solution(arr);
    }


    public static void solution(int[] arr) {
        int e1 = 0;
        for (int i : arr) {
            e1 ^= i;
        }
        //e1 = a^b
        int index = 0;
        for (; index < 32; index++) {
            if (((1 << index) & (-e1)) != 0) {
                break;
            }
        }
        int e2 = 1 << index;
        int e3 = 0 ;
        for (int i : arr) {
            if((e2 & i) != 0){
                e3 ^= i;
            }
        }
        //此时 e3 = a 或则 b
        System.out.printf(e3 + "  " + (e3^e1));
    }

}
