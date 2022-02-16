package com.zcp.part4.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 本题测试链接 : https://leetcode.com/problems/palindrome-partitioning-ii/
public class Code02_PalindromePartitioningII {


    /**
     * 思路：
     * 动态规划+记忆化搜索+优化回文串判断
     *
     * @param s
     * @return
     */
    public int minCut5(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int len = s.length();
        //生成是否回文串dp表
        boolean[][] checkPalindrome = getCheckPalindorme(chars);

        //创建dp
        int[] dp = new int[len];
        dp[len - 2] = chars[len - 1] == chars[len - 2] ? 0 : 1;

        for (int start = len - 3; start >= 0; start--) {
            dp[start] = len - start - 1;
            for (int end = start; end < len; end++) {
                if (checkPalindrome[start][end]) {
                    if (end == len - 1) {
                        dp[start] = 0;
                        break;
                    } else {
                        dp[start] = Math.min(dp[start], dp[end + 1] + 1);
                    }
                }
            }
        }
        return dp[0];
    }

    /**
     * 思路：
     * 动态规划+记忆化搜索+优化回文串判断
     *
     * @param s
     * @return
     */
    public int minCut4(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int len = s.length();

        //生成是否回文串dp表
        boolean[][] checkPalindrome = getCheckPalindorme(chars);

        //创建dp
        int[] dp = new int[len];
        Arrays.fill(dp, Integer.MAX_VALUE);

        return process4(chars, 0, checkPalindrome, dp);
    }

    /**
     * 获取校验是否回文串的 dp表
     *
     * @param chars
     * @return
     */
    private boolean[][] getCheckPalindorme(char[] chars) {
        int len = chars.length;
        boolean[][] checkPalindrome = new boolean[len][len];
        for (int i = 0; i < len - 1; i++) {
            checkPalindrome[i][i] = true;
            checkPalindrome[i][i + 1] = (chars[i] == chars[i + 1]);
        }
        checkPalindrome[len - 1][len - 1] = true;

        for (int i = len - 3; i >= 0; i--) {
            for (int j = i + 2; j < len; j++) {
                if (chars[i] == chars[j]) {
                    checkPalindrome[i][j] = checkPalindrome[i + 1][j - 1];
                }
            }
        }
        return checkPalindrome;
    }

    /**
     * 返回s[start....]后面需要切割的最小代价
     *
     * @param s
     * @param start
     * @param checkPalindrome
     * @param dp
     * @return
     */
    public int process4(char[] s, int start, boolean[][] checkPalindrome, int[] dp) {
        if (dp[start] != Integer.MAX_VALUE) {
            return dp[start];
        }
        if (start >= s.length - 1) {
            dp[start] = 0;
            return dp[start];
        }
        if (start == s.length - 2) {
            if (s[start] == s[start + 1]) {
                dp[start] = 0;
            } else {
                dp[start] = 1;
            }
            return dp[start];
        }
        int len = s.length;
        int min = len - start - 1;
        for (int end = start; end < len; end++) {
            if (checkPalindrome[start][end]) {
                if (end == len - 1) {
                    //说明当前start字符到字符串最后是回文串，那么当前的切割代价就为0
                    min = 0;
                } else {
                    //在end位置上切割一刀，然后后续递归求最小代价
                    min = Math.min(min, process4(s, end + 1, checkPalindrome, dp) + 1);
                }
            }
        }
        dp[start] = min;
        return dp[start];
    }

    public static void main(String[] args) {
        System.out.println(new Code02_PalindromePartitioningII().minCut5("coder"));
    }

    /**
     * 思路：
     * 优化回文串判断
     *
     * @param s
     * @return
     */
    public int minCut3(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int len = s.length();
        boolean[][] checkPalindrome = getCheckPalindorme(chars);

        return process3(chars, 0, checkPalindrome);
    }

