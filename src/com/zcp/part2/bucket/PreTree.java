package com.zcp.part2.bucket;

import java.util.HashMap;

/**
 * 前缀树
 * tire
 */
public class PreTree {

    public static class Node {
        int pass;
        int end;
        Node[] nexts;

        public Node(int pass, int end) {
            this.pass = pass;
            this.end = end;
            this.nexts = new Node[26];//代表26个英文小写字母
        }
    }

    /**
     * 基于数组实现的前缀树
     */
    public static class ArrayPreTree {
        private Node root;

        public ArrayPreTree() {
            root = new Node(0, 0);
        }

        /**
         * 将字符串插入树
         *
         * @param str
         */
        public void insert(String str) {
            if (str == null) {
                return;
            }
            char[] chars = str.toCharArray();
            Node curNode = root;
            for (int i = 0; i < chars.length; i++) {
                int offset = chars[i] - 'a';
                if (curNode.nexts[offset] == null) {
                    curNode.nexts[offset] = new Node(0, 0);
                }
                curNode.nexts[offset].pass++;
                curNode = curNode.nexts[offset];
            }
            curNode.end++;
        }

        /**
         * 返回树中指定字符串出现的次数
         *
         * @return
         */
        public int search(String str) {
            if (str == null) {
                return 0;
            }
            char[] chars = str.toCharArray();
            Node curNode = root;
            for (int i = 0; i < chars.length; i++) {
                int offset = chars[i] - 'a';
                if (curNode.nexts[offset] == null) {
                    return 0;
                }
                curNode = curNode.nexts[offset];
            }
            return curNode.end;
        }

        /**
         * 删除指定字符串
         *
         * @param str
         */
        public void delete(String str) {
            if (str == null) {
                return;
            }
            char[] chars = str.toCharArray();
            Node curNode = root;
            for (int i = 0; i < chars.length; i++) {
                int offset = chars[i] - 'a';
                if (curNode.nexts[offset] == null) {
                    return;
                }
                if (--curNode.nexts[offset].pass == 0) {
                    curNode.nexts[offset] = null;
                    return;
                }
                curNode = curNode.nexts[offset];
            }
            curNode.end--;
        }

        /**
         * 返回前缀为指定字符串的字符串个数
         *
         * @param str
         * @return
         */
        public int prefixNumber(String str) {
            if (str == null) {
                return 0;
            }
            char[] chars = str.toCharArray();
            Node curNode = root;
            for (int i = 0; i < chars.length; i++) {
                int offset = chars[i] - 'a';
                if (curNode.nexts[offset] == null) {
                    return 0;
                }
                curNode = curNode.nexts[offset];
            }
            return curNode.pass;
        }

    }

    public static class HashMapNode {
        int pass;
        int end;
        HashMap<Integer, HashMapNode> nextMap;

        public HashMapNode(int pass, int end) {
            this.pass = pass;
            this.end = end;
            this.nextMap = new HashMap<>();
        }
    }

    /**
     * 基于Map实现的前缀树
     */
    public static class HashMapPreTree {
        private HashMapNode root;

        public HashMapPreTree() {
            root = new HashMapNode(0, 0);
        }

        public void insert(String str) {
            if (str == null) {
                return;
            }
            HashMapNode curNode = root;
            char[] chars = str.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int offset = chars[i] - 'a';
                if (!curNode.nextMap.containsKey(offset)) {
                    curNode.nextMap.put(offset, new HashMapNode(0, 0));
                }
                curNode = curNode.nextMap.get(offset);
                curNode.pass++;
            }
            curNode.end++;
        }

        public int search(String str) {
            if (str == null) {
                return 0;
            }
            HashMapNode curNode = root;
            char[] chars = str.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int offset = chars[i] - 'a';
                if (!curNode.nextMap.containsKey(offset)) {
                    return 0;
                }
                curNode = curNode.nextMap.get(offset);
            }
            return curNode.end;
        }

        public void delete(String str) {
            if (str == null) {
                return;
            }
            HashMapNode curNode = root;
            char[] chars = str.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int offset = chars[i] - 'a';
                if (!curNode.nextMap.containsKey(offset)) {
                    return;
                }
                if ((--curNode.nextMap.get(offset).pass) == 0) {
                    curNode.nextMap.remove(offset);
                    return;
                }
                curNode = curNode.nextMap.get(offset);
            }
            curNode.end--;
        }

        public int prefixNumber(String str){
            if (str == null) {
                return 0;
            }
            HashMapNode curNode = root;
            char[] chars = str.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int offset = chars[i] - 'a';
                if (!curNode.nextMap.containsKey(offset)) {
                    return 0;
                }
                curNode = curNode.nextMap.get(offset);
            }
            return curNode.pass;
        }

    }

    public static void main(String[] args) {
//        ArrayPreTree preTree = new ArrayPreTree();
        HashMapPreTree preTree = new HashMapPreTree();
        preTree.insert("abc");
        preTree.insert("dd");
        preTree.insert("cc");
        preTree.insert("abc");
        System.out.println(preTree.prefixNumber("a"));
        System.out.println(preTree.search("abc"));
        preTree.delete("abc");
        System.out.println(preTree.search("abc"));
        preTree.delete("abc");
        System.out.println(preTree.search("abc"));//2 2 1 0
    }

}
