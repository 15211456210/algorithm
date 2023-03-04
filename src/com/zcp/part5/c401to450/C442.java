package com.zcp.part5.c401to450;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZCP
 * @title: C442
 * @projectName algorithm
 * @description: https://leetcode.com/problems/find-all-duplicates-in-an-array/
 * @date 2023/2/1 13:39
 */
public class C442 {

    public List<Integer> findDuplicates(int[] nums) {
        int length = nums.length;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if (nums[i] == i + 1) {
                continue;
            }
            int holdNum = nums[i];
            nums[i] = 0;
            while (holdNum != 0) {
                if (holdNum == nums[holdNum - 1]) {
                    ans.add(holdNum);
                    break;
                } else {
                    int next = nums[holdNum - 1];
                    nums[holdNum - 1] = holdNum;
                    holdNum = next;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new C442().findDuplicates(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }
}
