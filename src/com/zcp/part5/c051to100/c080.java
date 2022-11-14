package com.zcp.part5.c051to100;

/**
 * @author ZCP
 * @title: c080
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/remove-duplicates-from-sorted-array-ii/submissions/
 * @date 2022/8/30 15:33
 */
public class c080 {

    public int removeDuplicates(int[] nums) {
        int num = -1;
        int cnt = 0;
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] == num) {
                if (++cnt <= 2) {
                    nums[slow++] = nums[fast];
                }
            } else {
                nums[slow++] = nums[fast];
                num = nums[fast];
                cnt = 1;
            }
            fast++;
        }
        return slow;
    }


}
