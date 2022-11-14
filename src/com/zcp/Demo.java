package com.zcp;

import com.sun.javafx.binding.StringFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ZCP
 * @title: Demo
 * @projectName algorithm
 * @description: TODO
 * @date 2022/8/26 16:00
 */
public class Demo {

    public static void main(String[] args) {



        String s = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
        System.out.println(Arrays.toString(s.split("\n")));

        System.out.println(String.join("/", new ArrayList<>().toArray(new String[0])));

    }

    public static String f(String s) {
        return s.replace(".", "");
    }

    public static boolean isItNegative(int a, int b) {
        return ((a >> 31) ^ (b >> 31)) == 1;
    }

    public static void printInt(int num) {

        for (int i = 31; i >= 0; i--) {
            System.out.print((num >> i) & 1);
        }

        System.out.println();

    }
}
