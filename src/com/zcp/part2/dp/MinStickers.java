package com.zcp.part2.dp;


import java.util.HashMap;
import java.util.HashSet;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/20
 * @description：691.[[多少张贴纸可以贴出给定字符串|贴纸拼词]]https://leetcode-cn.com/problems/stickers-to-spell-word/
 * @version:
 */
public class MinStickers {


    /**
     * @param stickers
     * @param target
     * @return
     */
    public int solution1(String[] stickers, String target) {
        if (target == null || target.length() < 1 || stickers == null || stickers.length < 1) {
            return -1;
        }
        //stickers拆分成一个个字母存放在数组里
        int[][] helps = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            for (char c : stickers[i].toCharArray()) {
                helps[i][c - 'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        return process1(helps, target,dp);
    }

    /**
     * 拼成target目标最少需要多少张纸条
     *
     * @param helps
     * @param target
     * @return
     */
    public int process1(int[][] helps, String target, HashMap<String,Integer> dp) {
        if (dp.containsKey(target)){
            return dp.get(target);
        }
        if (target.length() == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int[] tar = new int[26];
        for (char c : target.toCharArray()) {
            tar[c - 'a']++;
        }
        for (int[] help : helps) {
            if (help[target.charAt(0) - 'a'] > 0) {
                //剪枝，只计算包含目标字符第一个位置的贴纸
                String remain = subStrCount(tar, help);//减去对应字母次数
                int ans = process1(helps, remain,dp);
                if (ans != -1) {
                    //-1说明无解
                    min = Math.min(1 + ans, min);
                }
            }
        }
        min = min == Integer.MAX_VALUE ? -1 : min;
        dp.put(target,min);
        return min;
    }

    private String subStrCount(int[] target, int[] help) {
        int[] ans = new int[26];
        for (int i = 0; i < target.length; i++) {
            ans[i] = target[i] - help[i] < 0 ? 0 : target[i] - help[i];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[i]; j++) {
                sb.append((char) ('a' + i));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
//["heavy","claim","seven","set","had","it","dead","jump","design","question","sugar","dress","any","special","ground","huge","use","busy","prove","there","lone","window","trip","also","hot","choose","tie","several","be","that","corn","after","excite","insect","cat","cook","glad","like","wont","gray","especially","level","when","cover","ocean","try","clean","property","root","wing"]
//        "travelbell"
        MinStickers minStickers = new MinStickers();
        String[] stickers = {"heavy","claim","seven","set","had","it","dead","jump","design","question","sugar","dress","any","special","ground","huge","use","busy","prove","there","lone","window","trip","also","hot","choose","tie","several","be","that","corn","after","excite","insect","cat","cook","glad","like","wont","gray","especially","level","when","cover","ocean","try","clean","property","root","wing"};
        System.out.println(minStickers.solution1(stickers, "travelbell"));
    }
}
