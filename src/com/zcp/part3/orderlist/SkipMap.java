package com.zcp.part3.orderlist;


/**
 * @description:跳表
 * @projectName:algorithm
 * @see:com.zcp.part2.orderlist
 * @author:ZCP
 * @createTime:2021/11/14
 * @version:1.0
 */
public class SkipMap {

    //最开始的节点
    SkipNode root;

    //节点个数
    int size;

    //最高层数
    int maxLevel;

    //摇塞子的概率，直接将觉得大数据量下每层节点的比例
    static final double PROBABILITY = 0.5;

    public SkipMap() {
        root = new SkipNode();
        maxLevel = 0;
        size = 0;
    }

    public Object get(int key) {
        queryCount = 0;
        SkipNode left = getLastLeftWithLevel(key, root, maxLevel);
        return left.nodes[0] != null ? (left.nodes[0].key == key ? left.nodes[0].value : null) : null;
    }

    /**
     * 添加节点
     *
     * @param key
     * @param val
     */
    public void add(int key, Object val) {
        if (!containsKey(key)) {
            size++;
            int level = 0;
            //先摇塞子 确定添加节点的最大level
            while (Math.random() < PROBABILITY) {
                level++;
            }
            //将当前整张表的最大maxLevel更新
//            while (maxLevel < level) {
//                maxLevel++;
//                root.nodes.add(new SkipNode(Integer.MIN_VALUE, "#", maxLevel));
//            }
            if (maxLevel < level) {
                //expansion扩容
                expansion(level);
            }
            SkipNode addNode = new SkipNode(key, val, level);
            doAddNode(root, addNode, level);
        }
    }

    public int delete(int key) {
        if (containsKey(key)) {
            size--;
            doDelete(key, root, maxLevel);
            return key;
        }
        return -1;
    }

    /**
     * 删除
     *
     * @param key
     * @param cur
     * @param level
     */
    private void doDelete(int key, SkipNode cur, int level) {
        if (level >= 0) {
            SkipNode next = cur.nodes[level];
            if (next == null || next.key > key) {
                doDelete(key, cur, level - 1);
            } else if (next.key < key) {
                doDelete(key, next, level);
            } else if (next.key == key) {
                cur.nodes[level] = next.nodes[level];
                doDelete(key, cur, level - 1);
            }
        }
    }

    /**
     * 扩容方法
     *
     * @param level
     */
    private void expansion(int level) {
        SkipNode[] newNodes = new SkipNode[level + 1];
        for (int i = 0; i <= maxLevel; i++) {
            newNodes[i] = root.nodes[i];
        }
        root.nodes = newNodes;
        //更新最大层数
        maxLevel = level;
    }

    /**
     * 添加节点方法
     *
     * @param cur
     * @param addNode
     * @param level
     */
    private void doAddNode(SkipNode cur, SkipNode addNode, int level) {
        SkipNode next = cur.nodes[level];
        if (next == null || next.key > addNode.key) {
            //将当前节点插入 当前层链表
            cur.nodes[level] = addNode;
            addNode.nodes[level] = next;
            if (level > 0) {
                //继续取下一层添加节点
                doAddNode(cur, addNode, level - 1);
            }
        } else {
            //如果当前节点的后继节点比 要添加的key小，说明还可以往后走
            doAddNode(next, addNode, level);
        }
    }

    /**
     * 是否存在key
     *
     * @param key
     * @return
     */
    public boolean containsKey(int key) {
        SkipNode mostLeftNode = getLastLeftWithLevel(key, root, maxLevel);
        return mostLeftNode != null && mostLeftNode.nodes[0] != null && mostLeftNode.nodes[0].key == key;
    }

    static int queryCount = 0;

    /**
     * 获取level层最后一个不大于 key的节点
     *
     * @param key
     * @param node
     * @param level
     * @return
     */
    private SkipNode getLastLeftWithLevel(int key, SkipNode node, int level) {
        queryCount++;
        SkipNode next = node.nodes[level];
        if (next == null || next.key >= key) {
            if (level > 0) {
                return getLastLeftWithLevel(key, node, level - 1);
            }
            //已经是第0层了，并且next节点不比当前节点key小返回
            return node;
        } else {
            return getLastLeftWithLevel(key, next, level);
        }
    }

    /**
     * 跳表内部节点 数据结构
     */
    public static class SkipNode {
        int key;
        Object value;
        SkipNode[] nodes;//里面存放着这个结点每层往后的节点链表
        int level;
        SkipNode next;

        public SkipNode() {
            level = 0;//一开始有0层
            nodes = new SkipNode[level + 1];
        }

        public SkipNode(int key, Object value, int level) {
            this.key = key;
            this.value = value;
            this.level = level;
            nodes = new SkipNode[level + 1];
        }
    }


    public static void main(String[] args) {
        SkipMap skipMap = new SkipMap();

        for (int i = 0; i < 10000; i++) {
            skipMap.add(i, i);
        }
        skipMap.delete(9988);
        System.out.println(skipMap.get(9987));
        System.out.println(skipMap.get(9988));
//        System.out.println(queryCount);

//
//        LinkedList<Integer> integers = new LinkedList<Integer>();
//
//        integers.set(1, 3);

        System.out.println();
    }

}
