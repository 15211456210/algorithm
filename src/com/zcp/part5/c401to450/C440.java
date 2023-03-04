package com.zcp.part5.c401to450;

/**
 * @author ZCP
 * @title: C440
 * @projectName algorithm
 * @description: https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/
 * @date 2023/1/31 20:25
 */
public class C440 {
    public int findKthNumber(int n, int k) {
        long cur = 1;
        k--;
        while (k > 0) {
            int cnt = getNodeCnt(cur, n);
            if (k < cnt) {
                k--;
                cur *= 10;
            } else {
                k -= cnt;
                cur++;
            }
        }
        return (int) cur;
    }

    private int getNodeCnt(long cur, int n) {
        int count = 0;
        int ten = 1;
        while (cur * ten <= n) {
            count += Math.min(ten, n - cur * ten + 1);
            ten *= 10;
        }
        return count;
    }


//    public int findKthNumber(int n, int k) {
//        int ans = 0;
//        while (k > 0) {
//            for (int cNum = 1; cNum <= 9 && k > 0; cNum++) {
//                if (k == 1) {
//                    return ans + cNum;
//                }
//                int ten = 1;
//                int left = cNum * ten;
//                if (left > n) {
//                    continue;
//                }
//                k--;
//                while (left * 10 < n && k > 0) {
//                    ten *= 10;
//                    left = cNum * ten;
//                    int rangeNumCnt = Math.min(ten, n - left + 1);
//                    if (rangeNumCnt == k) {
//                        return left + rangeNumCnt - 1;
//                    }
//                    if (rangeNumCnt < k) {
//                        k -= rangeNumCnt;
//                        continue;
//                    }
//                    if (rangeNumCnt > k) {
//                        // 可以确认最高位的数字
//                        ans += left;
//                        return ans + findKthNumber(rangeNumCnt - 1, k - 1);
//                    }
//                }
//            }
//        }
//
//        return ans;
//    }

    public static void main(String[] args) {
        System.out.println(new C440().findKthNumber(885, 137));
//        System.out.println(new C440().findKthNumber(13, 2));
//        System.out.println(new C440().findKthNumber(88485, 137));
    }
}
