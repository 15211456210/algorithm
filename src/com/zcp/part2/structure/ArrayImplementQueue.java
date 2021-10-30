package com.zcp.part2.structure;

import java.lang.reflect.Array;

/**
 * 数组实现队列
 */
public class ArrayImplementQueue {
    public static class MyQueue<T>{
        T[] arr;
        int size;
        int length;
        int begin;
        int end;
        public MyQueue(Class<T> clazz,int len){
            this.arr = (T[]) Array.newInstance(clazz,len);
            this.size = 0;
            this.begin = 0;
            this.end = 0;
            this.length = len;
        }

        public void offer(T val){
            if(size == length){
                throw new RuntimeException("queue is fill");
            }
            arr[begin++%length] = val;
            size++;
        }

        public T peek(){
            if(size == 0){
                throw new RuntimeException("queue is empty");
            }
            return arr[end%length];
        }

        public T poll(){
            if(size == 0){
                throw new RuntimeException("queue is empty");
            }
            size--;
            return arr[end++%length];
        }

    }

    public static void main(String[] args) {
        MyQueue<Integer> myQueue = new MyQueue<>(Integer.class,3);
        myQueue.offer(1);
        myQueue.offer(2);
        myQueue.offer(3);
        System.out.println(myQueue.peek());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        myQueue.offer(4);
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
    }


}
