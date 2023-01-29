package com.zcp.part5.c401to450;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZCP
 * @title: C412
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/fizz-buzz/description/
 * @date 2022/11/23 9:36
 */
public class C412 {

    public List<String> fizzBuzz(int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            ans.add((i % 3 == 0 && i % 5 == 0) ? "FizzBuzz" : (i % 3 == 0 ? "Fizz" : i % 5 == 0 ? "Buzz" : i + ""));
        }
        return ans;
    }
}
