package com.zcp.part4.day10;

import com.zcp.part4.day05.Hash;

import java.util.*;

public class TopK {


    class Node implements Comparable<Node> {
        String key;
        int count;

        public Node(String key, int count) {
            this.key = key;
            this.count = count;
        }

        @Override
        public int compareTo(Node node) {
            return (node.count == this.count) ? (node.key.compareTo(this.key)) : (this.count - node.count);
        }
    }

    Node[] heap;

    int capacity;

    int heapSize = 0;

    /**
     * 记录word在heap[]中的位置
     */
    HashMap<String, Integer> indexMap;

    /**
     * 记录每个word出现的次数
     */
    HashMap<String, Integer> countMap;

    /*
     * @param k: An integer
     */
    public TopK(int k) {
        // do intialization if necessary
        capacity = k;
        heap = new Node[capacity];
        indexMap = new HashMap<>();
        countMap = new HashMap<>();
    }

    /*
     * @param word: A string
     * @return: nothing
     */
    public void add(String word) {
        if (capacity <= 0) {
            return;
        }
        if (!countMap.containsKey(word)) {
            //从未出现过的word
            Node node = new Node(word, 1);
            countMap.put(word, 1);
            indexMap.put(word, -1);
            if (heapSize < capacity) {
                heap[heapSize] = node;
                indexMap.put(word, heapSize);
                heapInsert(heapSize);
                heapSize++;
            } else {
                if (node.compareTo(heap[0]) > 0) {
                    //替换heap[0]位置的值
                    Node oldNode = heap[0];
                    //表示当前节点已经不在heap中
                    indexMap.put(oldNode.key, -1);
                    heap[0] = node;
                    indexMap.put(node.key, 0);
                    heapfiy(0);
                }
            }
        } else {
            //之前出现过，词频+1
            int appearCount = countMap.get(word) + 1;
            countMap.put(word, appearCount);
            //获取当前word对应node在heap[]上的下标
            Integer index = indexMap.get(word);
            if (index.equals(-1)) {
                Node node = new Node(word, appearCount);
                //说明不在堆上
                if (node.compareTo(heap[0]) > 0) {
                    //替换heap[0]位置的值
                    Node oldNode = heap[0];
                    //表示当前节点已经不在heap中
                    indexMap.put(oldNode.key, -1);
                    heap[0] = node;
                    indexMap.put(node.key, 0);
                    heapfiy(0);
                }
            } else {
                heap[index].count = appearCount;
                heapfiy(index);
            }
        }
    }

    /*
     * @return: the current top k frequent words.
     */
    public List<String> topk() {
        if (capacity <= 0) {
            return new ArrayList<>();
        }
        // write your code here
        Node[] sortArr = new Node[heapSize];
        System.arraycopy(heap, 0, sortArr, 0, heapSize);

        Arrays.sort(sortArr);
        String[] ans = new String[heapSize];
        for (int i = 0; i < heapSize; i++) {
            ans[i] = sortArr[heapSize - 1 - i].key;
        }
        return Arrays.asList(ans);
    }

    /**
     * 下沉
     *
     * @param index
     */
    public void heapfiy(int index) {
        int curIndex = index;
        int left = curIndex * 2 + 1;
        int right = left + 1;
        while (left < heapSize) {
            if (right < heapSize) {
                if (heap[left].compareTo(heap[right]) < 0 && heap[left].compareTo(heap[curIndex]) < 0) {
                    swap(left, curIndex);
                    curIndex = left;
                } else if (heap[right].compareTo(heap[left]) < 0 && heap[right].compareTo(heap[curIndex]) < 0) {
                    swap(right, curIndex);
                    curIndex = right;
                } else {
                    break;
                }
            } else {
                if (heap[left].compareTo(heap[curIndex]) < 0) {
                    swap(left, curIndex);
                    curIndex = left;
                } else {
                    break;
                }
            }
            left = curIndex * 2 + 1;
            right = left + 1;
        }
    }

    /**
     * 上浮
     *
     * @param index
     */
    public void heapInsert(int index) {
        int curIndex = index;
        while (curIndex != 0 && heap[curIndex].compareTo(heap[(curIndex - 1) / 2]) < 0) {
            swap(curIndex, (curIndex - 1) / 2);
            curIndex = (curIndex - 1) / 2;
        }
    }

    /**
     * 返回树的根节点
     *
     * @return
     */
    public Node pop() {
        swap(0, heapSize - 1);
        Node node = heap[heapSize - 1];
        heapSize--;
        heapfiy(0);
        return node;
    }

    private void swap(int i1, int i2) {
        Node tmp = heap[i1];
        heap[i1] = heap[i2];
        heap[i2] = tmp;
        indexMap.put(heap[i1].key, i1);
        indexMap.put(heap[i2].key, i2);
    }

    public static void main(String[] args) {
        TopK topK = new TopK(4);
        topK.add("cwht");
        topK.add("xvnz");
        topK.add("yh");
        topK.add("miyz");
        topK.add("xbpb");
        List<String> ans = topK.topk();
        System.out.println(ans);

    }
}