package com.zcp.part5.c301to350;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author ZCP
 * @title: C336
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/palindrome-pairs/description/
 * @date 2022/10/19 17:03
 */
public class C336 {

    /**
     * 思路：
     * 将字符预先存入set，之后遍历每一个字符串需要寻找的缺失串
     *
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> list = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int len = word.length();
            //全量逆序单独处理
            String target = reverse2(word);
            if (map.containsKey(target) && map.get(target).intValue() != i) {
                list.add(Arrays.asList(map.get(target), i));
            }
            //得到最大回文半径数组
            int[] radArr = manacher(word);
            //处理前缀是回文的
            for (int j = 0; j < len; j++) {
                //判断word[0..j]是否是回文
                if (radArr[j + 1] / 2 >= j / 2 + 1) {
                    target = reverse2(word.substring(j + 1));
                    if (map.containsKey(target)) {
                        list.add(Arrays.asList(map.get(target), i));
                    }
                }
                if (radArr[len * 2 - j - 1] / 2 >= j / 2 + 1) {
                    target = reverse2(word.substring(0, len - j - 1));
                    if (map.containsKey(target)) {
                        list.add(Arrays.asList(i, map.get(target)));
                    }
                }
            }
        }
        return list;
    }

    /**
     * manacher算法生成回文半径数组
     *
     * @param s
     * @return
     */
    public int[] manacher(String s) {
        char[] chars = s.toCharArray();
        char[] newChars = new char[chars.length * 2 + 1];
        for (int i = 0; i < newChars.length; i++) {
            newChars[i] = (i % 2 == 0 ? '#' : chars[i / 2]);
        }
        //每个位置的最长回文半径数组
        int[] radArr = new int[newChars.length];
        int center = 0;//当前最优范围的中心点下标
        int r = 0;//已包围的最优边界
        radArr[0] = 1;
        for (int i = 1; i < newChars.length; i++) {
            if (r < i) {
                int rad = maximumPalindromeRadius(newChars, i, 1);
                center = i;
                radArr[i] = rad;
                r = i + rad - 1;
            } else {
                //r>=i
                //计算出目前根据前面计算的位置数据可以确定  的 最小 回文半径
                int minLen = Math.min(r - i + 1, radArr[2 * center - i]);
                //在这个基础上继续尝试去括
                int rad = maximumPalindromeRadius(newChars, i, minLen);
                radArr[i] = rad;
                if (i + rad - 1 > r) {
                    center = i;
                    r = i + rad - 1;
                }
            }
        }
        return radArr;
    }

    /**
     * 获取最大回文半径
     *
     * @param newChars
     * @param i        当前位置
     * @param rad      已知的回文半径
     * @return
     */
    private int maximumPalindromeRadius(char[] newChars, int i, int rad) {
        int l = i - rad;
        int r = i + rad;
        while (l >= 0 && r < newChars.length) {
            if (newChars[l] != newChars[r]) {
                break;
            }
            l--;
            r++;
        }
        return (r - l - 1) / 2 + 1;
    }

    /**
     * 逆序字符串
     *
     * @param s
     * @return
     */
    public String reverse2(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            char c = chars[i];
            chars[i] = chars[chars.length - 1 - i];
            chars[chars.length - 1 - i] = c;
        }
        return new String(chars);
    }
}
