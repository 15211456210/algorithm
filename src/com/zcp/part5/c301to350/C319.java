package com.zcp.part5.c301to350;

/**
 * @author ZCP
 * @title: C319
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/bulb-switcher/
 * @date 2022/10/3 23:49
 */
public class C319 {

    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }

    public int bulbSwitch2(int n) {
        boolean[] arr = new boolean[n];
        for (int step = 1; step <= n; step++) {
            int idx = step - 1;
            while (idx < n) {
                arr[idx] = !arr[idx];
                idx += step;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += (arr[i] ? 1 : 0);
            System.out.print((arr[i] ? 1 : 0) + " ");
        }
        System.out.println();
        System.out.println(ans);
        return ans;
    }


    public static void main(String[] args) {
        for (int i = 1; i <= 49; i++) {
            new C319().bulbSwitch2(i);
            System.out.println();
        }

    }
}
