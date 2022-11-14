package com.zcp.part5.c301to350;


import java.math.BigInteger;

/**
 * @author ZCP
 * @title: C306
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/additive-number/
 * @date 2022/9/27 15:28
 */
public class C306 {

    public boolean isAdditiveNumber(String num) {
        boolean res = false;
        int len = num.length();
        for (int i = 1; i < len - 1; i++) {
            String num1 = num.substring(0, i);
            for (int j = i + 1; j < len; j++) {
                String nums2 = num.substring(i, j);
                res |= process(num.substring(j), num1, nums2);
                if (j == i + 1 && num.charAt(i) == '0') {
                    break;
                }
            }
            if (i == 1 && num.charAt(0) == '0'){
                break;
            }
        }
        return res;
    }

    public boolean process(String num, String n1, String n2) {
        if (num.length() == 0) {
            return true;
        }
        BigInteger num1 = new BigInteger(n1);
        BigInteger num2 = new BigInteger(n2);

        String sum = num1.add(num2).toString();

        if (num.length() < sum.length()) {
            return false;
        }

        if ((num.substring(0, sum.length())).equals(sum)) {
            return process(num.substring(sum.length()), n2, sum);
        } else {
            return false;
        }

    }

    public static void main(String[] args) {
        boolean res = new C306().isAdditiveNumber("11111111111011111111111");
        System.out.println(res);


    }
}
