package com.zcp.part5.c251to300;

/**
 * @author ZCP
 * @title: C278
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/first-bad-version/
 * @date 2022/9/25 12:04
 */
public class C278 {

    class VersionControl {
        boolean isBadVersion(int n) {
            return false;
        }
    }

    public class Solution extends VersionControl {
        public int firstBadVersion(int n) {

            int l = 1, r = n;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (isBadVersion(mid)) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }

            return l;
        }
    }
}
