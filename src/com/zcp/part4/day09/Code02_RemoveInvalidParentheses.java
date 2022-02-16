package com.zcp.part4.day09;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// 测试链接 : https://leetcode.com/problems/remove-invalid-parentheses/
public class Code02_RemoveInvalidParentheses {


    /**
     * 思路：
     * 动态规划
     *
     * @param s
     * @return
     */
    public List<String> removeInvalidParentheses2(String s) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }
        //创建一个保存结果列表的对象，其中包含结果字符串列表，最长长度信息
        Info info = new Info();
        process2(s.toCharArray(), 0, 0, "", info);
        return new ArrayList<>(info.set);
    }

    public class Info {
        HashSet set = new HashSet<String>();
        int maxLen = 0;

        /**
         * 添加方法
         * @param s
         */
        public void add(String s) {
            //如果 新添加的字符串长度大于当前set中最长字符串长度，先清空set中的数据，在做添加
            //如果 新添加的字符串长度等于当前set中最长字符串长度，直接添加
            //如果 新添加的字符串长度小于当前set中最长字符串长度，不添加
            if (s.length() > maxLen) {
                set.clear();
                set.add(s);
                maxLen = s.length();
            } else if (s.length() == maxLen) {
                set.add(s);
            }
        }
    }

    /**
     *
     * @param s
     * @param index 当前所在s字符数组的位置
     * @param count 前面还有多少个 '(' 没有配对
     * @param path 前面选择的字符串
     * @param info 结果信息
     */
    public void process2(char[] s, int index, int count, String path, Info info) {
        if (index == s.length) {
            // 到最后位置如果count！=0说明 path不合法
            if (count != 0) {
                return;
            }
            //path合法，尝试添加到结果信息Info中去
            info.add(path);
            return;
        }
        //如果是‘（’可以选择，也可以不选择
        //如果是‘）’需要分情况：
        //情况1：count=0，前面所有（都已经匹配上了，那么当前位置不能选择 ‘）’，否则就会不合法
        //情况2：count>0，前面还有‘（’没有匹配上，可以选择‘）’，也可以不选择交给后面去处理
        if (s[index] == '(' || s[index] == ')') {
            if (s[index] == '(') {
                process2(s, index + 1, count, path, info);
                process2(s, index + 1, count + 1, path + "(", info);
            } else {
                if (count > 0) {
                    process2(s, index + 1, count, path, info);
                    process2(s, index + 1, count - 1, path + ")", info);
                } else {
                    process2(s, index + 1, count, path, info);
                }
            }
        } else {
            //如果是其他字符，直接跳过去处理下一个位置
            process2(s, index + 1, count, path + String.valueOf(s[index]), info);
        }
    }


    // 来自leetcode投票第一的答案，实现非常好，我们来赏析一下
    public static List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    // modifyIndex <= checkIndex
    // 只查s[checkIndex....]的部分，因为之前的一定已经调整对了
    // 但是之前的部分是怎么调整对的，调整到了哪？就是modifyIndex
    // 比如：
    // ( ( ) ( ) ) ) ...
    // 0 1 2 3 4 5 6
    // 一开始当然checkIndex = 0，modifyIndex = 0
    // 当查到6的时候，发现不对了，
    // 然后可以去掉2位置、4位置的 )，都可以
    // 如果去掉2位置的 ), 那么下一步就是
    // ( ( ( ) ) ) ...
    // 0 1 2 3 4 5 6
    // checkIndex = 6 ，modifyIndex = 2
    // 如果去掉4位置的 ), 那么下一步就是
    // ( ( ) ( ) ) ...
    // 0 1 2 3 4 5 6
    // checkIndex = 6 ，modifyIndex = 4
    // 也就是说，
    // checkIndex和modifyIndex，分别表示查的开始 和 调的开始，之前的都不用管了  par  (  )
    public static void remove(String s, List<String> ans, int checkIndex, int deleteIndex, char[] par) {
        for (int count = 0, i = checkIndex; i < s.length(); i++) {
            if (s.charAt(i) == par[0]) {
                count++;
            }
            if (s.charAt(i) == par[1]) {
                count--;
            }
            // i check计数<0的第一个位置
            if (count < 0) {
                for (int j = deleteIndex; j <= i; ++j) {
                    // 比如
                    if (s.charAt(j) == par[1] && (j == deleteIndex || s.charAt(j - 1) != par[1])) {
                        remove(
                                s.substring(0, j) + s.substring(j + 1, s.length()),
                                ans, i, j, par);
                    }
                }
                return;
            }
        }
        String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') {
            remove(reversed, ans, 0, 0, new char[]{')', '('});
        } else {
            ans.add(reversed);
        }
    }

}
