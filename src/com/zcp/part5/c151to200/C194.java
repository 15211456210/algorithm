package com.zcp.part5.c151to200;

/**
 * @author ZCP
 * @title: C194
 * @projectName algorithm
 * @description: https://leetcode.cn/problems/transpose-file/
 * @date 2022/9/15 14:13
 */
public class C194 {

    /**
     * awk '{
     *     for (i=1;i<=NF;i++){
     *         if (NR==1){
     *             res[i]=$i
     *         }
     *         else{
     *             res[i]=res[i]" "$i
     *         }
     *     }
     * }END{
     *     for(j=1;j<=NF;j++){
     *         print res[j]
     *     }
     * }' file.txt
     */
}
