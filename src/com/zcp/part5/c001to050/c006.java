package com.zcp.part5.c001to050;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ZCP
 * @title: c006
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/zigzag-conversion/submissions/
 * @date 2022/8/25 13:27
 */
public class c006 {
    public String convert(String s, int numRows) {
        if (s.length() <= numRows || numRows == 1) {
            return s;
        }
        Queue<Integer> queue = new LinkedList<>();
        int location = 0;
        int size = 0;
        while (location < s.length()) {
            queue.offer(location);
            ++size;
            location += 2 * numRows - 2;
        }
        queue.offer(location);
        ++size;

        int index = 0;
        StringBuffer r = new StringBuffer();
        while (!queue.isEmpty() && r.length() < s.length()) {
            Integer cur = queue.poll();
            int round = index / size;
            if (round > 0) {
                int lIndex = cur - round;
                int rIndex = cur + round;
                if (lIndex > 0 && lIndex < s.length() && lIndex > cur - numRows) {
                    r.append(s.charAt(lIndex));
                }
                if (rIndex < cur + numRows - 1 && rIndex < s.length()) {
                    r.append(s.charAt(rIndex));
                }
            } else {
                if (cur < s.length()) {
                    r.append(s.charAt(cur));
                }
            }
            queue.offer(cur);//重新入队
            ++index;
        }
        return r.toString();
    }
}
