package com.zcp.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ：ZCP
 * @date ：Created in 2021/9/9 14:08
 * @description：
 * @version:
 */
public class ScheduleExecutorTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2);
//
//        ScheduledFuture<?> scheduledFuture = scheduledThreadPoolExecutor.schedule(() -> {
//            System.out.println("延迟5S执行，是否阻塞？");
//        }, 5000, TimeUnit.MILLISECONDS);
//
//
//        scheduledFuture.get();
//        System.out.println("主线程");
//        scheduledThreadPoolExecutor.shutdownNow();
        System.out.println(String.format("%.3f", "235"));


    }
}
