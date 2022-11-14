package com.zcp.part5.c051to100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZCP
 * @title: c068
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/text-justification/
 * @date 2022/8/29 15:54
 */
public class c068 {

    public List<String> fullJustify(String[] words, int maxWidth) {
        int begin = 0;
        int end = 0;
        int len = 0;
        int gapWidth = 1;
        int gapCnt = 0;
        int bigGapCnt = 0;
        ArrayList ans = new ArrayList<String>();
        StringBuilder line = new StringBuilder();
        while (end < words.length) {

            while (end < words.length && len + words[end].length() + end - begin <= maxWidth) {
                len += words[end].length();
                end++;
            }
            if (end < words.length) {
                gapCnt = Math.max(1, end - begin - 1);
                gapWidth = (maxWidth - len) / gapCnt;
                bigGapCnt = (maxWidth - len) - gapWidth * gapCnt;
            } else {
                gapWidth = 1;
            }
            while (begin < end) {
                line.append(words[begin++]);
                if (begin <= end - 1) {
                    if (bigGapCnt > 0) {
                        line.append(getGap(gapWidth + 1));
                        bigGapCnt--;
                    } else {
                        line.append(getGap(gapWidth));
                    }
                }
            }

            line.append(getGap(maxWidth - line.length()));


            ans.add(line.toString());
            len = 0;
            line.setLength(0);
        }
        return ans;
    }

    public String getGap(int width) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < width; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new c068().fullJustify(new String[]{"What", "must", "be", "acknowledgment", "shall", "be"}, 16));
    }
}
