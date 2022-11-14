package com.zcp.part5.c251to300;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author ZCP
 * @title: C299
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/bulls-and-cows/submissions/
 * @date 2022/9/27 13:23
 */
public class C299 {

    public String getHint(String secret, String guess) {
        HashMap<Character, Integer> appear = new HashMap<>();
        int x = 0, y = 0;
        char c;
        for (int i = 0; i < secret.length(); i++) {
            c = secret.charAt(i);
            if (c == guess.charAt(i)) {
                x++;
                continue;
            }
            if (!appear.containsKey(c)) {
                appear.put(c, 0);
            }
            appear.put(c, appear.get(c) + 1);
        }
        for (int i = 0; i < guess.length(); i++) {
            c = guess.charAt(i);
            if (secret.charAt(i) == c) {
                continue;
            }
            Integer remian = appear.getOrDefault(c, 0);
            if (remian > 0) {
                y++;
                appear.put(c, appear.get(c) - 1);
            }
        }
        return x + "A" + y + "B";
    }
}
