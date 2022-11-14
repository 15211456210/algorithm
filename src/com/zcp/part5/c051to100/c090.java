package com.zcp.part5.c051to100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author ZCP
 * @title: c090
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/subsets-ii/submissions/
 * @date 2022/8/31 11:03
 */
public class c090 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        ArrayList<List<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        ans.add(list);
        String hash;
        for (int i = 0; i < nums.length; i++) {
            int size = ans.size();
            for (int n = 0; n < size; n++) {
                List<Integer> oList = new ArrayList<>(ans.get(n));
                oList.add(nums[i]);
                Object[] arr = oList.toArray();
                Arrays.sort(arr);
                hash = Arrays.toString(arr);
                if (!set.contains(hash)) {
                    set.add(hash);
                    ans.add(oList);
                }
            }
        }
        return ans;
    }


}
