package com.zcp.part2.forceRecursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/17
 * @description：字符串所有子序列
 * @version:
 */
public class SubString {

    public static List<String> solution(String s) {
        ArrayList<String> list = new ArrayList<>();
        if (s == null || s.length() < 1) {
            return list;
        }
        char[] chars = s.toCharArray();
        process(chars, 0, "", list);
        return list;
    }

    /**
     * 返回从index下标开始 字符串所有子序列
     *
     * @param chars 完整的字符串
     * @param index 当前下标
     * @param path  当前的字符串
     */
    public static void process(char[] chars, int index, String path, List<String> ans) {
        if (index == chars.length) {
            ans.add(path);
            return;
        }
        //不选择chars[index]这个字符
        process(chars, index + 1, path, ans);
        //选择chars[index]这个字符
        process(chars, index + 1, path + chars[index], ans);
    }


    public static void main(String[] args) {
        List<String> list = solution("abbc");
        list.forEach(item -> {
            System.out.println(item);
        });

    }

}
