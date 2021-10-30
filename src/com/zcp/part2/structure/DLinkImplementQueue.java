package com.zcp.part2.structure;

/**
 * 用双向链表实现队列
 */
public class DLinkImplementQueue {

    public static class Node<T> {
        T val;
        Node pre;
        Node next;

        public Node(T val, Node pre, Node next) {
            this.val = val;
            this.pre = pre;
            this.next = next;
        }
    }

    public static class MyQueue<T> {
        Node<T> head;
        Node<T> last;
        int size;

        public MyQueue(){
            head = null;
            last = null;
            size = 0;
        }
        public void offer(T val){
            if(size == 0){
                head = new Node<>(val,null,null);
                last = head;
            }else {
                Node<T> next = new Node<>(val, last, null);
                last.next = next;
                last = next;
            }
            size++;
        }

        public T poll(){
            if(size == 0){
                throw new RuntimeException("Quere is empty");
            }
            if(size == 1){
                T ans = head.val;
                head = null;
                last = null;
                size--;
                return ans;
            }else{
                T ans = head.val;
                Node next = head.next;
                head.next = null;
                head = next;
                head.pre = null;
                size--;
                return  ans;
            }
        }

        public T peek(){
            if(size == 0){
                throw new RuntimeException("Quere is empty");
            }
            return head.val;
        }

        public static void main(String[] args) {
            MyQueue<Integer> myQueue = new MyQueue<>();
            myQueue.offer(1);
            myQueue.offer(2);
            myQueue.offer(3);
            myQueue.offer(4);
            System.out.println(myQueue.peek());
            System.out.println(myQueue.poll());
            System.out.println(myQueue.poll());
            System.out.println(myQueue.poll());
            System.out.println(myQueue.poll());
            System.out.println(myQueue.poll());
            System.out.println(myQueue.poll());
        }
    }

}
