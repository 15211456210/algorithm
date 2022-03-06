package com.zcp.part4.day19;

import java.util.HashMap;

// 本题测试链接 : https://leetcode.com/problems/lfu-cache/
// 提交时把类名和构造方法名改为 : LFUCache
public class LFUCache {

    HashMap<Integer, Node> nodeMap = new HashMap<>();
    HashMap<Integer, Bucket> bucketMap = new HashMap<>();

    int capacity;
    int size;

    Bucket headBucket;
    Bucket tailBucket;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
    }

    public int get(int key) {
        if (capacity <= 0) {
            return -1;
        }
        if (!nodeMap.containsKey(key)) {
            return -1;
        } else {
            Node node = nodeMap.get(key);
            Bucket preBucket = bucketMap.get(key);
            node.frequency++;
            moveNode(preBucket, node, node.frequency);
            return node.val;
        }
    }


    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }
        if (!nodeMap.containsKey(key)) {
            if (size == capacity) {
                removeFirstNode();
            } else {
                size++;
            }
            Node node = new Node(key, value);
            nodeMap.put(key, node);
            Bucket firstBunket = getFirstBunket();
            bucketMap.put(key, firstBunket);
            firstBunket.addNode(node);
            return;
        } else {
            //key 已存在
            //需要将node在原桶中移除
            //将node加入后面的桶中
            Node node = nodeMap.get(key);
            node.val = value;
            Bucket preBucket = bucketMap.get(key);
            node.frequency++;
            moveNode(preBucket, node, node.frequency);
        }
    }

    /**
     * 按照最低使用频率+最早使用时间策略 删除一个节点
     */
    private void removeFirstNode() {
        int removeKey = headBucket.removeFirst();
        nodeMap.remove(removeKey);
        bucketMap.remove(removeKey);
        checkEmptyBucket(headBucket);
    }

    /**
     * 节点移动过后需要判断桶是否为空桶
     * 如果空桶需要删除掉
     *
     * @param bucket
     */
    private void checkEmptyBucket(Bucket bucket) {
        Bucket preBucket = bucket.pre;
        Bucket nextBucket = bucket.next;
        if (bucket.head == null) {
            //如果
            if (preBucket == null && nextBucket == null) {
                headBucket = null;
                tailBucket = null;
            } else if (preBucket == null) {
                //第一个桶
                bucket.next = null;
                nextBucket.pre = null;
                headBucket = nextBucket;
            } else if (nextBucket == null) {
                //最后一个桶
                bucket.pre = null;
                preBucket.next = null;
                tailBucket = preBucket;
            } else {
                //中间的桶
                bucket.pre = null;
                bucket.next = null;
                preBucket.next = nextBucket;
                nextBucket.pre = preBucket;
            }
        }
    }


    /**
     * 移动node到指定的桶中
     *
     * @param preBucket 当前所在桶
     * @param node      当前移动节点
     * @param frequency 结算后node的使用频率
     */
    private void moveNode(Bucket preBucket, Node node, int frequency) {
        Bucket nextBucket = preBucket.next;
        preBucket.removeNode(node);
        if (nextBucket == null) {
            Bucket putBucket = new Bucket(frequency, preBucket, null);
            tailBucket = putBucket;
            preBucket.next = putBucket;
            putBucket.addNode(node);
            updateNodeLocate(node, putBucket);
        } else if (nextBucket.frequency != frequency) {
            //nextBucket存在,但是frequency 不是 node 当前的frequency
            Bucket putBucket = new Bucket(frequency, preBucket, null);
            preBucket.next = putBucket;
            putBucket.pre = preBucket;
            putBucket.next = nextBucket;
            nextBucket.pre = putBucket;
            putBucket.addNode(node);
            updateNodeLocate(node, putBucket);
        } else {
            //nextBucket存在 且 frequency 等于 node frequency
            nextBucket.addNode(node);
            updateNodeLocate(node, nextBucket);
        }

        //需要判断preBucket是否为空
        checkEmptyBucket(preBucket);
    }

    /**
     * 更新节点位置
     *
     * @param node
     * @param putBucket
     */
    private void updateNodeLocate(Node node, Bucket putBucket) {
        this.bucketMap.put(node.key, putBucket);
    }

    private Bucket getFirstBunket() {
        if (headBucket == null) {
            //一开始无桶情况
            headBucket = new Bucket();
            tailBucket = headBucket;
            return headBucket;
        } else if (headBucket.frequency != 1) {
            //有桶但是不是1,需要将新桶插入到头部
            Bucket bucket = new Bucket();
            headBucket.pre = bucket;
            bucket.next = headBucket;
            headBucket = bucket;
            return headBucket;
        } else {
            //存在1桶
            return headBucket;
        }
    }


    /**
     * 根据使用频率分桶
     */
    static class Bucket {
        //使用的频率
        int frequency;
        Node head;
        Node tail;
        Bucket pre;
        Bucket next;

        public Bucket() {
            this.frequency = 1;
        }

        public Bucket(int frequency, Bucket pre, Bucket next) {
            this.frequency = frequency;
            this.pre = pre;
            this.next = next;
        }

        public void addNode(Node node) {
            if (head == null) {
                //无节点
                head = node;
                tail = head;
            } else {
                tail.next = node;
                node.pre = tail;
                tail = node;
            }
        }

        /**
         * 删除第一个节点，保证删除之前桶内有节点
         */
        public int removeFirst() {
            int key = head.key;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                //删除头节点
                Node next = head.next;
                next.pre = null;
                head.next = null;
                head = next;
            }
            return key;
        }

        /**
         * 删除节点
         *
         * @param node
         */
        public void removeNode(Node node) {
            Node preNode = node.pre;
            Node nextNode = node.next;
            if (preNode == null && nextNode == null) {
                //头节点且桶内只有一个节点
                head = null;
                tail = null;
            } else if (preNode == null) {
                //头节点
                nextNode.pre = null;
                node.next = null;
                head = nextNode;
            } else if (nextNode == null) {
                //node是尾节点
                preNode.next = null;
                node.pre = null;
                tail = preNode;
            } else {
                //中间节点
                node.pre = null;
                node.next = null;
                preNode.next = nextNode;
                nextNode.pre = preNode;
            }

        }
    }

    static class Node {
        int key;
        int val;
        Node pre;
        Node next;
        int frequency;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.frequency = 1;//默认是1
        }

    }


}