package com.zcp.part2.structure;

import java.lang.reflect.Array;

/**
 *  数组实现栈
 */
public class ArrayImplementStack {
    public static class MyStack<T>{
        T[] arr;
        int size;
        int length;

        public MyStack(Class<T> clazz,int len) {
            this.arr = (T[]) Array.newInstance(clazz,len);
            this.size = 0;
            this.length = len;
        }

        public void push(T val){
            if(size == length){
                throw new RuntimeException("stack is fill");
            }
            arr[size++] = val;
        }

        public T peek(){
            if(size == 0){
                throw new RuntimeException("stack is empty");
            }
            return arr[size-1];
        }

        public T pop(){
            if(size == 0){
                throw new RuntimeException("stack is empty");
            }
            return arr[--size];
        }

    }


    public static void main(String[] args) {
        MyStack<Integer> myStack = new MyStack<>(Integer.class, 5);
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        System.out.println(myStack.peek());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
    }



}
