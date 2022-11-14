package com.zcp.part5.c001to050;

/**
 * @author ZCP
 * @title: c038
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/count-and-say/submissions/
 * @date 2022/8/27 10:22
 */
public class c038 {

    public String countAndSay(int n) {
        String ans = "1";
        for (int i = 2; i <= n; i++) {
            String tmp = "";
            int idx = 0;
            while (idx < ans.length()) {
                int count = 1;
                while (idx + 1 < ans.length() && ans.charAt(idx + 1) == ans.charAt(idx)) {
                    idx++;
                    count++;
                }
                tmp += count + "" + ans.charAt(idx);
                idx++;
            }
            ans = tmp;
        }
        return ans;
    }
}
