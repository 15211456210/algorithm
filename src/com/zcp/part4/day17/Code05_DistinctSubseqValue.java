package com.zcp.part4.day17;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

// 本题测试链接 : https://leetcode.com/problems/distinct-subsequences-ii/
public class Code05_DistinctSubseqValue {


    /**
     * 思路：
     * 想象出一个all集合，里面装着所有子序列的集合
     * 一开始all中之一一个 “” 元素
     * 如果字符串不存在重复值的情况，每遍历到一个元素i都往all集合中每个元素后面添加字符s[i]形成新的集合temp，然后将temp集合合并到all集合中
     * <p>
     * 如果遇到重复的元素，需要在上一步的基础上 再减去 上一个出现s[i]字符时候的理论需要新增的个数（包含重复）
     *
     * @param s
     * @return
     */
    public static int distinctSubseqII3(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }

        char[] chars = s.toCharArray();
        //初始情况有一个“”元素
        int all = 1;
        //count[i] 表示 s[i] - 'a' 上一次遇到时增加的子序列数量(包含重复)
        int[] count = new int[26];
        int m = 1000000000 + 7;
        for (int i = 0; i < chars.length; i++) {
            int addCount = all % m;
            all = (all + addCount) % m;
            if (count[chars[i] - 'a'] != 0) {
                all = (all + m - count[chars[i] - 'a']) % m;
            }
            count[chars[i] - 'a'] = addCount;
        }
        //除去空字符串
        return all - 1;
    }

    public static int distinctSubseqII2(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        char[] chars = s.toCharArray();
        HashSet<String> set = new HashSet<>();
        set.add("");
        HashSet<String> tmp = new HashSet<>();
        for (int i = 0; i < chars.length; i++) {
            Iterator<String> iterator = set.iterator();
            tmp.clear();
            while (iterator.hasNext()) {
                tmp.add(iterator.next() + chars[i]);
            }
            set.addAll(tmp);
        }
        return (set.size() - 1) % (1000000000 + 7);
    }


    public static int distinctSubseqII(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int m = 1000000007;
        char[] str = s.toCharArray();
        int[] count = new int[26];
        int all = 1; // 算空集
        for (char x : str) {
            int add = (all - count[x - 'a'] + m) % m;
            all = (all + add) % m;
            count[x - 'a'] = (count[x - 'a'] + add) % m;
        }
        return all - 1;
    }

    public static int zuo(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int m = 1000000007;
        char[] str = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        int all = 1; // 一个字符也没遍历的时候，有空集
        for (char x : str) {
            int newAdd = all;
//			int curAll = all + newAdd - (map.containsKey(x) ? map.get(x) : 0);
            int curAll = all;
            curAll = (curAll + newAdd) % m;
            curAll = (curAll - (map.containsKey(x) ? map.get(x) : 0) + m) % m;
            all = curAll;
            map.put(x, newAdd);
        }
        return all;
    }

    public static void main(String[] args) {
        String s = "bccaccbaabbc";
        System.out.println(distinctSubseqII2(s) + 1);
        System.out.println(zuo(s));
    }

}
