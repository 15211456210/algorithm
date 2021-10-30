package com.zcp.part2.structure;

/**
 * 单链表反转
 * 迭代、递归
 */
public class ReverseSingleLink {

    public static class Node {
        int val;
        Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 迭代实现 单链表反转
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
        while (cur != null) {
            Node next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    /**
     * 递归实现 单链表反转
     *
     * @param head
     * @return
     */
    public static Node solution2(Node head, Node pre) {
        if (head.next == null) {
            head.next = pre;
            return head;
        }
        Node last = solution2(head.next, head);
        head.next = pre;
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
            Node r2 = solution2(link2, null);
            if (!check(r1, r2)) {
                System.out.println("出现错误");
                break;
            }
        }
        System.out.println("测试结束");

    }

    private static boolean check(Node r1, Node r2) {
        while (r1 != null && r2 != null) {
            if (r1.val != r2.val) {
                return false;
            }
            r1 = r1.next;
            r2 = r2.next;
        }
        return r1 == null && r2 == null;
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
