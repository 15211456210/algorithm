package com.zcp.part2.concurrent;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        MyArrayList<Integer> list = new MyArrayList<>();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int l = 0; l < 1000; l++) {
                    list.add(l);
                }
            }).start();
        }

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int l = 0; l < 1000; l++) {
                    list.remove(list.size() - 1);
                }
            }).start();
        }

        Thread.sleep(1000);
        System.out.println(list.size());
    }
}
