package com.zcp.part2.heap;

import jdk.nashorn.internal.ir.CallNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * 自定义加强堆
 * 可实现功能：
 * 1.可实现复杂类型的自定义比较器
 * 2.支持堆大小的动态扩容
 * 3.支持 add,poll,peek,size,remove,contains 方法
 * 4.任何操作的时间复杂度不超过O(logN)
 *
 * @author ZCP
 */
public class MyHeapPlus<T> {

    private ArrayList<Wrapper<T>> heap;
    private HashMap<Wrapper<T>, Integer> indexMap;
    private Comparator<T> comparator;

    public MyHeapPlus(Comparator<T> comparator) {
        this.heap = new ArrayList<>();
        this.indexMap = new HashMap<>();
        this.comparator = comparator;
    }

    /**
     * 加入元素
     *
     * @param val
     */
    public void add(T val) {
        Wrapper wrapper = new Wrapper(val);
        heap.add(wrapper);
        indexMap.put(wrapper, heap.size() - 1);
        //heapInsert
        heapInsert();
    }

    /**
     * 显示第一个数据
     *
     * @return
     */
    public T peek() {
        return heap.get(0).val;
    }

    /**
     * 返回第一个元素，并将该元素删除
     *
     * @return
     */
    public T poll() {
        Wrapper<T> ans = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        indexMap.remove(ans);
        //heapify
        heapify();
        return ans.val;
    }

    public int size() {
        return heap.size();
    }

    public void heapify() {
        int index = 0;
        int left = index * 2 + 1;
        int size = heap.size();
        while (left < size) {
            int right = left + 1;
            if (right < size && comparator.compare(heap.get(left).val, heap.get(right).val) > 0) {
                swap(index, right);
                index = right;
            } else if (comparator.compare(heap.get(left).val, heap.get(index).val) < 0) {
                swap(index, left);
                index = left;
            } else {
                break;
            }
            left = index * 2 + 1;
        }
    }

    public void heapInsert() {
        if (heap.size() <= 1) {
            return;
        }
        int index = heap.size() - 1;
        while (comparator.compare(heap.get(index).val, heap.get((index - 1) / 2).val) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }


    /**
     * 交换堆元素，同时交换反向索引表元素
     *
     * @param index
     * @param index2
     */
    private void swap(int index, int index2) {
        Wrapper w1 = heap.get(index);
        Wrapper w2 = heap.get(index2);
        heap.set(index, w2);
        heap.set(index2, w1);
        indexMap.put(w1, index2);
        indexMap.put(w2, index);
    }

    public boolean isEmpty() {
        return heap.size() == 0;
    }

    /**
     * 包装类
     * （主要为了解决基础类型反向索引表按值传递导致索引覆盖问题）
     *
     * @param <T>
     */
    public static class Wrapper<T> {
        T val;

        public Wrapper(T val) {
            this.val = val;
        }
    }

    public static class Stu {
        int id;
        int age;
        String name;

        public Stu(int id, int age, String name) {
            this.id = id;
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Stu{" +
                    "id=" + id +
                    ", age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
//        MyHeapPlus<Integer> myHeapPlus = new MyHeapPlus<>((o1, o2) -> o1.compareTo(o2));
//        myHeapPlus.add(5);
//        myHeapPlus.add(2);
//        myHeapPlus.add(3);
//        myHeapPlus.add(0);
//        myHeapPlus.add(6);
//
//        System.out.println(myHeapPlus.poll());
//        System.out.println(myHeapPlus.poll());
//        System.out.println(myHeapPlus.poll());
//        System.out.println(myHeapPlus.poll());
        MyHeapPlus<Stu> stuMyHeapPlus = new MyHeapPlus<>((o1, o2) -> o2.age - o1.age);

        stuMyHeapPlus.add(new Stu(1,24,"kiki"));
        stuMyHeapPlus.add(new Stu(2,20,"bob"));
        stuMyHeapPlus.add(new Stu(3,18,"huhu"));
        stuMyHeapPlus.add(new Stu(4,28,"66"));
        stuMyHeapPlus.add(new Stu(5,25,"ss"));
        stuMyHeapPlus.add(new Stu(6,20,"11"));

        System.out.println(stuMyHeapPlus.poll());
        System.out.println(stuMyHeapPlus.poll());
        stuMyHeapPlus.add(new Stu(7,30,"qq"));
        System.out.println(stuMyHeapPlus.poll());
        System.out.println(stuMyHeapPlus.poll());
        System.out.println(stuMyHeapPlus.poll());



        System.out.println();
    }
}
