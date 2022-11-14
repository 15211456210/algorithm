package com.zcp.part5.c251to300;

/**
 * @author ZCP
 * @title: C2763
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/integer-to-english-words/submissions/
 * @date 2022/9/24 21:01
 */
public class C273 {

    static String[][] unitStr = new String[][]{{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"},
            {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"},
            {"Hundred"},
            {"Thousand"},
            {"Million"},
            {"Billion"}};

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        if (num < 20) {
            return unitStr[0][num];
        }
        String ans = "";
        int c = 0;
        int tn = 0;
        int k = 1;
        while (num > 0) {
            int n = num % 10;
            if (c <= 1) {
                tn += n * k;
                k *= 10;
                if (tn >= 10 && tn <= 19) {
                    ans = unitStr[0][tn];
                } else {
                    ans = unitStr[c][n] + (n == 0 ? "" : " ") + ans;
                }
                if (c == 1) {
                    tn = 0;
                    k = 1;
                }
            } else if (c == 2) {
                if (n > 0){
                    ans = unitStr[0][n] + " " + unitStr[2][0] + " " + ans;
                }
            } else if (c <= 5) {
                tn += n * k;
                k *= 10;
                if (c == 5) {
                    if (tn > 0){
                        ans = numberToWords(tn) + " " + unitStr[3][0] + " " + ans;
                    }
                    tn = 0;
                    k = 1;
                }
            } else if (c <= 8) {
                tn += n * k;
                k *= 10;
                if (c == 8) {
                    if (tn > 0){
                        ans = numberToWords(tn) + " " + unitStr[4][0] + " " + ans;
                    }
                    tn = 0;
                    k = 1;
                }
            } else {
                tn += n * k;
                k *= 10;
                if (c == 11) {
                    if (tn > 0){
                        ans = numberToWords(tn) + " " + unitStr[5][0] + " " + ans;
                    }
                    tn = 0;
                    k = 1;
                }
            }
            c++;
            num /= 10;
        }
        if (tn != 0) {
            if (c <= 5) {
                ans = numberToWords(tn) + " " + unitStr[3][0] + " " + ans;
            } else if (c <= 8) {
                ans = numberToWords(tn) + " " + unitStr[4][0] + " " + ans;
            } else {
                ans = numberToWords(tn) + " " + unitStr[5][0] + " " + ans;
            }

        }
        while (ans.charAt(ans.length() - 1) == ' ') {
            ans = ans.substring(0, ans.length() - 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new C273().numberToWords(2));
    }
}
