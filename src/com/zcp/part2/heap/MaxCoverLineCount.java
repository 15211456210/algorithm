package com.zcp.part2.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * 最大线段重合数
 * 提供N个线段[x,y],x为线段开始位置，y为线段结束位置。线段重合长度>=1
 * 求线段重合最大的数
 */
public class MaxCoverLineCount {

    public static class Line {
        int start;
        int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int solution(int[][] lines) {
        if (lines == null || lines.length < 1) {
            return 0;
        }
        List<Line> lineList = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            lineList.add(new Line(lines[i][0], lines[i][1]));
        }
        //根据开始位置排序
        lineList.sort((o1, o2) -> o1.start - o2.start);
        MyHeapD.MyHeap myHeap = new MyHeapD.MyHeap();
        int max = 0;
        for (Line line : lineList) {
            while (!myHeap.isEmpty() && line.start >= myHeap.peek()) {
                myHeap.poll();
            }
            myHeap.add(line.end);
            max = Math.max(max, myHeap.size);
        }
        return max;
    }


    public static void main(String[] args) {
        int[][] lines = {{1,4},{2,3},{3,4},{2,4}};
        System.out.println(solution(lines));
    }

}
