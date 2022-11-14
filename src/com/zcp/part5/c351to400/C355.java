package com.zcp.part5.c351to400;

import java.math.BigInteger;
import java.util.*;

/**
 * @author ZCP
 * @title: C355
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/design-twitter/description/
 * @date 2022/10/20 16:22
 */
public class C355 {

    class Twitter {
        class Node {
            int tweetId;
            int seq;

            public Node(int tweetId, int seq) {
                this.tweetId = tweetId;
                this.seq = seq;
            }
        }

        int seq = 0;
        Map<Integer, Set<Integer>> followMap;
        Map<Integer, List<Node>> userTweets;

        PriorityQueue<Node> queue;

        public Twitter() {
            followMap = new HashMap<>();
            userTweets = new HashMap<>();
            queue = new PriorityQueue<Node>((n1, n2) -> n2.seq - n1.seq);
        }

        public void postTweet(int userId, int tweetId) {
            List<Node> tweets = userTweets.getOrDefault(userId, new ArrayList<>());
            tweets.add(new Node(tweetId, seq++));
            if (tweets.size() > 10) {
                tweets.remove(0);
            }
            userTweets.put(userId, tweets);
        }

        public List<Integer> getNewsFeed(int userId) {
            queue.clear();
            Set<Integer> followees = followMap.getOrDefault(userId, new HashSet<>());
            followees.add(userId);
            for (int followee : followees) {
                if (userTweets.containsKey(followee)) {
                    List<Node> nodes = userTweets.get(followee);
                    for (int i = 0; i < nodes.size(); i++) {
                        queue.offer(nodes.get(i));
                    }
                }
            }
            ArrayList<Integer> ans = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < Math.min(10, size); i++) {
                ans.add(queue.poll().tweetId);
            }
            return ans;
        }

        public void follow(int followerId, int followeeId) {
            Set<Integer> followeeIds = followMap.getOrDefault(followerId, new HashSet<>());
            followeeIds.add(followeeId);
            followMap.put(followerId, followeeIds);
        }

        public void unfollow(int followerId, int followeeId) {
            Set<Integer> followeeIds = followMap.getOrDefault(followerId, new HashSet<>());
            followeeIds.remove(followeeId);
            followMap.put(followerId, followeeIds);
        }
    }
}
