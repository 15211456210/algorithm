package com.zcp.part5.c301to350;


import java.util.*;

/**
 * @author ZCP
 * @title: C332
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/reconstruct-itinerary/
 * @date 2022/10/17 22:31
 */
public class C332 {

    class Pair {
        String key;
        boolean selected;

        public Pair(String key, boolean selected) {
            this.key = key;
            this.selected = selected;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        ArrayList<String> ans = new ArrayList<>();
        HashMap<String, List<Pair>> map = new HashMap<>();
        for (int i = 0; i < tickets.size(); i++) {
            List<String> ticket = tickets.get(i);
            String from = ticket.get(0);
            String to = ticket.get(1);
            if (!map.containsKey(from)) {
                map.put(from, new ArrayList<>());
            }
            List<Pair> nexts = map.get(from);
            int size = nexts.size();
            Pair toPair = new Pair(to, false);
            if (size == 0) {
                nexts.add(toPair);
            } else {
                for (int j = 0; j < size; j++) {
                    Pair next = nexts.get(j);
                    if (next.getKey().compareTo(to) > 0) {
                        nexts.add(j, toPair);
                        break;
                    }
                    if (j == size - 1) {
                        nexts.add(toPair);
                    }
                }
            }

        }
        String cur = "JFK";
        ans.add(cur);
        process(map, ans);
        return ans;
    }

    private boolean process(HashMap<String, List<Pair>> map, List<String> path) {
        boolean isFinish = true;
        for (List<Pair> pairs : map.values()) {
            for (Pair pair : pairs) {
                if (!pair.isSelected()) {
                    isFinish = false;
                }
            }
        }
        if (isFinish) {
            return true;
        }


        int size = path.size();
        String last = path.get(size - 1);

        if (map.containsKey(last)) {
            List<Pair> nexts = map.get(last);
            int pqSize = nexts.size();
            for (int i = 0; i < pqSize; i++) {
                Pair next = nexts.get(i);
                if (next.isSelected()) {
                    continue;
                }
                next.setSelected(true);
                path.add(next.getKey());
                boolean rets = process(map, path);

                if (rets) {
                    return true;
                }

                path.remove(size);
                next.setSelected(false);
            }
        }
        return false;
    }


    public static void main(String[] args) {
        // ["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]

        ArrayList<List<String>> input = new ArrayList<>();

        ArrayList<String> input1 = new ArrayList<>();
        ArrayList<String> input2 = new ArrayList<>();
        ArrayList<String> input3 = new ArrayList<>();
        input1.add("JFK");
        input1.add("KUL");
        input2.add("JFK");
        input2.add("NRT");
        input3.add("NRT");
        input3.add("JFK");
        input.add(input1);
        input.add(input2);
        input.add(input3);
        List<String> itinerary = new C332().findItinerary(input);

        System.out.println(itinerary);


    }
}