    /**
     * 返回s[start....]后面需要切割的最小代价
     *
     * @param s
     * @param start
     * @param checkPalindrome
     * @return
     */
    public int process3(char[] s, int start, boolean[][] checkPalindrome) {
        if (start >= s.length - 1) {
            return 0;
        }
        if (start == s.length - 2) {
            return s[start] == s[start + 1] ? 0 : 1;
        }
        int len = s.length;
        int min = len - start - 1;
        for (int end = start; end < len; end++) {
            if (checkPalindrome[start][end]) {
                if (end == len - 1) {
                    //说明当前start字符到字符串最后是回文串，那么当前的切割代价就为0
                    min = 0;
                } else {
                    //在end位置上切割一刀，然后后续递归求最小代价
                    min = Math.min(min, process3(s, end + 1, checkPalindrome) + 1);
                }
            }
        }
        return min;
    }

    /**
     * 思路：
     * 暴力尝试：
     * 遍历每一个位置，
     * 先校验一下是否是回文，如果是，切割，剩下的那段走递归；
     *
     * @param s
     * @return
     */
    public int minCut2(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        return process2(s.toCharArray(), 0);
    }

    /**
     * 返回s[start....]后面需要切割的最小代价
     *
     * @param s
     * @param start
     * @return
     */
    public int process2(char[] s, int start) {
        if (start >= s.length - 1) {
            return 0;
        }
        if (start == s.length - 2) {
            return s[start] == s[start + 1] ? 0 : 1;
        }
        int len = s.length;
        int min = len - start - 1;
        for (int end = start; end < len; end++) {
            if (checkPalindrome(s, start, end)) {
                if (end == len - 1) {
                    //说明当前start字符到字符串最后是回文串，那么当前的切割代价就为0
                    min = 0;
                } else {
                    //在end位置上切割一刀，然后后续递归求最小代价
                    min = Math.min(min, process2(s, end + 1) + 1);
                }
            }
        }
        return min;
    }

    /**
     * 判断s[start...end]是否为回文串
     *
     * @param s
     * @param start
     * @param end
     * @return
     */
    private boolean checkPalindrome(char[] s, int start, int end) {
        if (start == end) {
            return true;
        }
        while (start < end) {
            if (s[start++] != s[end--]) {
                return false;
            }
        }
        return true;
    }


