package com.zcp.part2.structure;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 删除链表中指定值的节点
 */
public class RemoveNumFromLink {

    public static class Node {
        int val;
        Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 删除链表中值为k的节点
     *
     * @param head
     * @return
     */
    public static Node solution(Node head, int k) {
        while (head != null && head.val == k) {
            head = head.next;
        }
        if(head == null){
            return head;
        }
        Node ans = head;
        Node pre = head;
        head = head.next;
        while (head != null) {
            if (head.val == k) {
                pre.next = head.next;
            } else {
                pre = pre.next;
            }
            head = head.next;
        }
        return ans;
    }

    /**
     * 对照函数
     *
     * @param head
     * @param k
     * @return
     */
    public static Node solution2(Node head, int k) {
        ArrayList<Integer> arr = new ArrayList<>();
        while (head != null) {
            if (head.val != k) {
                arr.add(head.val);
            }
            head = head.next;
        }

        if (arr.size() > 0) {
            Node cur = null;
            Collections.reverse(arr);
            for (Integer integer : arr) {
                cur = new Node(integer, cur);
            }
            return cur;
        } else {
            return null;
        }

    }


    public static void main(String[] args) {
        int len = 50;
        int range = 500;
        int testTime = 100000;//测试次数
        int k = 5;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            Node link = generateRandomLink(len, range,k);
            Node link2 = copyLink(link);
            Node r1 = solution(link,k);
            Node r2 = solution2(link2,k);
            if (!check(r1, r2)) {
                System.out.println("出现错误");
                break;
            }
        }
        System.out.println("测试结束");

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
    
    private static Node generateRandomLink(int len, int range, int k) {
        int l = (int) (Math.random() * len) + 1;
        int val = Math.random() < 0.5 ? k : (int) (Math.random() * range) - (int) (Math.random() * range);
        Node cur = new Node(val, null);
        Node head = cur;
        for (int i = 0; i < l; i++) {
            val = Math.random() < 0.5 ? k : (int) (Math.random() * range) - (int) (Math.random() * range);
            Node next = new Node(val, null);
            cur.next = next;
            cur = next;
        }
        return head;
    }

}
