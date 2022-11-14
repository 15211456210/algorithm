package com.zcp.part5.c351to400;

/**
 * @author ZCP
 * @title: C387
 * @projectName algorithm
 * @description: https://leetcode.com/problems/first-unique-character-in-a-string/
 * @date 2022/11/2 10:46
 */
public class C387 {

    public int firstUniqChar(String s) {
        int[] chars = new int[26];
        int[] index = new int[26];
        for(int i = 0;i<s.length();++i){
            int idx = s.charAt(i) - 'a';
            if(chars[idx] == 0){
                index[idx] = i;
            }
            chars[idx]++;
        }
        int ans = Integer.MAX_VALUE;
        for(int i = 0;i<26;i++){
            if(chars[i] == 1){
                ans = Math.min(ans,index[i]);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
