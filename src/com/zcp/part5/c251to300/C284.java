package com.zcp.part5.c251to300;

import java.util.Iterator;

/**
 * @author ZCP
 * @title: C284
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/peeking-iterator/
 * @date 2022/9/26 16:34
 */
public class C284 {

    class PeekingIterator implements Iterator<Integer> {

        Iterator<Integer> iterator;
        Integer next = null;

        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.
            this.iterator = iterator;
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            if (next == null) {
                next = iterator.next();
            }
            return next;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            if (next != null) {
                Integer n = next;
                next = null;
                return n;
            } else {
                return iterator.next();
            }
        }

        @Override
        public boolean hasNext() {
            return next != null ? true : iterator.hasNext();
        }
    }
}
