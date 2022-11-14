package com.zcp.part4.day19;

import java.util.Arrays;

/**
 * @author ZCP
 * @title: TreePreInToPost
 * @projectName algorithm
 * @description: 给出二叉树的先序、中序遍历结果，要求返回后续遍历结果
 * @date 2022/3/7 19:22
 */
public class TreePreInToPost {


    /**
     * @param pre
     * @param in
     * @return
     */
    public int[] solution(int[] pre, int[] in) {

        if (pre == null || in == null || pre.length != in.length) {
            return new int[0];
        }

        int[] post = new int[pre.length];
        process(pre, 0, pre.length - 1, in, 0, in.length - 1, post, 0, post.length - 1);
        return post;
    }

    private void process(int[] pre, int l1, int r1, int[] in, int l2, int r2, int[] post, int l3, int r3) {
        if (l3 > r3) {
            return;
        }
        if (l3 == r3) {
            post[r3] = pre[l1];
            return;
        }

        post[r3] = pre[l1];

        int leftCnt = 0;

        for (int i = l2; i <= r2; i++, leftCnt++) {
            if (pre[l1] == in[i]) {
                break;
            }
        }

        int rightCnt = r1 - l1 - leftCnt;
        process(pre, l1 + 1, l1 + leftCnt, in, l2, l2 + leftCnt - 1, post, l3, l3 + leftCnt - 1);
        process(pre, l1 + leftCnt + 1, r1, in, l2 + leftCnt + 1, r2, post, l3 + leftCnt, r3 - 1);
    }


    public static void main(String[] args) {
        TreePreInToPost treePreInToPost = new TreePreInToPost();
        System.out.println(Arrays.toString(treePreInToPost.solution(new int[]{3, 7, 6, 8, 9, 6}, new int[]{6, 7, 8, 3, 6, 9})));
        System.out.println(Arrays.toString(treePreInToPost.solution(new int[]{3, 7, 6, 9, 4, 2}, new int[]{7, 9, 4, 2, 6, 3})));
    }

}
