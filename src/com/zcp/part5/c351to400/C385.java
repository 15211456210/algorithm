package com.zcp.part5.c351to400;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZCP
 * @title: C385
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/mini-parser/description/
 * @date 2022/10/31 14:10
 */
public class C385 {

    static class NestedInteger {
        // Constructor initializes an empty nested list.
        public NestedInteger() {
        }

        ;

        // Constructor initializes a single integer.
        public NestedInteger(int value) {
        }

        ;

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger() {
            return false;
        }

        ;

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger() {
            return 0;
        }

        ;

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value) {

        }

        ;

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni) {

        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList() {
            return null;
        }
    }

    static class Solution {
        public NestedInteger deserialize(String s) {
            return parse(s);
        }

        private NestedInteger parse(String s) {
            if (s.charAt(0) != '[') {
                return new NestedInteger(Integer.valueOf(s));
            }
            NestedInteger ans = new NestedInteger();

            int leftCnt = 0;
            int start = 1;
            for (int i = 1; i < s.length() - 1; i++) {
                if (s.charAt(i) == '[') {
                    leftCnt++;
                } else if (s.charAt(i) == ']') {
                    leftCnt--;
                }
                if (s.charAt(i) == ',' && leftCnt == 0) {
                    ans.add(parse(s.substring(start, i)));
                    start = i + 1;
                }
            }
            return ans;
        }

        public static void main(String[] args) {
            NestedInteger deserialize = new Solution().deserialize("[123,[456,[789]]]");
        }
    }
}
