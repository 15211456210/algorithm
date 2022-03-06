package com.zcp.part4.day19;

public class Code03_OneNumber {


    /**
     * 思路：
     * 数位dp
     *
     * @param num
     * @return
     */
    public static int solution3(int num) {
        if (num < 1) {
            return 0;
        }
        return process3(num);
    }

    /**
     * 1876
     * 877-1876
     * 77-876
     * 7-76
     * 1-6
     *
     * @param num
     * @return
     */
    public static int process3(int num) {
        if (num < 1) {
            return 0;
        } else if (num < 10) {
            return 1;
        }

        int n = num;
        int pow = 0;//num位数-1
        while ((n = (n / 10)) > 0) {
            pow++;
        }
        int div = (int) Math.pow(10, pow);
        //最高位
        int high = num / div;
        //除了最高位
        int rest = num % div;

        if (high != 1) {
            //process3(6876) = process3(876) + （877-6876范围）千位1个数+百位1个数+十位1个数+个位1个数
            return process3(rest) + (int) (div + high * pow * Math.pow(10, pow - 1));
        } else {
            //process3(1876) = process3(876) + （877-1876范围）千位1个数+百位1个数+十位1个数+个位1个数
            return process3(rest) + (int) (rest + 1 + pow * Math.pow(10, pow - 1));
        }

    }


    public static int solution1(int num) {
        if (num < 1) {
            return 0;
        }
        int count = 0;
        for (int i = 1; i != num + 1; i++) {
            count += get1Nums(i);
        }
        return count;
    }

    public static int get1Nums(int num) {
        int res = 0;
        while (num != 0) {
            if (num % 10 == 1) {
                res++;
            }
            num /= 10;
        }
        return res;
    }


    // 1 ~ num 这个范围上，画了几道1
    public static int solution2(int num) {
        if (num < 1) {
            return 0;
        }
        // num -> 13625
        // len = 5位数
        int len = getLenOfNum(num);
        if (len == 1) {
            return 1;
        }
        // num  13625
        // tmp1 10000
        //
        // num  7872328738273
        // tmp1 1000000000000
        int tmp1 = powerBaseOf10(len - 1);
        // num最高位 num / tmp1
        int first = num / tmp1;
        // 最高1 N % tmp1 + 1
        // 最高位first tmp1
        int firstOneNum = first == 1 ? num % tmp1 + 1 : tmp1;
        // 除去最高位之外，剩下1的数量
        // 最高位1 10(k-2次方) * (k-1) * 1
        // 最高位first 10(k-2次方) * (k-1) * first
        int otherOneNum = first * (len - 1) * (tmp1 / 10);
        return firstOneNum + otherOneNum + solution2(num % tmp1);
    }

    public static int getLenOfNum(int num) {
        int len = 0;
        while (num != 0) {
            len++;
            num /= 10;
        }
        return len;
    }

    public static int powerBaseOf10(int base) {
        return (int) Math.pow(10, base);
    }

    public static void main(String[] args) {
        int num = 518321532;
//        long start1 = System.currentTimeMillis();
//        System.out.println(solution1(num));
//        long end1 = System.currentTimeMillis();
//        System.out.println("cost time: " + (end1 - start1) + " ms");

        long start2 = System.currentTimeMillis();
        System.out.println(solution2(num));
        long end2 = System.currentTimeMillis();
        System.out.println("cost time: " + (end2 - start2) + " ms");

        long start3 = System.currentTimeMillis();
        System.out.println(solution3(num));
        long end3 = System.currentTimeMillis();
        System.out.println("cost time: " + (end3 - start3) + " ms");

    }
}
