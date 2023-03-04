package com.zcp.part5.c401to450;


import java.util.Stack;

/**
 * @author ZCP
 * @title: C443
 * @projectName algorithm
 * @description: https://leetcode.com/problems/string-compression/
 * @date 2023/2/1 14:12
 */
public class C443 {

    public int compress(char[] chars) {
        // [start,end)
        int start = 0, end = 0;
        int overWIndex = 0;

        while (start < chars.length) {
            if (end == chars.length || chars[end] != chars[start]) {
                chars[overWIndex++] = chars[start];
                int len = end - start;
                if (len > 1) {
                    char[] lenc = toCharArr(len);
                    System.arraycopy(lenc, 0, chars, overWIndex, lenc.length);
                    overWIndex += lenc.length;
                }
                start = end;
            } else {
                end++;
            }
        }


        return overWIndex;
    }

    /**
     * 123->  ['1','2','3']
     *
     * @param len
     * @return
     */
    private char[] toCharArr(int len) {
        Stack<Integer> stack = new Stack<>();
        while (len > 0) {
            stack.push(len % 10);
            len /= 10;
        }
        char[] ans = new char[stack.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = ((char) (stack.pop() + '0'));
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new C443().compress(new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'}));
    }
}
