package com.zcp.part5.c351to400;

import com.zcp.common.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author ZCP
 * @title: C382
 * @projectName algorithm
 * @description: https://leetcode.com/problems/linked-list-random-node/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 * @date 2022/10/31 10:41
 */
public class C382 {

    class Solution {

        private List<ListNode> list;
        private int size;

        public Solution(ListNode head) {
            list = new ArrayList<>();
            while (head != null) {
                list.add(head);
                head = head.next;
            }
            size = list.size();
        }

        public int getRandom() {
            return list.get((int) (Math.random() * size)).val;
        }
    }


}
