package com.zcp.part5.c001to050;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZCP
 * @title: c001
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/two-sum/
 * @date 2022/8/25 11:26
 */
public class c001 {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> indexMap = new HashMap<Integer,Integer>();
        for(int i = 0;i<nums.length;i++){
            if(indexMap.get(nums[i]) != null){
                return new int[]{i,indexMap.get(nums[i])};
            }
            indexMap.put(target-nums[i],i);
        }
        return null;
    }

}
