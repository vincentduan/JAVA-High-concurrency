package com.vincent.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CountDownLatchExample2 {

    private final static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for(int i = 0; i< threadCount; i++) {
            final int threadNum = i;
            executorService.execute(() ->{
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        //可以保证之前的线程都执行完成
        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        log.info("finish");
        // 第一时间内并不会把所有线程都销毁，而是让当前已有线程执行完之后在把线程池销毁。
        executorService.shutdown();
    }
    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(100);
        log.info("{}", threadNum);
    }
}
