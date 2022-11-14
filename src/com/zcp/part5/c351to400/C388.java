package com.zcp.part5.c351to400;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZCP
 * @title: C388
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/longest-absolute-file-path/description/
 * @date 2022/11/3 10:06
 */
public class C388 {

    public int lengthLongestPath(String input) {
        if (input == null || input.length() == 0) {
            return 0;
        }
        String[] dirs = input.split("\n");
        List<String> path = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < dirs.length; i++) {
            int level = level(dirs[i]);
            while (path.size() > level) {
                path.remove(path.size() - 1);
            }
            path.add(dirs[i].substring(level));
            if(path.get(path.size()-1).contains(".")){
                max = Math.max(max, getLen(path));
            }
        }
        return max;
    }

    private int getLen(List<String> path) {
        return String.join("/", path.toArray(new String[0])).length();
    }

    private int level(String s) {
        int cnt = 0;
        while (s.startsWith("\t")) {
            cnt++;
            s = s.substring(1);
        }
        return cnt;
    }

    public static void main(String[] args) {

        System.out.println(new C388().lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
    }
}
