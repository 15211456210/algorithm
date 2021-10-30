package com.zcp.part2.structure;

/**
 * 双向链表实现栈
 */
public class DLinkImplementStack {

    public static class Node<T>{
        T val;
        Node pre;
        Node next;

        public Node(T val, Node pre, Node next) {
            this.val = val;
            this.pre = pre;
            this.next = next;
        }
    }

    public static class MyStack<T>{
        public Node<T> head;
        public Node<T> last;
        public int size;

        public MyStack() {
            this.head = null;
            this.last = null;
            this.size = 0;
        }

        public T peek(){
            if(size == 0){
                throw new RuntimeException("stack is empty");
            }
            return last.val;
        }

        public T pop(){
            if(size == 0){
                throw new RuntimeException("stack is empty");
            }
            if(size == 1){
                T ans = last.val;
                head = null;
                last = null;
                size--;
                return ans;
            }else {
                T ans = last.val;
                Node pre = last.pre;
                last.pre = null;
                pre.next = null;
                last = pre;
                size--;
                return ans;
            }

        }

        public void push(T val){
            if (size == 0){
                head = new Node<>(val,null,null);
                last = head;
            }else {
                Node next = new Node<>(val,last,null);
                last.next = next;
                last = next;
            }
            size++;
        }

    }


    public static void main(String[] args) {
        MyStack<Integer> myStack = new MyStack<>();
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
