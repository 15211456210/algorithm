package com.zcp.part4.day11;

import java.util.ArrayList;
import java.util.List;

// 本题测试链接 : https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
public class Code01_MinimumInsertionStepsToMakeAStringPalindrome {


    /**
     * 思路：
     * 动态规划
     *
     * @param s
     * @return
     */
    public int minInsertions3(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = 0; i < len - 1; i++) {
            dp[i][i + 1] = chars[i] == chars[i + 1] ? 0 : 1;
        }

        for (int i = len - 3; i >= 0; i--) {
            for (int j = i + 2; j < len; j++) {
                if(chars[i] == chars[j]){
                    dp[i][j] = dp[i+1][j-1];
                }else {
                    dp[i][j] = 1 + Math.min(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][len - 1];
    }

    /**
     * 思路：
     * 递归尝试
     *
     * @param s
     * @return
     */
    public int minInsertions2(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }

        int len = s.length();
        return process2(s.toCharArray(), 0, len - 1);

    }

    /**
     * 返回s[left...right]区间任意添加字符，使之变成回文串的最小代价
     * @param s
     * @param left
     * @param right
     * @return
     */
    public int process2(char[] s, int left, int right) {
        if (left == right) {
            return 0;
        }
        if (left + 1 == right) {
            //如果相邻且 字符相同，代价为0，否则代价为1
            return s[left] == s[right] ? 0 : 1;
        }
        //right-left>=2
        if (s[left] == s[right]) {
            return process2(s, left + 1, right - 1);
        }
        //如果字符不同，有两种情况：
        //p1：在left左侧添加c[right]字符
        //p2：在right右侧添加c[left]字符
        return 1 + Math.min(process2(s, left + 1, right), process2(s, left, right - 1));
    }

    // 测试链接只测了本题的第一问，直接提交可以通过
    public static int minInsertions(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        for (int i = 0; i < N - 1; i++) {
            dp[i][i + 1] = str[i] == str[i + 1] ? 0 : 1;
        }
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]) + 1;
                if (str[i] == str[j]) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
                }
            }
        }
        return dp[0][N - 1];
    }

    // 本题第二问，返回其中一种结果
    public static String minInsertionsOneWay(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        for (int i = 0; i < N - 1; i++) {
            dp[i][i + 1] = str[i] == str[i + 1] ? 0 : 1;
        }
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]) + 1;
                if (str[i] == str[j]) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
                }
            }
        }

        int L = 0;
        int R = N - 1;
        char[] ans = new char[N + dp[L][R]];
        int ansl = 0;
        int ansr = ans.length - 1;
        while (L < R) {
            if (dp[L][R - 1] == dp[L][R] - 1) {
                ans[ansl++] = str[R];
                ans[ansr--] = str[R--];
            } else if (dp[L + 1][R] == dp[L][R] - 1) {
                ans[ansl++] = str[L];
                ans[ansr--] = str[L++];
            } else {
                ans[ansl++] = str[L++];
                ans[ansr--] = str[R--];
            }
        }
        if (L == R) {
            ans[ansl] = str[L];
        }
        return String.valueOf(ans);
    }

    // 本题第三问，返回所有可能的结果
    public static List<String> minInsertionsAllWays(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() < 2) {
            ans.add(s);
        } else {
            char[] str = s.toCharArray();
            int N = str.length;
            int[][] dp = new int[N][N];
            for (int i = 0; i < N - 1; i++) {
                dp[i][i + 1] = str[i] == str[i + 1] ? 0 : 1;
            }
            for (int i = N - 3; i >= 0; i--) {
                for (int j = i + 2; j < N; j++) {
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]) + 1;
                    if (str[i] == str[j]) {
                        dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
                    }
                }
            }
            int M = N + dp[0][N - 1];
            char[] path = new char[M];
            process(str, dp, 0, N - 1, path, 0, M - 1, ans);
        }
        return ans;
    }

    // 当前来到的动态规划中的格子，(L,R)
    // path ....  [pl....pr] ....
    public static void process(char[] str, int[][] dp, int L, int R, char[] path, int pl, int pr, List<String> ans) {
        if (L >= R) { // L > R  L==R
            if (L == R) {
                path[pl] = str[L];
            }
            ans.add(String.valueOf(path));
        } else {
            if (dp[L][R - 1] == dp[L][R] - 1) {
                path[pl] = str[R];
                path[pr] = str[R];
                process(str, dp, L, R - 1, path, pl + 1, pr - 1, ans);
            }
            if (dp[L + 1][R] == dp[L][R] - 1) {
                path[pl] = str[L];
                path[pr] = str[L];
                process(str, dp, L + 1, R, path, pl + 1, pr - 1, ans);
            }
            if (str[L] == str[R] && (L == R - 1 || dp[L + 1][R - 1] == dp[L][R])) {
                path[pl] = str[L];
                path[pr] = str[R];
                process(str, dp, L + 1, R - 1, path, pl + 1, pr - 1, ans);
            }
        }
    }

    public static void main(String[] args) {
        String s = null;
        String ans2 = null;
        List<String> ans3 = null;

        System.out.println("本题第二问，返回其中一种结果测试开始");
        s = "mbadm";
        ans2 = minInsertionsOneWay(s);
        System.out.println(ans2);

        s = "leetcode";
        ans2 = minInsertionsOneWay(s);
        System.out.println(ans2);

        s = "aabaa";
        ans2 = minInsertionsOneWay(s);
        System.out.println(ans2);
        System.out.println("本题第二问，返回其中一种结果测试结束");

        System.out.println();

        System.out.println("本题第三问，返回所有可能的结果测试开始");
        s = "mbadm";
        ans3 = minInsertionsAllWays(s);
        for (String way : ans3) {
            System.out.println(way);
        }
        System.out.println();

        s = "leetcode";
        ans3 = minInsertionsAllWays(s);
        for (String way : ans3) {
            System.out.println(way);
        }
        System.out.println();

        s = "aabaa";
        ans3 = minInsertionsAllWays(s);
        for (String way : ans3) {
            System.out.println(way);
        }
        System.out.println();
        System.out.println("本题第三问，返回所有可能的结果测试结束");
    }

}
