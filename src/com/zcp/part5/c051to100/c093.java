package com.zcp.part5.c051to100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZCP
 * @title: c093
 * @projectName algorithm
 * @description: https://leetcode.com/problems/restore-ip-addresses/
 * @date 2022/8/31 19:16
 */
public class c093 {

    public List<String> restoreIpAddresses(String s) {
        ArrayList<String> ans = new ArrayList<>();

        process(s, 0, 4, ans, "", -1);

        return ans;
    }

    public void process(String s, int i, int remain, List<String> ans, String path, int pre) {
        if (i == s.length()) {
            if (remain == 0 && pre == -1) {
                ans.add(path.substring(1, path.length()));
            } else if (remain == 1 && pre >= 0 && pre < 256) {
                ans.add(path.substring(1, path.length()) + "." + pre);
            }
        } else {

            if (pre == 0) {
                process(s, i, remain - 1, ans, path + ".0", -1);
            } else if (pre == -1) {
                process(s, i + 1, remain, ans, path, s.charAt(i) - '0');
            } else {
                if (pre >= 256) {
                    return;
                }
                process(s, i, remain - 1, ans, path + "." + pre, -1);
                process(s, i + 1, remain, ans, path, pre * 10 + (s.charAt(i) - '0'));
            }

        }
    }

}
