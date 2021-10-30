package com.zcp.part2.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/11 14:54
 * @description：字符串组成的数组拼接后字典序最小的结果: ["aba","cc","s"]  -> "abaccs"
 * @version:
 */
public class MaxStrArrVal {


    public static class MyCompalator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);//此处不能直接比较o1<o2
        }
    }

    public static String solution(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs, new MyCompalator());
        String ans = "";
        for (String str : strs) {
            ans += str;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(solution(new String[]{"eaba","cc","s"}));
    }

}
