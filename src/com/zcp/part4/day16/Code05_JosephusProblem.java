package com.zcp.part4.day16;

import java.util.HashSet;

// 本题测试链接 : https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
public class Code05_JosephusProblem {

    /**
     * 思路：
     * 递归函数
     *
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining3(int n, int m) {
        // 一共1个数 且当前编号为0的这个点  在总个数为n的情况（初始状态）下对应的编号
        return getNum(n, m, 0, 1);
    }

    /**
     * 返回在 一共i个数 且当前编号为num的点 在总个数为n的情况下对应的编号
     * @param n
     * @param m
     * @param num 当前位置的报数
     * @param i 列表中有i个数
     * @return
     */
    private int getNum(int n, int m, int num, int i) {
        if (i == n) {
            return num;
        }
        return getNum(n, m, (num + m) % (i + 1), i + 1);
    }



    /**
     * 思路：
     * 模拟删除过程
     *
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining(int n, int m) {
        if (n < 0) {
            return -1;
        }
        int remain = n;
        int index = 0;
        int[] arr = new int[n];
        int step = 1;
        while (remain > 1) {
            while (step < m) {
                do {
                    index = (index + 1) % n;
                } while (arr[index] == -1);
                step++;
            }
            step = 0;
            arr[index] = -1;
            remain--;
        }
        while (arr[index] == -1) {
            index = (index + 1) % n;
        }
        return index;
    }

    public static void main(String[] args) {
        new Code05_JosephusProblem().lastRemaining(5, 3);
    }


    // 提交直接通过
    // 给定的编号是0~n-1的情况下，数到m就杀
    // 返回谁会活？
    public int lastRemaining1(int n, int m) {
        return getLive(n, m) - 1;
    }

    // 课上题目的设定是，给定的编号是1~n的情况下，数到m就杀
    // 返回谁会活？
    public static int getLive(int n, int m) {
        if (n == 1) {
            return 1;
        }
        return (getLive(n - 1, m) + m - 1) % n + 1;
    }

    // 提交直接通过
    // 给定的编号是0~n-1的情况下，数到m就杀
    // 返回谁会活？
    // 这个版本是迭代版
    public int lastRemaining2(int n, int m) {
        int ans = 1;
        int r = 1;
        while (r <= n) {
            ans = (ans + m - 1) % (r++) + 1;
        }
        return ans - 1;
    }

    // 以下的code针对单链表，不要提交
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node josephusKill1(Node head, int m) {
        if (head == null || head.next == head || m < 1) {
            return head;
        }
        Node last = head;
        while (last.next != head) {
            last = last.next;
        }
        int count = 0;
        while (head != last) {
            if (++count == m) {
                last.next = head.next;
                count = 0;
            } else {
                last = last.next;
            }
            head = last.next;
        }
        return head;
    }

    public static Node josephusKill2(Node head, int m) {
        if (head == null || head.next == head || m < 1) {
            return head;
        }
        Node cur = head.next;
        int size = 1; // tmp -> list size
        while (cur != head) {
            size++;
            cur = cur.next;
        }
        int live = getLive(size, m); // tmp -> service node position
        while (--live != 0) {
            head = head.next;
        }
        head.next = head;
        return head;
    }

    public static void printCircularList(Node head) {
        if (head == null) {
            return;
        }
        System.out.print("Circular List: " + head.value + " ");
        Node cur = head.next;
        while (cur != head) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println("-> " + head.value);
    }

    public static void main2(String[] args) {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = head1;
        printCircularList(head1);
        head1 = josephusKill1(head1, 3);
        printCircularList(head1);

        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        head2.next.next.next = new Node(4);
        head2.next.next.next.next = new Node(5);
        head2.next.next.next.next.next = head2;
        printCircularList(head2);
        head2 = josephusKill2(head2, 3);
        printCircularList(head2);

    }

}