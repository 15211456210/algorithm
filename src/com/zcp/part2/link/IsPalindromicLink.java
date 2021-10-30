package com.zcp.part2.link;

import com.zcp.part2.structure.ReverseSingleLink;

import java.util.Random;
import java.util.Stack;

/**
 * 是否是回文链表判断
 * 1-20-3-20-1 是回文链表  true
 * 1-2-2-1 是回文链表 true
 * 1-2-3-3-1 不是回文链表 false
 */
public class IsPalindromicLink {
    public static class Node {
        int val;
        Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }


    public static boolean solution(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node sp = head;
        Node qp = head;
        while (qp.next != null && qp.next.next != null) {
            sp = sp.next;
            qp = qp.next.next;
        }
        Node mid = sp.next;
        Node pre = sp;
        sp = mid;
        while (sp != null) {
            Node next = sp.next;
            sp.next = pre;
            pre = sp;
            sp = next;
        }
        boolean isPalindromic = true;
        //pre来到了最后的位置
        Node tail = pre;
        Node left = head;
        while (left != mid) {
            if (left.val != tail.val) {
                isPalindromic = false;
                break;
            }
            left = left.next;
            tail = tail.next;
        }
        //pre 最后的位置
        Node cur = pre.next;
        pre.next = null;
        Node stopNode = mid.next;
        //将链表还原回原始的样子
        while (cur != stopNode) {
            Node next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return isPalindromic;
    }

    public static boolean solution1(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        while (head != null) {
            if (head.val != stack.pop().val) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static void main(String[] args) {
        int len = 50;
        int range = 500;
        int testTime = 1000000;//测试次数
        int count = 0;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            Node link = generateRandomLink((int) Math.random() * len, range);
            Node link2 = copyLink(link);
            boolean r1 = solution(link);
            boolean r2 = solution1(link2);
            if (r1 == true) {
                count++;
            }
            if (r1 != r2) {
                System.out.println("出现错误");
                break;
            }
        }
        System.out.println("测试结束");
        System.out.println("测试回文数为：" + count);
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

    /**
     * 拷贝一份链表
     *
     * @param link
     * @return
     */
    private static Node copyLink(Node link) {
        if (link == null) {
            return null;
        }
        Node head = new Node(link.val, null);
        Node cur = head;
        link = link.next;
        while (link != null) {
            cur.next = new Node(link.val, null);
            cur = cur.next;
            link = link.next;
        }
        return head;
    }

}
