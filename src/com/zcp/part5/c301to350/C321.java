package com.zcp.part5.c301to350;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author ZCP
 * @title: C321
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/create-maximum-number/
 * @date 2022/10/4 1:16
 */
public class C321 {


    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] ans = new int[k];
        int[] help1, help2;
        for (int i = 0; i <= k; i++) {
            int j = k - i; // 第二个数组的大小
            if (i > nums1.length || j > nums2.length) {
                continue;
            }
            int[] nNum1 = getNNum(nums1, i);
            int[] nNum2 = getNNum(nums2, j);

            int idx1 = 0, idx2 = 0;
            int idx = 0;
            int[] tem = new int[k];
            while (idx1 < i || idx2 < j || idx < k) {
                int n1 = (idx1 < i) ? nNum1[idx1] : -1;
                int n2 = (idx2 < j) ? nNum2[idx2] : -1;
                if (n1 > n2) {
                    tem[idx++] = nNum1[idx1++];
                } else if (n1 < n2) {
                    tem[idx++] = nNum2[idx2++];
                } else {
                    if (compare(nNum1, idx1 + 1, nNum2, idx2 + 1) >= 0) {
                        tem[idx++] = nNum1[idx1++];
                    } else {
                        tem[idx++] = nNum2[idx2++];
                    }
                }
            }
            System.out.println();
            for (int n = 0; n < k; n++) {
                if (tem[n] == ans[n]) {
                    continue;
                } else if (tem[n] > ans[n]) {
                    ans = tem;
                }
                break;
            }
        }

        return ans;
    }


    public int compare(int[] subsequence1, int index1, int[] subsequence2, int index2) {
        int x = subsequence1.length, y = subsequence2.length;
        while (index1 < x && index2 < y) {
            int difference = subsequence1[index1] - subsequence2[index2];
            if (difference != 0) {
                return difference;
            }
            index1++;
            index2++;
        }
        return (x - index1) - (y - index2);
    }

//
//    private int compare(int[] nums1, int i, int[] nums2, int j) {
//        while (i < nums1.length || j < nums2.length) {
//            int n1 = i < nums1.length ? nums1[i] : -1;
//            int n2 = j < nums2.length ? nums2[j] : -1;
//            if (n1 > n2) {
//                return 1;
//            } else if (n1 < n2) {
//                return -1;
//            }
//        }
//        return 0;
//    }

    /**
     * @param nums
     * @param n
     * @return ans[n] 最大序列
     * ans[1][i] i 右侧最大的数
     */
    private int[] getNNum(int[] nums, int n) {
        int[] ans = new int[n];

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < nums.length; i++) {
            int remain = nums.length - i;
            while (!stack.isEmpty() && (stack.size() + remain > n) && stack.peek() < nums[i]) {
                stack.pop();
            }
            if (stack.size() < n) {
                stack.push(nums[i]);
            }
        }
        int maxLeft = -1;
        for (int i = n - 1; i >= 0; i--) {
            ans[i] = stack.pop();
            maxLeft = Math.max(maxLeft, ans[i]);
        }
        return ans;
    }

//    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
//        return maxNumber(nums1, nums2, 0, 0, k);
//    }
//
//    public int[] maxNumber(int[] nums1, int[] nums2, int i, int j, int k) {
//        if (k <= 0) {
//            return null;
//        }
//        int idx = 0;
//        int remain1 = Math.max(0, nums1.length - i);
//        int remain2 = Math.max(0, nums2.length - j);
//        int remain = remain1 + remain2;
//        if (k > remain) {
//            // 剩余的数不够
//            return null;
//        }
//        if (k == remain) {
//            int[] ans = new int[k];
//            int idx1 = i;
//            int idx2 = j;
//            while (idx1 < nums1.length || idx2 < nums2.length) {
//                int num1 = idx1 < nums1.length ? nums1[idx1] : -1;
//                int num2 = idx2 < nums2.length ? nums2[idx2] : -1;
//                if (num1 > num2) {
//                    ans[idx++] = num1;
//                    idx1++;
//                } else {
//                    ans[idx++] = num2;
//                    idx2++;
//                }
//            }
//            return ans;
//        }
//
//        // k < remain 剩余的数足够
//        // p1 不选择 i
//        int[] p1 = null;
//        if (i < nums1.length) {
//            p1 = maxNumber(nums1, nums2, i + 1, j, k);
//        }
//
//        // p2 不选择 j
//        int[] p2 = null;
//        if (j < nums2.length) {
//            p2 = maxNumber(nums1, nums2, i, j + 1, k);
//        }
//
//        // p3 选择 i
//        int[] p3 = null;
//        if (i < nums1.length) {
//            p3 = new int[k];
//            p3 = nums1[i];
//            int[] p3x = maxNumber(nums1, nums2, i + 1, j, k - 1);
//            if (p3x != null) {
//                System.arraycopy(p3x, 0, p3, 1, k - 1);
//            }
//        }
//
//        // p4 选择 j
//        int[] p4 = null;
//        if (j < nums2.length) {
//            p4 = new int[k];
//            p4 = nums2[j];
//            int[] p4x = maxNumber(nums1, nums2, i, j + 1, k - 1);
//            if (p4x != null) {
//                System.arraycopy(p4x, 0, p4, 1, k - 1);
//            }
//        }
//
//        int[] ans = new int[k];
//        Arrays.fill(ans, 0);
//
//
//        if (p1 != null) {
//            for (int x = 0; x < k; x++) {
//                if (p1[x] > ans[x]) {
//                    ans = p1;
//                    break;
//                }
//            }
//        }
//        if (p2 != null) {
//            for (int x = 0; x < k; x++) {
//                if (p2[x] > ans[x]) {
//                    ans = p2;
//                    break;
//                }
//            }
//        }
//
//        if (p3 != null) {
//            for (int x = 0; x < k; x++) {
//                if (p3[x] > ans[x]) {
//                    ans = p3;
//                    break;
//                }
//            }
//        }
//
//        if (p4 != null) {
//            for (int x = 0; x < k; x++) {
//                if (p4[x] > ans[x]) {
//                    ans = p4;
//                    break;
//                }
//            }
//        }
//        return ans;
//    }

    public static void main(String[] args) {
        //{3, 4, 6, 5}  {9, 1, 2, 5, 8, 3}
        //       i                      j
        // {9,8,6}
        System.out.println(Arrays.toString(new C321().maxNumber(new int[]{6, 7, 5}, new int[]{4, 8, 1}, 3)));
    }
}
