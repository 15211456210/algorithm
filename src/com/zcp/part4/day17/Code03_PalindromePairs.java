package com.zcp.part4.day17;

import java.util.*;

// 测试链接 : https://leetcode.com/problems/palindrome-pairs/
public class Code03_PalindromePairs {


    /**
     * 思路：
     * 将字符预先存入set，之后遍历每一个字符串需要寻找的缺失串
     *
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairs3(String[] words) {
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

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(new Code03_PalindromePairs().manacher("abccabacsd")));
//        System.out.println(Arrays.toString(new Code03_PalindromePairs().manacher("abac")));//#a#b#a#c#
        new Code03_PalindromePairs().palindromePairs3(new String[]{"abc", ""});
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

    /**
     * 判断 s[l...r]是否是回文串
     *
     * @param s
     * @param l
     * @param r
     * @return
     */
    private boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 思路：
     * 普遍解
     *
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairs2(String[] words) {
        List<List<Integer>> list = new ArrayList<>();
        int size = words.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    continue;
                }
                if (isPalindrome(words[i] + words[j])) {
                    list.add(Arrays.asList(i, j));
                }
            }
        }
        return list;
    }


    /**
     * 判断是否是回文串
     *
     * @param s
     * @return
     */
    private boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;

        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) {
                return false;
            }
        }
        return true;
    }


    public static List<List<Integer>> palindromePairs(String[] words) {
        HashMap<String, Integer> wordset = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            wordset.put(words[i], i);
        }
        List<List<Integer>> res = new ArrayList<>();
        //{ [6,23] 、 [7,13] }
        for (int i = 0; i < words.length; i++) {
            // i words[i]
            // findAll(字符串，在i位置，wordset) 返回所有生成的结果返回
            res.addAll(findAll(words[i], i, wordset));
        }
        return res;
    }

    public static List<List<Integer>> findAll(String word, int index, HashMap<String, Integer> words) {
        List<List<Integer>> res = new ArrayList<>();
        String reverse = reverse(word);
        Integer rest = words.get("");
        if (rest != null && rest != index && word.equals(reverse)) {
            addRecord(res, rest, index);
            addRecord(res, index, rest);
        }
        int[] rs = manacherss(word);
        int mid = rs.length >> 1;
        for (int i = 1; i < mid; i++) {
            if (i - rs[i] == -1) {
                rest = words.get(reverse.substring(0, mid - i));
                if (rest != null && rest != index) {
                    addRecord(res, rest, index);
                }
            }
        }
        for (int i = mid + 1; i < rs.length; i++) {
            if (i + rs[i] == rs.length) {
                rest = words.get(reverse.substring((mid << 1) - i));
                if (rest != null && rest != index) {
                    addRecord(res, index, rest);
                }
            }
        }
        return res;
    }

    public static void addRecord(List<List<Integer>> res, int left, int right) {
        List<Integer> newr = new ArrayList<>();
        newr.add(left);
        newr.add(right);
        res.add(newr);
    }

    public static int[] manacherss(String word) {
        char[] mchs = manachercs(word);
        int[] rs = new int[mchs.length];
        int center = -1;
        int pr = -1;
        for (int i = 0; i != mchs.length; i++) {
            rs[i] = pr > i ? Math.min(rs[(center << 1) - i], pr - i) : 1;
            while (i + rs[i] < mchs.length && i - rs[i] > -1) {
                if (mchs[i + rs[i]] != mchs[i - rs[i]]) {
                    break;
                }
                rs[i]++;
            }
            if (i + rs[i] > pr) {
                pr = i + rs[i];
                center = i;
            }
        }
        return rs;
    }

    public static char[] manachercs(String word) {
        char[] chs = word.toCharArray();
        char[] mchs = new char[chs.length * 2 + 1];
        int index = 0;
        for (int i = 0; i != mchs.length; i++) {
            mchs[i] = (i & 1) == 0 ? '#' : chs[index++];
        }
        return mchs;
    }

    public static String reverse(String str) {
        char[] chs = str.toCharArray();
        int l = 0;
        int r = chs.length - 1;
        while (l < r) {
            char tmp = chs[l];
            chs[l++] = chs[r];
            chs[r--] = tmp;
        }
        return String.valueOf(chs);
    }

}