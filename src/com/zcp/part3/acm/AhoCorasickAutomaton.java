package com.zcp.part3.acm;

import java.util.*;

/**
 * @description: AC自动机
 * @projectName:algorithm
 * @see:com.zcp.part2.acm
 * @author:ZCP
 * @createTime:2021/10/30
 * @version:1.0
 */
public class AhoCorasickAutomaton {


    Node root;

    /**
     * 字典树节点类
     */
    public class Node {
        String seq;
        Node[] nexts;
        Node fail;

        boolean end;//是否是结束节点
        boolean hasFind;

        public Node(String sq) {
            seq = sq;
            nexts = new Node[26];
        }
    }

    public AhoCorasickAutomaton(String[] words) {
        root = new Node("");
        for (String word : words) {
            addTrie(word);
        }
        build();
    }

    /**
     * 构建tire 每个节点的fail指针
     */
    private void build() {
        //root节点fail指针指向空
        root.fail = null;
        //宽度优先遍历
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            if (curNode == root) {
                //如果是根节点，所有子节点fail指针指向root
                for (Node next : curNode.nexts) {
                    if (next != null) {
                        next.fail = root;
                    }
                }
            } else {
                //如果不是root节点
                for (int i = 0; i < curNode.nexts.length; i++) {
                    Node next = curNode.nexts[i];
                    if (next != null) {
                        next.fail = root;//先指向root，如果下面能匹配到更优的节点就覆盖这里的指向
                        Node curFail = curNode.fail;
                        while (curFail != null) {
                            if (curFail.nexts[i] != null) {
                                //匹配到了，直接将next节点的fail指针指向它
                                next.fail = curFail.nexts[i];
                                break;
                            }
                            //如果没有匹配到，继续向上跳
                            curFail = curFail.fail;
                        }
                    }
                }
            }
        }

    }

    /**
     * 构建字典树
     *
     * @param word
     */
    private void addTrie(String word) {
        char[] chars = word.toCharArray();
        Node cur = root;
        for (char c : chars) {
            if (cur.nexts[c - 'a'] == null) {
                cur.nexts[c - 'a'] = new Node(cur.seq + c);
            }
            cur = cur.nexts[c - 'a'];
        }
        cur.end = true;
    }

    /**
     * 返回 context文本中匹配到的words
     *
     * @param context
     * @return
     */
    public List<String> query(String context) {
        char[] chars = context.toCharArray();
        ArrayList<String> findWords = new ArrayList<>();
        Node cur = root;
        for (char c : chars) {
            Node matchNode = cur;
            while (matchNode != null && matchNode.nexts[c - 'a'] == null) {
                matchNode = matchNode.fail;
            }
            //两种情况
            cur = matchNode == null? root:matchNode.nexts[c - 'a'];
            if(cur.end && !cur.hasFind){
                cur.hasFind = true;
                findWords.add(cur.seq);
            }
        }
        return findWords;
    }


    public static void main(String[] args) {
        AhoCorasickAutomaton ahoCorasickAutomaton = new AhoCorasickAutomaton(new String[]{"tx", "abc", "acd"});
        List<String> list = ahoCorasickAutomaton.query("dhjakjkabcqdjatxxtadbna");
        System.out.println(Arrays.toString(list.toArray(new String[0])));
    }


}
