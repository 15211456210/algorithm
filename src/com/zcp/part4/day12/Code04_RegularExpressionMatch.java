package com.zcp.part4.day12;

// 测试链接 : https://leetcode.com/problems/regular-expression-matching/
public class Code04_RegularExpressionMatch {

    /**
     * 思路：
     * 动态规划 样本对应
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch6(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int sLen = s.length();
        int pLen = p.length();
        char[] cs = s.toCharArray();
        char[] cp = p.toCharArray();
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        dp[sLen][pLen] = true;
        for (int i = sLen; i >= 0; i--) {
            for (int j = pLen - 1; j >= 0; j--) {
                if (j < pLen - 1 && cp[j + 1] == '*') {
                    if (i == sLen) {
                        //i已经都匹配结束了
                        dp[i][j] = dp[i][j + 2];
                    } else {
                        if (cs[i] != cp[j] && cp[j] != '.') {
                            dp[i][j] = dp[i][j + 2];
                        } else {
                            dp[i][j] = (dp[i][j + 2] || dp[i + 1][j] || dp[i + 1][j + 2]);
                        }
                    }
                } else {
                    if (i >= sLen) {
                        //i已经都匹配结束了
                        dp[i][j] = false;
                    } else {
                        if (cs[i] != cp[j] && cp[j] != '.') {
                            dp[i][j] = false;
                        } else {
                            dp[i][j] = dp[i + 1][j + 1];
                        }
                    }
                }
            }
        }
        return dp[0][0];
    }

    /**
     * 思路：
     * 动态规划 + 记忆化搜索
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch5(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        //0:没算过
        //1：true
        //-1：false
        int[][] dp = new int[s.length() + 1][p.length() + 1];
        return process5(s.toCharArray(), p.toCharArray(), 0, 0, dp);
    }

    /**
     * s[i...] 和 p[j...]能否匹配
     *
     * @param s
     * @param p
     * @param i
     * @param j
     * @param dp
     * @return
     */
    public boolean process5(char[] s, char[] p, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j] == 1;
        }
        if (i >= s.length && j >= p.length) {
            //p1：i,j都到走完了
            dp[i][j] = 1;
            return true;
        }
        if (i < s.length && j >= p.length) {
            dp[i][j] = -1;
            return false;
        }

        if (j < p.length - 1 && p[j + 1] == '*') {
            if (i >= s.length) {
                //i已经都匹配结束了
                return process5(s, p, i, j + 2, dp);
            } else {
                if (s[i] != p[j] && p[j] != '.') {
                    return process5(s, p, i, j + 2, dp);
                } else {
                    return process5(s, p, i, j + 2, dp) || process5(s, p, i + 1, j, dp) || process5(s, p, i + 1, j + 2, dp);
                }
            }
        } else {
            if (i >= s.length) {
                //i已经都匹配结束了
                dp[i][j] = -1;
                return false;
            } else {
                if (s[i] != p[j] && p[j] != '.') {
                    dp[i][j] = -1;
                    return false;
                } else {
                    return process5(s, p, i + 1, j + 1, dp);
                }
            }
        }
    }


    /**
     * 思路：
     * 动态规划 样本对应
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch4(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        return process4(s.toCharArray(), p.toCharArray(), 0, 0);
    }

    /**
     * s[i...] 和 p[j...]能否匹配
     *
     * @param s
     * @param p
     * @param i
     * @param j
     * @return
     */
    public boolean process4(char[] s, char[] p, int i, int j) {
        if (i >= s.length && j >= p.length) {
            //p1：i,j都到走完了
            return true;
        }
        if (i < s.length && j >= p.length) {
            return false;
        }
        if (j < p.length - 1 && p[j + 1] == '*') {
            if (i >= s.length) {
                //i已经都匹配结束了
                return process4(s, p, i, j + 2);
            } else {
                if (s[i] != p[j] && p[j] != '.') {
                    return process4(s, p, i, j + 2);
                } else {
                    return process4(s, p, i, j + 2) || process4(s, p, i + 1, j) || process4(s, p, i + 1, j + 2);
                }
            }
        } else {
            if (i >= s.length) {
                //i已经都匹配结束了
                return false;
            } else {
                if (s[i] != p[j] && p[j] != '.') {
                    return false;
                } else {
                    return process4(s, p, i + 1, j + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Code04_RegularExpressionMatch().isMatch4("aab", "c*a*b*"));
    }

    public static boolean isValid(char[] s, char[] e) {
        // s中不能有'.' or '*'
        for (int i = 0; i < s.length; i++) {
            if (s[i] == '*' || s[i] == '.') {
                return false;
            }
        }
        // 开头的e[0]不能是'*'，没有相邻的'*'
        for (int i = 0; i < e.length; i++) {
            if (e[i] == '*' && (i == 0 || e[i - 1] == '*')) {
                return false;
            }
        }
        return true;
    }

    // 初始尝试版本，不包含斜率优化
    public static boolean isMatch1(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        char[] s = str.toCharArray();
        char[] e = exp.toCharArray();
        return isValid(s, e) && process(s, e, 0, 0);
    }

    // str[si.....] 能不能被 exp[ei.....]配出来！ true false
    public static boolean process(char[] s, char[] e, int si, int ei) {
        if (ei == e.length) { // exp 没了 str？
            return si == s.length;
        }
        // exp[ei]还有字符
        // ei + 1位置的字符，不是*
        if (ei + 1 == e.length || e[ei + 1] != '*') {
            // ei + 1 不是*
            // str[si] 必须和 exp[ei] 能配上！
            return si != s.length && (e[ei] == s[si] || e[ei] == '.') && process(s, e, si + 1, ei + 1);
        }
        // exp[ei]还有字符
        // ei + 1位置的字符，是*!
        while (si != s.length && (e[ei] == s[si] || e[ei] == '.')) {
            if (process(s, e, si, ei + 2)) {
                return true;
            }
            si++;
        }
        return process(s, e, si, ei + 2);
    }

    // 改记忆化搜索+斜率优化
    public static boolean isMatch2(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        char[] s = str.toCharArray();
        char[] e = exp.toCharArray();
        if (!isValid(s, e)) {
            return false;
        }
        int[][] dp = new int[s.length + 1][e.length + 1];
        // dp[i][j] = 0, 没算过！
        // dp[i][j] = -1 算过，返回值是false
        // dp[i][j] = 1 算过，返回值是true
        return isValid(s, e) && process2(s, e, 0, 0, dp);
    }

    public static boolean process2(char[] s, char[] e, int si, int ei, int[][] dp) {
        if (dp[si][ei] != 0) {
            return dp[si][ei] == 1;
        }
        boolean ans = false;
        if (ei == e.length) {
            ans = si == s.length;
        } else {
            if (ei + 1 == e.length || e[ei + 1] != '*') {
                ans = si != s.length && (e[ei] == s[si] || e[ei] == '.') && process2(s, e, si + 1, ei + 1, dp);
            } else {
                if (si == s.length) { // ei ei+1 *
                    ans = process2(s, e, si, ei + 2, dp);
                } else { // si没结束
                    if (s[si] != e[ei] && e[ei] != '.') {
                        ans = process2(s, e, si, ei + 2, dp);
                    } else { // s[si] 可以和 e[ei]配上
                        ans = process2(s, e, si, ei + 2, dp) || process2(s, e, si + 1, ei, dp);
                    }
                }
            }
        }
        dp[si][ei] = ans ? 1 : -1;
        return ans;
    }

    // 动态规划版本 + 斜率优化
    public static boolean isMatch3(String str, String pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        char[] s = str.toCharArray();
        char[] p = pattern.toCharArray();
        if (!isValid(s, p)) {
            return false;
        }
        int N = s.length;
        int M = p.length;
        boolean[][] dp = new boolean[N + 1][M + 1];
        dp[N][M] = true;
        for (int j = M - 1; j >= 0; j--) {
            dp[N][j] = (j + 1 < M && p[j + 1] == '*') && dp[N][j + 2];
        }
        // dp[0..N-2][M-1]都等于false，只有dp[N-1][M-1]需要讨论
        if (N > 0 && M > 0) {
            dp[N - 1][M - 1] = (s[N - 1] == p[M - 1] || p[M - 1] == '.');
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j = M - 2; j >= 0; j--) {
                if (p[j + 1] != '*') {
                    dp[i][j] = ((s[i] == p[j]) || (p[j] == '.')) && dp[i + 1][j + 1];
                } else {
                    if ((s[i] == p[j] || p[j] == '.') && dp[i + 1][j]) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i][j + 2];
                    }
                }
            }
        }
        return dp[0][0];
    }

}
