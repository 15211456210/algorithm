package com.zcp.part5.c351to400;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author ZCP
 * @title: C399
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/evaluate-division/description/
 * @date 2022/11/12 11:15
 */
public class C399 {

    class UniunFind {
        int[] parent;
        double[] weight;
        HashMap<String, Integer> index;
        int count;

        public UniunFind(int size) {
            parent = new int[size];
            weight = new double[size];
            index = new HashMap<>(size);
            Arrays.fill(weight, 1);

        }

        public double getDiv(String a, String b) {
            if (isUniun(a, b)) {
                return weight[index.get(a)] / weight[index.get(b)];
            }
            return -1;

        }


        public boolean isUniun(String a, String b) {
            if (!index.containsKey(a) || !index.containsKey(b)) {
                return false;
            }
            return (find(index.get(a)) == find(index.get(b)));
        }

        public void uniun(String a, String b, double value) {
            if (!index.containsKey(a)) {
                index.put(a, count);
                parent[count] = count;
                weight[count] = 1;
                count++;
            }

            if (!index.containsKey(b)) {
                index.put(b, count);
                parent[count] = count;
                weight[count] = 1;
                count++;
            }

            uniun(index.get(a), index.get(b), value);
        }

        public void uniun(int a, int b, double value) {
            int pa = find(a);
            int pb = find(b);
            if (pa != pb) {
                parent[pa] = pb;
            }
            weight[pa] = weight[b] * value / weight[a];
        }

        public int find(int a) {
            if (parent[a] != a) {
                int pa = parent[a];
                parent[a] = find(parent[a]);
                weight[a] = weight[a] * weight[pa];
            }
            return parent[a];
        }


    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        UniunFind uniun = new UniunFind(equations.size() * 2);
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            uniun.uniun(equation.get(0), equation.get(1), values[i]);
        }
        double[] ans = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            ans[i] = uniun.getDiv(queries.get(i).get(0), queries.get(i).get(1));
        }
        return ans;

    }
}
