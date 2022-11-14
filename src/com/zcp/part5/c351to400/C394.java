package com.zcp.part5.c351to400;

/**
 * @author ZCP
 * @title: C394
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/decode-string/
 * @date 2022/11/11 11:10
 */
public class C394 {

    class Pair {
        String path;
        int end;

        public Pair(String path, int end) {
            this.path = path;
            this.end = end;
        }
    }

    public String decodeString(String s) {
        return process(s.toCharArray(), 0).path;
    }


    public Pair process(char[] s, int i) {
        if (i == s.length) {
            return new Pair("", i);
        }
        if (s[i] >= '0' && s[i] <= '9') {
            int nextNoNumIndex = getNextNoNumIndex(s, i);
            int num = 0;
            int k = 1;
            for (int n = nextNoNumIndex - 1; n >= i; n--) {
                num += k * (s[n] - '0');
                k *= 10;
            }
            Pair next = process(s, nextNoNumIndex);
            String newStr = "";
            for (int index = 0; index < num; index++) {
                newStr += next.path;
            }
            next.path = newStr;
            Pair nnext = process(s, next.end + 1);
            nnext.path = next.path + nnext.path;
            return nnext;
        } else if (s[i] == '[') {
            return process(s, i + 1);
        } else if (s[i] == ']') {
            Pair pair = new Pair("", i);
            return pair;
        } else {
            Pair next = process(s, i + 1);
            next.path = s[i] + next.path;
            return next;
        }


    }

    public int getNextNoNumIndex(char[] s, int i) {
        for (int n = i + 1; n < s.length; n++) {
            if (s[n] < '0' || s[n] > '9') {
                return n;
            }
        }
        return -1;
    }
}