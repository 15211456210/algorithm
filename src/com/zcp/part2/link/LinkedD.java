package com.zcp.part2.link;

import com.zcp.part2.structure.ReverseDoubleLink;
import com.zcp.part2.structure.ReverseSingleLink;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.util.ArrayList;

/**
 * 一些常见的链表边界题
 */
public class LinkedD {

    public static class Node {
        int val;
        Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 1）输入链表头节点，奇数长度返回中点，偶数长度返回上中点
     *
     * @param head
     * @return
     */
    public static Node solution1(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node sp = head;
        Node qp = head;
        while (qp.next != null && qp.next.next != null) {
            sp = sp.next;
            qp = qp.next.next;
        }
        return sp;
    }

    /**
     * 对照函数
     *
     * @param head
     * @return
     */
    public static Node solution1_1(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        ArrayList<Node> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        if (list.size() % 2 == 0) {
            return list.get(list.size() / 2 - 1);
        } else {
            return list.get(list.size() / 2);
        }
    }


    public static void main(String[] args) {
        int range = 500;
        int testTime = 1000000;//测试次数
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * 50);
            Node link = generateRandomLink(len, range);
            Node r1 = solution1(link);
            Node r2 = solution1_1(link);
            if (r1 != r2) {
                System.out.println("出现错误");
                break;
            }
        }
        System.out.println("测试结束");
    }

    /**
     * 根据长度 范围生成随机链表
     *
     * @param len
     * @param range
     * @return
     */
    private static Node generateRandomLink(int len, int range) {
        int l = (int) (Math.random() * len) + 1;
        int val = (int) (Math.random() * range) - (int) (Math.random() * range);
        Node last = new Node(val, null);

        for (int i = 0; i < l; i++) {
            int newVal = (int) (Math.random() * range) - (int) (Math.random() * range);
            last = new Node(newVal, last);
        }
        return last;
    }


}
