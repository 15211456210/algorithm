package com.zcp.part4.day10;

// 本题测试链接 : https://leetcode-cn.com/problems/boolean-evaluation-lcci/
public class Code05_BooleanEvaluation {


    /**
     * 思路：
     * 动态规划+记忆化搜索
     *
     * @param s
     * @param result
     * @return
     */
    public int countEval3(String s, int result) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        int len = s.length();
        int[][][] dp = new int[len][len][2];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                dp[i][j][0] = -1;
                dp[i][j][1] = -1;
            }
        }
        return process3(s.toCharArray(), 0, len - 1, result, dp);
    }

    /**
     * s[left...right] 范围添加括号，计算出result结果的（）总排列个数
     *
     * @param s
     * @param left
     * @param right
     * @param result
     * @param dp
     * @return
     */
    public int process3(char[] s, int left, int right, int result, int[][][] dp) {
        if (dp[left][right][result] != -1) {
            return dp[left][right][result];
        }
        if (left == right) {
            dp[left][right][result] = (Character.getNumericValue(s[left]) == result) ? 1 : 0;;
            return dp[left][right][result];
        }
        int ans = 0;
        //遍历每个逻辑符号的位置，计算所有情况
        for (int i = left + 1; i < right; i = i + 2) {
            //假设s[i]逻辑运算符最后计算
            int left0Count = process3(s, left, i - 1, 0, dp);
            int left1Count = process3(s, left, i - 1, 1, dp);
            int right0Count = process3(s, i + 1, right, 0, dp);
            int right1Count = process3(s, i + 1, right, 1, dp);
            switch (s[i]) {
                case '|':
                    if (result == 0) {
                        //0|0=0
                        ans += left0Count * right0Count;
                    } else {
                        //1|0,0|1,1|1
                        ans += left1Count * right0Count;
                        ans += left0Count * right1Count;
                        ans += left1Count * right1Count;
                    }
                    break;
                case '&':
                    if (result == 0) {
                        //0&0, 1&0,0&1
                        ans += left0Count * right0Count;
                        ans += left1Count * right0Count;
                        ans += left0Count * right1Count;
                    } else {
                        //1&1
                        ans += left1Count * right1Count;
                    }
                    break;
                case '^':
                    if (result == 0) {
                        //0^0, 1^1
                        ans += left0Count * right0Count;
                        ans += left1Count * right1Count;
                    } else {
                        //1^0,0^1
                        ans += left1Count * right0Count;
                        ans += left0Count * right1Count;
                    }
                    break;
            }
        }
        dp[left][right][result] = ans;
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Code05_BooleanEvaluation().countEval3("0|1", 1));
    }

    public static int countEval0(String express, int desired) {
        if (express == null || express.equals("")) {
            return 0;
        }
        char[] exp = express.toCharArray();
        int N = exp.length;
        Info[][] dp = new Info[N][N];
        Info allInfo = func(exp, 0, exp.length - 1, dp);
        return desired == 1 ? allInfo.t : allInfo.f;
    }

    public static class Info {
        public int t;
        public int f;

        public Info(int tr, int fa) {
            t = tr;
            f = fa;
        }
    }

    // 限制:
    // L...R上，一定有奇数个字符
    // L位置的字符和R位置的字符，非0即1，不能是逻辑符号！
    // 返回str[L...R]这一段，为true的方法数，和false的方法数
    public static Info func(char[] str, int L, int R, Info[][] dp) {
        if (dp[L][R] != null) {
            return dp[L][R];
        }
        int t = 0;
        int f = 0;
        if (L == R) {
            t = str[L] == '1' ? 1 : 0;
            f = str[L] == '0' ? 1 : 0;
        } else { // L..R >=3
            // 每一个种逻辑符号，split枚举的东西
            // 都去试试最后结合
            for (int split = L + 1; split < R; split += 2) {
                Info leftInfo = func(str, L, split - 1, dp);
                Info rightInfo = func(str, split + 1, R, dp);
                int a = leftInfo.t;
                int b = leftInfo.f;
                int c = rightInfo.t;
                int d = rightInfo.f;
                switch (str[split]) {
                    case '&':
                        t += a * c;
                        f += b * c + b * d + a * d;
                        break;
                    case '|':
                        t += a * c + a * d + b * c;
                        f += b * d;
                        break;
                    case '^':
                        t += a * d + b * c;
                        f += a * c + b * d;
                        break;
                }
            }

        }
        dp[L][R] = new Info(t, f);
        return dp[L][R];
    }

    public static int countEval1(String express, int desired) {
        if (express == null || express.equals("")) {
            return 0;
        }
        char[] exp = express.toCharArray();
        return f(exp, desired, 0, exp.length - 1);
    }

    public static int f(char[] str, int desired, int L, int R) {
        if (L == R) {
            if (str[L] == '1') {
                return desired;
            } else {
                return desired ^ 1;
            }
        }
        int res = 0;
        if (desired == 1) {
            for (int i = L + 1; i < R; i += 2) {
                switch (str[i]) {
                    case '&':
                        res += f(str, 1, L, i - 1) * f(str, 1, i + 1, R);
                        break;
                    case '|':
                        res += f(str, 1, L, i - 1) * f(str, 0, i + 1, R);
                        res += f(str, 0, L, i - 1) * f(str, 1, i + 1, R);
                        res += f(str, 1, L, i - 1) * f(str, 1, i + 1, R);
                        break;
                    case '^':
                        res += f(str, 1, L, i - 1) * f(str, 0, i + 1, R);
                        res += f(str, 0, L, i - 1) * f(str, 1, i + 1, R);
                        break;
                }
            }
        } else {
            for (int i = L + 1; i < R; i += 2) {
                switch (str[i]) {
                    case '&':
                        res += f(str, 0, L, i - 1) * f(str, 1, i + 1, R);
                        res += f(str, 1, L, i - 1) * f(str, 0, i + 1, R);
                        res += f(str, 0, L, i - 1) * f(str, 0, i + 1, R);
                        break;
                    case '|':
                        res += f(str, 0, L, i - 1) * f(str, 0, i + 1, R);
                        break;
                    case '^':
                        res += f(str, 1, L, i - 1) * f(str, 1, i + 1, R);
                        res += f(str, 0, L, i - 1) * f(str, 0, i + 1, R);
                        break;
                }
            }
        }
        return res;
    }

    public static int countEval2(String express, int desired) {
        if (express == null || express.equals("")) {
            return 0;
        }
        char[] exp = express.toCharArray();
        int N = exp.length;
        int[][][] dp = new int[2][N][N];
        dp[0][0][0] = exp[0] == '0' ? 1 : 0;
        dp[1][0][0] = dp[0][0][0] ^ 1;
        for (int i = 2; i < exp.length; i += 2) {
            dp[0][i][i] = exp[i] == '1' ? 0 : 1;
            dp[1][i][i] = exp[i] == '0' ? 0 : 1;
            for (int j = i - 2; j >= 0; j -= 2) {
                for (int k = j; k < i; k += 2) {
                    if (exp[k + 1] == '&') {
                        dp[1][j][i] += dp[1][j][k] * dp[1][k + 2][i];
                        dp[0][j][i] += (dp[0][j][k] + dp[1][j][k]) * dp[0][k + 2][i] + dp[0][j][k] * dp[1][k + 2][i];
                    } else if (exp[k + 1] == '|') {
                        dp[1][j][i] += (dp[0][j][k] + dp[1][j][k]) * dp[1][k + 2][i] + dp[1][j][k] * dp[0][k + 2][i];
                        dp[0][j][i] += dp[0][j][k] * dp[0][k + 2][i];
                    } else {
                        dp[1][j][i] += dp[0][j][k] * dp[1][k + 2][i] + dp[1][j][k] * dp[0][k + 2][i];
                        dp[0][j][i] += dp[0][j][k] * dp[0][k + 2][i] + dp[1][j][k] * dp[1][k + 2][i];
                    }
                }
            }
        }
        return dp[desired][0][N - 1];
    }

}
