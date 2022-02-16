package com.zcp.part4.day12;

// 本题测试链接 : https://leetcode.com/problems/median-of-two-sorted-arrays/
public class Code03_FindKthMinNumber {


    /**
     * 双指针
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        if (nums1 == null && nums2 == null) {
            return -1;
        }
        int len1 = nums1.length;
        int len2 = nums2.length;
        int totalLen = len1 + len2;
        int[] midIndex = new int[2];
        midIndex[0] = -1;
        midIndex[1] = -1;
        if (totalLen % 2 != 0) {
            //奇数
            midIndex[0] = totalLen / 2;
        } else {
            //偶数
            midIndex[0] = totalLen / 2 - 1;
            midIndex[1] = totalLen / 2;
        }
        double ans = 0;
        int p1 = 0, p2 = 0;
        int count = 0;
        while (count < totalLen) {
            int num = 0;
            if (p1 < len1 && p2 < len2) {
                if (nums1[p1] <= nums2[p2]) {
                    num = nums1[p1];
                    p1++;
                } else {
                    num = nums2[p2];
                    p2++;
                }
            } else if (p1 >= len1) {
                num = nums2[p2];
                p2++;
            } else {
                //(p2 >= len2)\
                num = nums1[p1];
                p1++;
            }

            count++;
            if (count - 1 == midIndex[0]) {
                ans += num;
                if (midIndex[1] == -1) {
                    return ans;
                }
            } else if (count - 1 == midIndex[1]) {
                //偶数情况
                ans += num;
                return ans / 2;
            }
        }
        return 0;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size = nums1.length + nums2.length;
        boolean even = (size & 1) == 0;
        if (nums1.length != 0 && nums2.length != 0) {
            if (even) {
                return (double) (findKthNum(nums1, nums2, size / 2) + findKthNum(nums1, nums2, size / 2 + 1)) / 2D;
            } else {
                return findKthNum(nums1, nums2, size / 2 + 1);
            }
        } else if (nums1.length != 0) {
            if (even) {
                return (double) (nums1[(size - 1) / 2] + nums1[size / 2]) / 2;
            } else {
                return nums1[size / 2];
            }
        } else if (nums2.length != 0) {
            if (even) {
                return (double) (nums2[(size - 1) / 2] + nums2[size / 2]) / 2;
            } else {
                return nums2[size / 2];
            }
        } else {
            return 0;
        }
    }

    // 进阶问题 : 在两个都有序的数组中，找整体第K小的数
    // 可以做到O(log(Min(M,N)))
    public static int findKthNum(int[] arr1, int[] arr2, int kth) {
        int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
        int[] shorts = arr1.length < arr2.length ? arr1 : arr2;
        int l = longs.length;
        int s = shorts.length;
        if (kth <= s) { // 1)
            return getUpMedian(shorts, 0, kth - 1, longs, 0, kth - 1);
        }
        if (kth > l) { // 3)
            if (shorts[kth - l - 1] >= longs[l - 1]) {
                return shorts[kth - l - 1];
            }
            if (longs[kth - s - 1] >= shorts[s - 1]) {
                return longs[kth - s - 1];
            }
            return getUpMedian(shorts, kth - l, s - 1, longs, kth - s, l - 1);
        }
        // 2)  s < k <= l
        if (longs[kth - s - 1] >= shorts[s - 1]) {
            return longs[kth - s - 1];
        }
        return getUpMedian(shorts, 0, s - 1, longs, kth - s, kth - 1);
    }


    // A[s1...e1]
    // B[s2...e2]
    // 一定等长！
    // 返回整体的，上中位数！8（4） 10（5） 12（6）
    public static int getUpMedian(int[] A, int s1, int e1, int[] B, int s2, int e2) {
        int mid1 = 0;
        int mid2 = 0;
        while (s1 < e1) {
            // mid1 = s1 + (e1 - s1) >> 1
            mid1 = (s1 + e1) / 2;
            mid2 = (s2 + e2) / 2;
            if (A[mid1] == B[mid2]) {
                return A[mid1];
            }
            // 两个中点一定不等！
            if (((e1 - s1 + 1) & 1) == 1) { // 奇数长度
                if (A[mid1] > B[mid2]) {
                    if (B[mid2] >= A[mid1 - 1]) {
                        return B[mid2];
                    }
                    e1 = mid1 - 1;
                    s2 = mid2 + 1;
                } else { // A[mid1] < B[mid2]
                    if (A[mid1] >= B[mid2 - 1]) {
                        return A[mid1];
                    }
                    e2 = mid2 - 1;
                    s1 = mid1 + 1;
                }
            } else { // 偶数长度
                if (A[mid1] > B[mid2]) {
                    e1 = mid1;
                    s2 = mid2 + 1;
                } else {
                    e2 = mid2;
                    s1 = mid1 + 1;
                }
            }
        }
        return Math.min(A[s1], B[s2]);
    }

}