    // 测试链接只测了本题的第一问，直接提交可以通过
    public static int minCut(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        boolean[][] checkMap = createCheckMap(str, N);
        int[] dp = new int[N + 1];
        dp[N] = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (checkMap[i][N - 1]) {
                dp[i] = 1;
            } else {
                int next = Integer.MAX_VALUE;
                for (int j = i; j < N; j++) {
                    if (checkMap[i][j]) {
                        next = Math.min(next, dp[j + 1]);
                    }
                }
                dp[i] = 1 + next;
            }
        }
        return dp[0] - 1;
    }

    public static boolean[][] createCheckMap(char[] str, int N) {
        boolean[][] ans = new boolean[N][N];
        for (int i = 0; i < N - 1; i++) {
            ans[i][i] = true;
            ans[i][i + 1] = str[i] == str[i + 1];
        }
        ans[N - 1][N - 1] = true;
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                ans[i][j] = str[i] == str[j] && ans[i + 1][j - 1];
            }
        }
        return ans;
    }

    // 本题第二问，返回其中一种结果
    public static List<String> minCutOneWay(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() < 2) {
            ans.add(s);
        } else {
            char[] str = s.toCharArray();
            int N = str.length;
            boolean[][] checkMap = createCheckMap(str, N);
            int[] dp = new int[N + 1];
            dp[N] = 0;
            for (int i = N - 1; i >= 0; i--) {
                if (checkMap[i][N - 1]) {
                    dp[i] = 1;
                } else {
                    int next = Integer.MAX_VALUE;
                    for (int j = i; j < N; j++) {
                        if (checkMap[i][j]) {
                            next = Math.min(next, dp[j + 1]);
                        }
                    }
                    dp[i] = 1 + next;
                }
            }
            // dp[i]  (0....5) 回文！  dp[0] == dp[6] + 1
            //  (0....5)   6
            for (int i = 0, j = 1; j <= N; j++) {
                if (checkMap[i][j - 1] && dp[i] == dp[j] + 1) {
                    ans.add(s.substring(i, j));
                    i = j;
                }
            }
        }
        return ans;
    }

    // 本题第三问，返回所有结果
    public static List<List<String>> minCutAllWays(String s) {
        List<List<String>> ans = new ArrayList<>();
        if (s == null || s.length() < 2) {
            List<String> cur = new ArrayList<>();
            cur.add(s);
            ans.add(cur);
        } else {
            char[] str = s.toCharArray();
            int N = str.length;
            boolean[][] checkMap = createCheckMap(str, N);
            int[] dp = new int[N + 1];
            dp[N] = 0;
            for (int i = N - 1; i >= 0; i--) {
                if (checkMap[i][N - 1]) {
                    dp[i] = 1;
                } else {
                    int next = Integer.MAX_VALUE;
                    for (int j = i; j < N; j++) {
                        if (checkMap[i][j]) {
                            next = Math.min(next, dp[j + 1]);
                        }
                    }
                    dp[i] = 1 + next;
                }
            }
            process(s, 0, 1, checkMap, dp, new ArrayList<>(), ans);
        }
        return ans;
    }

    // s[0....i-1]  存到path里去了
    // s[i..j-1]考察的分出来的第一份
    public static void process(String s, int i, int j, boolean[][] checkMap, int[] dp,
                               List<String> path,
                               List<List<String>> ans) {
        if (j == s.length()) { // s[i...N-1]
            if (checkMap[i][j - 1] && dp[i] == dp[j] + 1) {
                path.add(s.substring(i, j));
                ans.add(copyStringList(path));
                path.remove(path.size() - 1);
            }
        } else {// s[i...j-1]
            if (checkMap[i][j - 1] && dp[i] == dp[j] + 1) {
                path.add(s.substring(i, j));
                process(s, j, j + 1, checkMap, dp, path, ans);
                path.remove(path.size() - 1);
            }
            process(s, i, j + 1, checkMap, dp, path, ans);
        }
    }

    public static List<String> copyStringList(List<String> list) {
        List<String> ans = new ArrayList<>();
        for (String str : list) {
            ans.add(str);
        }
        return ans;
    }

    public static void main2(String[] args) {
        String s = null;
        List<String> ans2 = null;
        List<List<String>> ans3 = null;

        System.out.println("本题第二问，返回其中一种结果测试开始");
        s = "abacbc";
        ans2 = minCutOneWay(s);
        for (String str : ans2) {
            System.out.print(str + " ");
        }
        System.out.println();

        s = "aabccbac";
        ans2 = minCutOneWay(s);
        for (String str : ans2) {
            System.out.print(str + " ");
        }
        System.out.println();

        s = "aabaa";
        ans2 = minCutOneWay(s);
        for (String str : ans2) {
            System.out.print(str + " ");
        }
        System.out.println();
        System.out.println("本题第二问，返回其中一种结果测试结束");
        System.out.println();
        System.out.println("本题第三问，返回所有可能结果测试开始");
        s = "cbbbcbc";
        ans3 = minCutAllWays(s);
        for (List<String> way : ans3) {
            for (String str : way) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
        System.out.println();

        s = "aaaaaa";
        ans3 = minCutAllWays(s);
        for (List<String> way : ans3) {
            for (String str : way) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
        System.out.println();

        s = "fcfffcffcc";
        ans3 = minCutAllWays(s);
        for (List<String> way : ans3) {
            for (String str : way) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("本题第三问，返回所有可能结果测试结束");
    }

}
