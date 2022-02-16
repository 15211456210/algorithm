package com.zcp.part4.day02;

import java.util.HashMap;

/**
 * 010.一种消息接收并打印的结构设计
 * 已知一个消息流会不断地吐出整数 1~N，但不一定按照顺序吐出。如果上次打印的数为 i， 那么当 i+1 出现时，请打印 i+1
 * 及其之后接收过的并且连续的所有数，直到 1~N 全部接收 并打印完，请设计这种接收并打印的结构。
 * <p>
 * <p>
 * 思路：
 * 链表 、缓存
 */
public class Code03_ReceiveAndPrintOrderLine {

    public static class Node {
        int num;
        String value;
        Node next;

        public Node(int num, String value) {
            this.num = num;
            this.value = value;
        }
    }

    public static class MessageBox {
        HashMap<Integer, Node> headMap;
        HashMap<Integer, Node> tailMap;
        int curNum = 1;//当前需要等到的消息序列号

        public MessageBox() {
            headMap = new HashMap<>();
            tailMap = new HashMap<>();
        }

        // 消息的编号，info消息的内容, 消息一定从1开始
        public void receive(int num, String info) {
            Node node = new Node(num, info);
            headMap.put(num, node);
            tailMap.put(num, node);
            //检查前面是否有num-1结尾的
            Node pre = null;
            if ((pre = tailMap.get(num - 1)) != null) {
                pre.next = node;
                tailMap.remove(num - 1);
                headMap.remove(num);
            }

            //检查后面是否有num+1的节点
            Node next = null;
            if ((next = headMap.get(num + 1)) != null) {
                node.next = next;
                headMap.remove(num + 1);
                tailMap.remove(num);
            }

            if (num == curNum) {
                //如果当前收到的消息就是当前再等待的消息，则打印
                print();
            }

        }

        private void print() {
            Node node = headMap.get(curNum);
            headMap.remove(curNum);
            Node tail = node;
            while (node != null) {
                tail = node;
                System.out.print(node.value + " ");
                node = node.next;
            }
            System.out.println();
            tailMap.remove(tail.num);
            curNum = tail.num + 1;
        }

    }

    public static void main(String[] args) {
        // MessageBox only receive 1~N
        MessageBox box = new MessageBox();
        // 1....
        System.out.println("这是2来到的时候");
        box.receive(2, "B"); // - 2"
        System.out.println("这是1来到的时候");
        box.receive(1, "A"); // 1 2 -> print, trigger is 1
        box.receive(4, "D"); // - 4
        box.receive(5, "E"); // - 4 5
        box.receive(7, "G"); // - 4 5 - 7
        box.receive(8, "H"); // - 4 5 - 7 8
        box.receive(6, "F"); // - 4 5 6 7 8
        box.receive(3, "C"); // 3 4 5 6 7 8 -> print, trigger is 3
        box.receive(9, "I"); // 9 -> print, trigger is 9
        box.receive(10, "J"); // 10 -> print, trigger is 10
        box.receive(12, "L"); // - 12
        box.receive(13, "M"); // - 12 13
        box.receive(11, "K"); // 11 12 13 -> print, trigger is 11

    }
}
