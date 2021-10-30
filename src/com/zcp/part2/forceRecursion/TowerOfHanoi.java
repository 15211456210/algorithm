package com.zcp.part2.forceRecursion;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/17
 * @description：汉诺塔问题，3根柱子，将N个圆盘从最左边移动到最右侧，打印移动的过程
 * @version:
 */
public class TowerOfHanoi {


    public static void solution(int n){
        if (n>0){
            process(n,"left","mid","right");
        }
    }


    /**
     * 将N个圆盘从from转移到to
     * @param n
     * @param from
     * @param to
     * @param other
     */
    public static void process(int n,String from,String to,String other){
        if(n == 1){
            System.out.println("move " + n + " from " + from + " to " + to);
            return;
        }
        //将上面n-1个盘移到other上
        process(n-1,from,other,to);
        System.out.println("move " + n + " from " + from + " to " + to);
        //再将n-1从other移到to
        process(n-1,other,to,from);
    }

    public static void main(String[] args) {
        solution(3);
    }

}
