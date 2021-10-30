package com.zcp.part2.structure;

import com.sun.org.apache.xerces.internal.impl.dv.dtd.NOTATIONDatatypeValidator;

/**
 * 双向链表反转
 * 迭代、递归
 */
public class ReverseDoubleLink {
    public static class Node {
        int val;
        Node pre;
        Node next;

        public Node(int val, Node pre, Node next) {
            this.val = val;
            this.pre = pre;
            this.next = next;
        }
    }

    /**
     * 双向链表反转 迭代实现
     *
     * @param head
     * @return
     */
    public static Node solution1(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node pre = null;
        Node cur = head;
        Node next = head;

        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            cur.pre = next;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    /**
     * 递归实现双向链表反转
     *
     * @param head
     * @return
     */
    public static Node solution2(Node head) {
        if (head.next == null) {
            head.next = head.pre;
            head.pre = null;
            return head;
        }
        Node last = solution2(head.next);
        Node next = head.next;
        head.next = head.pre;
        head.pre = next;
        return last;
    }

    public static void main(String[] args) {
        int len = 50;
        int range = 500;
        int testTime = 1000000;//测试次数
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            Node link = generateRandomLink(len, range);
            Node link2 = copyLink(link);
            Node r1 = solution1(link);
            Node r2 = solution2(link2);
            if (!check(r1, r2)) {
                System.out.println("出现错误");
                break;
            }
        }
        System.out.println("测试结束");
    }

    /**
     * 校验两个双向链表是否一致
     *
     * @param r1
     * @param r2
     * @return
     */
    private static boolean check(Node r1, Node r2) {
        while (r1 != null && r2 != null) {
            if (r1.val != r2.val) {
                return false;
            }
            if (r1.pre != null) {
                if (r2.pre == null || r2.pre.val != r1.pre.val) {
                    return false;
                }
            } else {
                if (r2.pre != null) {
                    return false;
                }
            }
            if (r1.next != null) {
                if (r2.next == null || r2.next.val != r1.next.val) {
                    return false;
                }
            } else {
                if (r2.next != null) {
                    return false;
                }
            }
            r1 = r1.next;
            r2 = r2.next;
        }
        return true;
    }

    /**
     * 拷贝双向链表
     *
     * @param link
     * @return
     */
    private static Node copyLink(Node link) {
        Node head = new Node(link.val, null, null);
        Node cur = head;
        link = link.next;
        while (link != null) {
            cur.next = new Node(link.val, cur, null);
            cur = cur.next;
            link = link.next;
        }
        return head;
    }

    /**
     * 根据长度，范围生成随机双向链表
     *
     * @param len
     * @param range
     * @return
     */
    private static Node generateRandomLink(int len, int range) {
        int l = (int) (Math.random() * len) + 1;
        int val = (int) (Math.random() * range) - (int) (Math.random() * range);
        Node cur = new Node(val, null, null);
        Node head = cur;
        for (int i = 0; i < l; i++) {
            val = (int) (Math.random() * range) - (int) (Math.random() * range);
            Node next = new Node(val, cur, null);
            cur.next = next;
            cur = next;
        }
        return head;
    }


}
