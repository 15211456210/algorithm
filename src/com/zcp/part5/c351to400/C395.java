package com.zcp.part5.c351to400;


/**
 * @author ZCP
 * @title: C395
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/longest-substring-with-at-least-k-repeating-characters/description/
 * @date 2022/11/11 14:26
 */
public class C395 {
    /**
     * 100%
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring(String s, int k) {
        int len = s.length();
        if (k == 1) {
            return len;
        }
        int[] cnt = new int[26];
        // 有多少种字母不满足k
        int vc = 0;
        for (int i = 0; i < len; i++) {
            int index = s.charAt(i) - 'a';
            cnt[index]++;
            vc += (cnt[index] == 1) ? 1 : (cnt[index] == k ? -1 : 0);
        }
        if (vc == 0) {
            return len;
        }

        int max = 0;
        int start = 0;
        for (int i = 0; i < len; i++) {
            if (cnt[s.charAt(i) - 'a'] < k) {
                max = Math.max(max, longestSubstring(s.substring(start, i), k));
                start = i + 1;
            }
        }
        return Math.max(max, longestSubstring(s.substring(start, len), k));
    }

//    /**
//     * 5%
//     * @param s
//     * @param k
//     * @return
//     */
//    public int longestSubstring(String s, int k) {
//        int max = 0;
//        int len = s.length();
//        for(int i=0;i<len;i++){
//            int mcnt = 0;
//            Map<Character,Integer> map = new HashMap<>();
//            for(int j = i;j<len;j++){
//                int cnt = map.getOrDefault(s.charAt(j),0);
//                if(cnt == 0){
//                    mcnt++;
//                }
//
//                map.put(s.charAt(j),++cnt);
//                if(cnt == k){
//                    if(--mcnt == 0){
//                        max = Math.max(max,j-i+1);
//                    }
//                }
//
//            }
//        }
//        return max;
//    }

}
