package com.zcp.part5.c351to400;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

/**
 * @author ZCP
 * @title: C365
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/water-and-jug-problem/description/
 * @date 2022/10/25 10:41
 */
public class C365 {

    static class Task {
        int x;
        int y;
        int pos;

        public Task(int x, int y, int pos) {
            this.x = x;
            this.y = y;
            this.pos = pos;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Task task = (Task) o;
            return x == task.x &&
                    y == task.y &&
                    pos == task.pos;
        }

        public long hashCode2() {
            return x * 1000000L + y;
        }
    }

    private long hash(int[] a) {
        return a[0] * 1000000L + a[1];
    }

    public boolean canMeasureWater2(int jug1Capacity, int jug2Capacity, int targetCapacity) {

        Stack<int[]> stack = new Stack<>();
        HashSet<Long> set = new HashSet<>();
        int[] task = new int[3];
        set.add(hash(task));
        stack.push(task);
        while (!stack.isEmpty()) {
            int[] pop = stack.pop();
            if (pop[0] == targetCapacity || pop[1] == targetCapacity || pop[0] + pop[1] == targetCapacity) {
                return true;
            }
            if (pop[2] < 6) {
                stack.push(pop);
            }

            int[] sub = null;
            // p1 倒满 i1
            if (pop[2] == 0) {
                sub = new int[]{jug1Capacity, pop[1], 0};
                if (!set.contains(hash(sub))) {
                    stack.push(sub);
                    set.add(hash(sub));
                }
                pop[2] = 1;
                continue;
            }
            // p2 倒满i2
            if (pop[2] == 1) {
                sub = new int[]{pop[0], jug2Capacity, 0};
                if (!set.contains(hash(sub))) {
                    stack.push(sub);
                    set.add(hash(sub));
                }
                pop[2] = 2;
                continue;
            }
            // p3 清空 i1
            if (pop[2] == 2) {
                sub = new int[]{0, pop[1], 0};
                if (!set.contains(hash(sub))) {
                    stack.push(sub);
                    set.add(hash(sub));
                }
                pop[2] = 3;
                continue;
            }
            // p4 清空 i2
            if (pop[2] == 3) {
                sub = new int[]{pop[0], 0, 0};
                if (!set.contains(hash(sub))) {
                    stack.push(sub);
                    set.add(hash(sub));
                }
                pop[2] = 4;
                continue;
            }

            // p5 i1 -> i2
            if (pop[2] == 4) {
                int diff = Math.min(pop[0], jug2Capacity - pop[1]);
                sub = new int[]{pop[0] - diff, pop[1] + diff, 0};
                if (!set.contains(hash(sub))) {
                    stack.push(sub);
                    set.add(hash(sub));
                }
                pop[2] = 5;
                continue;
            }

            // p6 i2 -> i1
            if (pop[2] == 5) {
                int diff = Math.min(pop[1], jug1Capacity - pop[0]);
                sub = new int[]{pop[0] + diff, pop[1] - diff, 0};
                if (!set.contains(hash(sub))) {
                    stack.push(sub);
                    set.add(hash(sub));
                }
                pop[2] = 6;
                continue;
            }

        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new C365().canMeasureWater2(22003, 31237, 1));
    }

    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        return process(0, 0, jug1Capacity, jug2Capacity, targetCapacity, new HashSet<>());
    }


    public boolean process(int i1, int i2, int jug1Capacity, int jug2Capacity, int target, Set<String> path) {
        if (i1 == target || i2 == target || i1 + i2 == target) {
            return true;
        }
        String p = "" + i1 + "" + i2;
        if (path.contains(p)) {
            return false;
        }
        path.add(p);

        // p1 倒满 i1
        if (i1 != jug1Capacity && process(jug1Capacity, i2, jug1Capacity, jug2Capacity, target, path)) {
            return true;
        }

        // p2 倒满i2
        if (i2 != jug2Capacity && process(i1, jug2Capacity, jug1Capacity, jug2Capacity, target, path)) {
            return true;
        }

        // p3 清空 i1
        if (i1 != 0 && process(0, i2, jug1Capacity, jug2Capacity, target, path)) {
            return true;
        }

        // p4 清空 i2
        if (i2 != 0 && process(i1, 0, jug1Capacity, jug2Capacity, target, path)) {
            return true;
        }

        // p5 i1 -> i2
        if (i1 != 0) {
            int ii = Math.min(i1, jug2Capacity - i2);
            if (process(i1 - ii, i2 + ii, jug1Capacity, jug2Capacity, target, path)) {
                return true;
            }
        }

        // p6 i2 -> i1
        if (i2 != 0) {
            int ii = Math.min(i2, jug1Capacity - i1);
            if (process(i1 + ii, i2 - ii, jug1Capacity, jug2Capacity, target, path)) {
                return true;
            }
        }
        return false;
    }
}
