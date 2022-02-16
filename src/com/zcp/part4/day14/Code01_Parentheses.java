package com.zcp.part4.day14;

public class Code01_Parentheses {


    /**
     * 思路：
     * 动态规划
     *
     * @param s
     * @return
     */
    public int longestValidParentheses2(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        char[] partten = s.toCharArray();
        int len = partten.length;
        //dp[i] 表示必须以partten[i]字符结尾的最长有效长度
        int[] dp = new int[len];
        int maxLen = 0;
        for (int i = 1; i < len; i++) {
            if (partten[i] == '(') {
                dp[i] = 0;
            } else {
                // ')'
                int preIndex = i - dp[i - 1] - 1;//取到最近一个与当前位置匹配的 字符 下标
                if (preIndex < 0) {
                    dp[i] = 0;
                } else if (partten[preIndex] == '(') {
                    //如果 partten[preIndex .... i]可以成功组成有效串，那么还需要加上 dp[preIndex-1]，即以preIndex-1位置结尾的最长有效长度
                    dp[i] = (preIndex - 1 >= 0 ? dp[i - 1] + 2 + dp[preIndex - 1] : dp[i - 1] + 2);
                    maxLen = Math.max(maxLen,dp[i]);
                }
            }
        }
        return maxLen;
    }

    public static boolean valid(String s) {
        char[] str = s.toCharArray();
        int count = 0;
        for (int i = 0; i < str.length; i++) {
            count += str[i] == '(' ? 1 : -1;
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }

    public static int needParentheses(String s) {
        char[] str = s.toCharArray();
        int count = 0;
        int need = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '(') {
                count++;
            } else { // 遇到的是')'
                if (count == 0) {
                    need++;
                } else {
                    count--;
                }
            }
        }
        return count + need;
    }

    public static boolean isValid(char[] str) {
        if (str == null || str.length == 0) {
            return false;
        }
        int status = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] != ')' && str[i] != '(') {
                return false;
            }
            if (str[i] == ')' && --status < 0) {
                return false;
            }
            if (str[i] == '(') {
                status++;
            }
        }
        return status == 0;
    }

    public static int deep(String s) {
        char[] str = s.toCharArray();
        if (!isValid(str)) {
            return 0;
        }
        int count = 0;
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '(') {
                max = Math.max(max, ++count);
            } else {
                count--;
            }
        }
        return max;
    }

    // s只由(和)组成
    // 求最长有效括号子串长度
    // 本题测试链接 : https://leetcode.com/problems/longest-valid-parentheses/
    public static int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] str = s.toCharArray();
        // dp[i] : 子串必须以i位置结尾的情况下，往左最远能扩出多长的有效区域
        int[] dp = new int[str.length];
        // dp[0] = 0; （  ）
        int pre = 0;
        int ans = 0;
        for (int i = 1; i < str.length; i++) {
            if (str[i] == ')') {
                // 当前谁和i位置的)，去配！
                pre = i - dp[i - 1] - 1; // 与str[i]配对的左括号的位置 pre
                if (pre >= 0 && str[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

}
