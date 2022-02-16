package com.zcp.part4;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * @description:
 * @projectName:algorithm
 * @see:com.zcp.part4
 * @author:ZCP
 * @createTime:2021/12/27
 * @version:1.0
 */
public class Test extends Thread {

    public static void fun() {
        int time = 10000;
        // H :1  T:0
        // HHT  HTT
        double arr[] = new double[2];
        List<Integer> list = new ArrayList<>();
        while (time-- > 0) {
            if (Math.random() < 0.5) {
                list.add(1);
            } else {
                list.add(0);
            }
            if (list.size() >= 3) {
                int size = list.size();
                if (list.get(size - 1) == 0 && list.get(size - 2) == 1 && list.get(size - 3) == 1) {
                    arr[0]++;
                    list.clear();
                    continue;
                }
                if (list.get(size - 1) == 0 && list.get(size - 2) == 0 && list.get(size - 3) == 1){
                    arr[1]++;
                    list.clear();
                    continue;
                }
            }
        }
        System.out.println(arr[0]/(arr[0]+arr[1]));
    }

    public static void main(String[] args) {
        fun();

    }


}
