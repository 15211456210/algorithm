package com.zcp.part5.c151to200;

/**
 * @author ZCP
 * @title: C178
 * @projectName algorithm
 * @description: TODO
 * @date 2022/9/10 12:33
 */
public class C178 {

    /**
     * select
     * s.score ,
     * ((select count(distinct ss.score )
     *  from Scores ss
     * where ss.score > s.score)+ 1)  as "rank"
     * from Scores s
     * order by s.score desc
     *
     *
     *
     */
}
