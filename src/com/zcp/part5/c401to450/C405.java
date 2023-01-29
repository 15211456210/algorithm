package com.zcp.part5.c401to450;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZCP
 * @title: C405
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/convert-a-number-to-hexadecimal/
 * @date 2022/11/21 14:27
 */
public class C405 {

//    static char[] cs = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
//    static Map<Character, Character> negativeMap;
//
//    static {
//        negativeMap = new HashMap<>();
//        negativeMap.put('0', 'f');
//        negativeMap.put('1', 'e');
//        negativeMap.put('2', 'd');
//        negativeMap.put('3', 'c');
//        negativeMap.put('4', 'b');
//        negativeMap.put('5', 'a');
//        negativeMap.put('6', '9');
//        negativeMap.put('7', '8');
//        negativeMap.put('8', '7');
//        negativeMap.put('9', '6');
//        negativeMap.put('a', '5');
//        negativeMap.put('b', '4');
//        negativeMap.put('c', '3');
//        negativeMap.put('d', '2');
//        negativeMap.put('e', '1');
//        negativeMap.put('f', '0');
//    }
//
//    public String toHex(int num) {
//        if (num == 0) {
//            return "0";
//        }
//        if (num == Integer.MIN_VALUE) {
//            return "80000000";
//        }
//        String ans = "";
//        boolean negative = num < 0;
//        num = negative ? -num : num;
//        while (num > 0) {
//            ans = cs[num % 16] + ans;
//            num /= 16;
//        }
//        if (negative) {
//            String tmpAns = "";
//            for (int i = 0; i < ans.length(); i++) {
//                tmpAns += negativeMap.get(ans.charAt(i));
//            }
//            int length = tmpAns.length();
//            for (int i = 0; i < 8 - length; i++) {
//                tmpAns = 'f' + tmpAns;
//            }
//            ans = tmpAns;
//            tmpAns = "";
//            int carry = 1;
//            int i = ans.length() - 1;
//            for (; i >= 0 && carry > 0; i--) {
//                tmpAns = (ans.charAt(i) == 'f' ? '0' : (ans.charAt(i) == '9' ? 'a' : (char) (ans.charAt(i) + carry))) + tmpAns;
//                carry = ans.charAt(i) == 'f' ? 1 : 0;
//            }
//            if (carry > 0) {
//                tmpAns = '1' + tmpAns;
//            } else {
//                tmpAns = ans.substring(0, i + 1) + tmpAns;
//            }
//            ans = tmpAns;
//        }
//        return ans;
//    }

    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 7; i >= 0; i--) {
            int val = (num >> (4 * i)) & 0xf;
            if (sb.length() > 0 || val > 0) {
                char digit = val < 10 ? (char) ('0' + val) : (char) ('a' + val - 10);
                sb.append(digit);
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(new C405().toHex(-111110));
    }
}
