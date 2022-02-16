package com.zcp.part4.day05;

// 如果一个节点X，它左树结构和右树结构完全一样，那么我们说以X为头的子树是相等子树
// 给定一棵二叉树的头节点head，返回head整棵树上有多少棵相等子树
public class Code02_LeftRightSameTreeNumber {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }


    public static class Info2 {
        int ans;
        String hashStr;

        public Info2(int ans, String hashStr) {
            this.ans = ans;
            this.hashStr = hashStr;
        }
    }

    /**
     * 思路：
     * 二叉树递归套路+序列化+hash
     *
     * @param head
     * @return
     */
    public static int sameNumber4(Node head) {
        if (head == null) {
            return 0;
        }
        //实例化hash函数对象
        Hash hash = new Hash("SHA-256");
        return process4(head, hash).ans;
    }

    private static Info2 process4(Node head, Hash hash) {
        if (head == null) {
            //如果是空树，返回“#，”对应的hashcode
            return new Info2(0, hash.hashCode("#,"));
        }
        Info2 left = process4(head.left, hash);
        Info2 right = process4(head.right, hash);
        //新的hash由 左树hash+当前节点value+右树hash，这样做的好出，可以通过hash算法将字符串比较的长度固定，从而降低了时间复杂度（极小的概率会出现hash碰撞，工程上可以考虑一下）
        return new Info2(left.ans + right.ans + (left.hashStr.equals(right.hashStr) ? 1 : 0), hash.hashCode(left.hashStr + head.value + "," + right.hashStr));
    }


    /**
     * 思路：
     * 二叉树递归套路
     *
     * @param head
     * @return
     */
    public static int sameNumber3(Node head) {
        if (head == null) {
            return 0;
        }
        return process3(head);
    }

    /**
     * 以head为头节点的树有几棵相等的二叉子树
     *
     * @param head
     * @return
     */
    private static int process3(Node head) {
        if (head == null) {
            return 0;
        }
        return process3(head.left) + process3(head.right) + (same3(head.left, head.right) ? 1 : 0);
    }

    /**
     * 判断两个节点 为根节点的树 是否相等
     *
     * @param left
     * @param right
     * @return
     */
    private static boolean same3(Node left, Node right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null ^ right == null) {
            return false;
        }
        return left.value == right.value && same3(left.left, right.left) && same3(left.right, right.right);
    }


    // 时间复杂度O(N * logN)
    public static int sameNumber1(Node head) {
        if (head == null) {
            return 0;
        }
        return sameNumber1(head.left) + sameNumber2(head.right) + (same(head.left, head.right) ? 1 : 0);
    }

    public static boolean same(Node h1, Node h2) {
        if (h1 == null ^ h2 == null) {
            return false;
        }
        if (h1 == null && h2 == null) {
            return true;
        }
        // 两个都不为空
        return h1.value == h2.value && same(h1.left, h2.left) && same(h1.right, h2.right);
    }

    // 时间复杂度O(N)
    public static int sameNumber2(Node head) {
        String algorithm = "SHA-256";
        Hash hash = new Hash(algorithm);
        return process(head, hash).ans;
    }

    public static class Info {
        public int ans;
        public String str;

        public Info(int a, String s) {
            ans = a;
            str = s;
        }
    }

    public static Info process(Node head, Hash hash) {
        if (head == null) {
            return new Info(0, hash.hashCode("#,"));
        }
        Info l = process(head.left, hash);
        Info r = process(head.right, hash);
        int ans = (l.str.equals(r.str) ? 1 : 0) + l.ans + r.ans;
        String str = hash.hashCode(String.valueOf(head.value) + "," + l.str + r.str);
        return new Info(ans, str);
    }

    public static Node randomBinaryTree(int restLevel, int maxValue) {
        if (restLevel == 0) {
            return null;
        }
        Node head = Math.random() < 0.2 ? null : new Node((int) (Math.random() * maxValue));
        if (head != null) {
            head.left = randomBinaryTree(restLevel - 1, maxValue);
            head.right = randomBinaryTree(restLevel - 1, maxValue);
        }
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 8;
        int maxValue = 4;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            Node head = randomBinaryTree(maxLevel, maxValue);
            int ans1 = sameNumber1(head);
            int ans2 = sameNumber2(head);
            int ans3 = sameNumber3(head);
            int ans4 = sameNumber4(head);
            if (ans1 != ans2 || ans2 != ans3 || ans1 != ans4) {
                System.out.println("出错了！");
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println(ans4);
            }
        }
        System.out.println("测试结束");

    }

}
