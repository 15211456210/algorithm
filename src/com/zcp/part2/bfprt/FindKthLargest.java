package com.zcp.part2.bfprt;

/**
 * @author ：ZCP
 * @date ：Created in 2021/10/18
 * @description：[[在无序数组中求第K小的数]] https://leetcode-cn.com/problems/xx4gT2/
 * @version:
 */
public class FindKthLargest {

    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return -1;
        }
        return getRangeK(nums, 0, nums.length - 1, k - 1);
    }

    /**
     * 在 L-R范围上找到第K大的数
     *
     * @param nums
     * @param L
     * @param R
     * @param k
     * @return
     */
    private int getRangeK(int[] nums, int L, int R, int k) {
        if (L == R) {
            return nums[L];
        }
        int[] range = partition(nums, L, R);
        if (range[0] > k) {
            return getRangeK(nums, L, range[0] - 1, k);
        } else if (range[1] < k) {
            return getRangeK(nums, range[1] + 1, R, k);
        } else {
            return nums[k];
        }
    }

    private int[] partition(int[] nums, int l, int r) {
//        int p = nums[r];
        //bfprt 获取P
        int p = getPAndSwap(nums, l, r);
        int index = l, L = l - 1, R = r + 1;
        while (index < R) {
            if (nums[index] > p) {
                swap(nums, index++, ++L);
            } else if (nums[index] < p) {
                swap(nums, index, --R);
            } else {
                index++;
            }
        }
//        swap(nums, index, r);
        return new int[]{L + 1, R - 1};
    }

    /**
     * bfprt算法核心，p值选取
     * 每5个一组，选取中位数
     *
     * @param nums
     * @param l
     * @param r
     * @return
     */
    private int getPAndSwap(int[] nums, int l, int r) {
        int step = 5;
        int[] ans = new int[(r - l) / step + 1];
        int index = 0;
        for (int cur = l; cur < r; cur = cur + step) {
            for (int i = cur; i < cur + 5 && i <= r; i++) {
                int j = i;
                for (; j > cur && nums[j] <= nums[j - 1]; j--) {
                    swap(nums, j, j - 1);
                }

            }
            ans[index++] = nums[cur + (cur + step < r ? step / 2 : (r - cur) / 2)];
        }
        return ans[(ans.length - 1) / 2];
    }

    private void swap(int[] nums, int index1, int index2) {
        int num = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = num;
    }

    public static void main(String[] args) {
        FindKthLargest largest = new FindKthLargest();
        System.out.println(largest.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
    }

}
