package com.zcp.part5.c401to450;

import java.util.Arrays;

/**
 * @author ZCP
 * @title: C406
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/queue-reconstruction-by-height/description/
 * @date 2022/11/21 15:10
 */
public class C406 {

    class Node {
        int height;
        int count;

        public Node(int h, int c) {
            height = h;
            count = c;
        }
    }

    public int[][] reconstructQueue(int[][] people) {
        int n = people.length;
        Node[] ps = new Node[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new Node(people[i][0], people[i][1]);
        }

        Arrays.sort(ps, (n1, n2) -> {
            return n1.count != n2.count ? n1.count - n2.count : n1.height - n2.height;
        });

        for (int i = 0; i < n; i++) {
            int h = ps[i].height;
            int c = ps[i].count;
            int find = 0;
            int idx = 0;
            do {
                if (ps[idx].height >= h) {
                    find++;
                }
                idx++;
            } while (find <= c);

            move(ps, i, idx - 1);
        }

        int[][] ans = new int[n][2];

        for (int i = 0; i < n; i++) {
            ans[i][0] = ps[i].height;
            ans[i][1] = ps[i].count;
        }
        return ans;

    }

    // ps[j...i]往后移动一位
    public void move(Node[] ps, int i, int j) {
        Node tmp = ps[i];
        for (int k = i; k > j; k--) {
            ps[k] = ps[k - 1];
        }
        ps[j] = tmp;
        ;
    }
}
