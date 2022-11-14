package com.zcp.part5.c351to400;

/**
 * @author ZCP
 * @title: C383
 * @projectName algorithm
 * @description: https://leetcode.com/problems/ransom-note/
 * @date 2022/10/31 10:53
 */
public class C383 {

    private int[] map = new int[26];

    public boolean canConstruct(String ransomNote, String magazine) {
        for (int i = 0; i < magazine.length(); i++) {
            map[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            if (--map[ransomNote.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;

    }
}
