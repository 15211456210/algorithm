package com.zcp.part5.c301to350;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author ZCP
 * @title: C341
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/flatten-nested-list-iterator/submissions/
 * @date 2022/10/19 17:05
 */
public class C341 {

    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }


    public class NestedIterator implements Iterator<Integer> {

        List<NestedInteger> nestedList;
        LinkedList<NestedInteger> dqueue;

        public NestedIterator(List<NestedInteger> nestedList) {
            this.nestedList = nestedList;
            this.dqueue = new LinkedList<>();
            for (int i = 0; i < nestedList.size(); i++) {
                dqueue.offer(nestedList.get(i));
            }
        }

        @Override
        public Integer next() {
            return dqueue.pollFirst().getInteger();
        }

        @Override
        public boolean hasNext() {
            cl();
            return !dqueue.isEmpty();
        }

        private void cl() {
            while (!dqueue.isEmpty() && !dqueue.peekFirst().isInteger()) {
                NestedInteger first = dqueue.pollFirst();
                List<NestedInteger> list = first.getList();
                int size = list.size();
                for (int i = size - 1; i >= 0; i--) {
                    dqueue.offerFirst(list.get(i));
                }
            }
        }
    }
}
