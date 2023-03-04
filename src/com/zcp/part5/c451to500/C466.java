package com.zcp.part5.c451to500;

import java.util.HashMap;

/**
 * @author ZCP
 * @title: C466
 * @projectName algorithm
 * @description: https://leetcode.com/problems/count-the-repetitions/
 * @date 2023/2/11 16:00
 */
public class C466 {


    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        char[] st1 = s1.toCharArray(), st2 = s2.toCharArray();
        int num = 0, pos = 0, len1 = st1.length, len2 = st2.length;
        for (int i = 1; i <= n1; ++i) {
            for (int j = 0; j < len1; ++j) {
                if (st1[j] == st2[pos]) {
                    pos++;
                }
                if (pos == len2) {
                    num++;
                    pos = 0;
                }
            }
        }
        return num / n2;
    }

//
//    /**
//     * 暴力 ttl
//     * @param s1
//     * @param n1
//     * @param s2
//     * @param n2
//     * @return
//     */
//    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
//        int tn2 = n2;
//        int p1 = 0, p2 = 0;
//        int len1 = s1.length();
//        int len2 = s2.length();
//        int ans = 0;
//
//        while (n1 > 0) {
//            while (n1 > 0 && s1.charAt(p1) != s2.charAt(p2)) {
//                if (p1 + 1 == len1) {
//                    n1--;
//                }
//                p1 = (p1 + 1) % len1;
//            }
//            if (p1 + 1 == len1) {
//                n1--;
//            }
//            p1 = (p1 + 1) % len1;
//            if (p2 + 1 == len2) {
//                p2 = 0;
//                if (--n2 == 0) {
//                    n2 = tn2;
//                    ans++;
//                }
//            } else {
//                p2++;
//            }
//        }
//
//        return ans;
//    }

    public static void main(String[] args) {
        System.out.println(new C466().getMaxRepetitions("aaa", 3, "aa", 1));
    }


}
