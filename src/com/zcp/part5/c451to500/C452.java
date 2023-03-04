package com.zcp.part5.c451to500;

import java.util.Arrays;

/**
 * @author ZCP
 * @title: C452
 * @projectName algorithm
 * @description: https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
 * @date 2023/2/3 10:11
 */
public class C452 {

    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (o1, o2) -> o1[0] != o2[0] ? (o1[0] < o2[0] ? -1 : 1) :(o1[1] != o2[1] ?  (o1[1] < o2[1] ? -1 : 1) : 0));
        int len = points.length;
        int start = -1,end = -1;
        int ans = 0;
        for(int i = 0;i<len;i++){
            if(start == -1){
                start = points[i][0];
                end = points[i][1];
            }else{
                int[] cover = cover(start,end,points[i][0],points[i][1]);
                if(cover[0] == -1){
                    ans++;
                    start = points[i][0];
                    end = points[i][1];
                }else{
                    start = cover[0];
                    end = cover[1];
                }
            }
        }
        return ans + (start == -1? 0 : 1);

    }

    private int[] cover(int x1,int x2,int x3,int x4){
        int[] ans = new int[2];
        ans[0] = x2 < x3? -1 : x3;
        ans[1] = x2 < x3 ?-1 : Math.min(x2,x4);
        return ans;
    }

    public static void main(String[] args) {
        // [[9,12],[1,10],[4,11],[8,12],[3,9],[6,9],[6,7]]
        System.out.println(new C452().findMinArrowShots(new int[][]{{9, 12}, {1, 10}, {4, 11}, {8, 12}, {3, 9}, {6, 9}, {6, 7}}));
    }
}
