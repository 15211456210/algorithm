package com.zcp.part4.day08;

import java.util.LinkedList;
import java.util.Stack;

// 本题测试链接 : https://leetcode.com/problems/basic-calculator-iii/
public class Code01_ExpressionCompute {


    public static void main(String[] args) {
        int i = new Code01_ExpressionCompute().calculate2("2-3+30");
    }

    /**
     * 思路：
     * 栈+递归
     *
     * @param s
     * @return
     */
    public int calculate2(String s) {
        return process(s.toCharArray(), 0)[0];
    }

    /**
     * str[i] 往后计算到第一个右括号（或则str结束位置）结束
     * 返回int[0]:结果值，int[1]结束的位置
     *
     * @param str
     * @param i
     * @return
     */
    public int[] process(char[] str, int i) {

        Stack<String> stack = new Stack<>();
        int curNum = 0;
        while (i < str.length && str[i] != ')') {
            if (str[i] == '(') {
                int[] nextAns = process(str, i + 1);
                curNum = nextAns[0];
                i = nextAns[1];
            } else if (str[i] == '*' || str[i] == '/') {
                while (!stack.isEmpty() && (stack.peek().equals("*") || stack.peek().equals("/"))) {
                    String calculatingSigns = stack.pop();
                    String num = stack.pop();
                    if (calculatingSigns.equals("*")) {
                        curNum = Integer.valueOf(num) * curNum;
                    } else if (calculatingSigns.equals("/")) {
                        curNum = Integer.valueOf(num) / curNum;
                    }
                }
                stack.push(curNum + "");
                curNum = 0;
                stack.push(str[i] + "");
            } else if (str[i] == '+' || str[i] == '-') {
                //判断栈顶元素是否是 * /，优先计算* /
                while (!stack.isEmpty() && (stack.peek().equals("*") || stack.peek().equals("/") || stack.peek().equals("-"))) {
                    String calculatingSigns = stack.pop();
                    String num = stack.pop();
                    if (calculatingSigns.equals("*")) {
                        curNum = Integer.valueOf(num) * curNum;
                    } else if (calculatingSigns.equals("/")) {
                        curNum = Integer.valueOf(num) / curNum;
                    } else if (calculatingSigns.equals("-")) {
                        curNum = Integer.valueOf(num) - curNum;
                    }
                }
                stack.push(curNum + "");
                curNum = 0;
                stack.push(str[i] + "");
            } else {
                //如果是数字
                curNum = curNum * 10 + Integer.valueOf(str[i] + "");
            }
            i++;
        }

        //计算结果
        int ans = curNum;
        while (!stack.isEmpty()) {
            //栈中只会剩下+，-，数字
            if (stack.peek().equals("+")) {
                stack.pop();
                ans += Integer.valueOf(stack.pop());
            } else if (stack.peek().equals("-")) {
                stack.pop();
                ans = Integer.valueOf(stack.pop()) - ans;
            } else if (stack.peek().equals("*")) {
                stack.pop();
                ans *= Integer.valueOf(stack.pop());
            } else if (stack.peek().equals("/")) {
                stack.pop();
                ans = Integer.valueOf(stack.pop()) / ans;
            }
        }
        return new int[]

                {
                        ans, i
                }

                ;
    }


    public static int calculate(String str) {
        return f(str.toCharArray(), 0)[0];
    }

    // 请从str[i...]往下算，遇到字符串终止位置或者右括号，就停止
    // 返回两个值，长度为2的数组
    // 0) 负责的这一段的结果是多少
    // 1) 负责的这一段计算到了哪个位置
    public static int[] f(char[] str, int i) {
        LinkedList<String> que = new LinkedList<String>();
        int cur = 0;
        int[] bra = null;
        // 从i出发，开始撸串
        while (i < str.length && str[i] != ')') {
            if (str[i] >= '0' && str[i] <= '9') {
                cur = cur * 10 + str[i++] - '0';
            } else if (str[i] != '(') { // 遇到的是运算符号
                addNum(que, cur);
                que.addLast(String.valueOf(str[i++]));
                cur = 0;
            } else { // 遇到左括号了
                bra = f(str, i + 1);
                cur = bra[0];
                i = bra[1] + 1;
            }
        }
        addNum(que, cur);
        return new int[]{getNum(que), i};
    }

    public static void addNum(LinkedList<String> que, int num) {
        if (!que.isEmpty()) {
            int cur = 0;
            String top = que.pollLast();
            if (top.equals("+") || top.equals("-")) {
                que.addLast(top);
            } else {
                cur = Integer.valueOf(que.pollLast());
                num = top.equals("*") ? (cur * num) : (cur / num);
            }
        }
        que.addLast(String.valueOf(num));
    }

    public static int getNum(LinkedList<String> que) {
        int res = 0;
        boolean add = true;
        String cur = null;
        int num = 0;
        while (!que.isEmpty()) {
            cur = que.pollFirst();
            if (cur.equals("+")) {
                add = true;
            } else if (cur.equals("-")) {
                add = false;
            } else {
                num = Integer.valueOf(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }

}
