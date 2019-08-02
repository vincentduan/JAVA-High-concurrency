package com.vincent.example.lock;

import com.vincent.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class LockExample2 {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static int count = 0;

    private final static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        //线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        //定义计数器
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i = 0; i < clientTotal; i++) {
            executorService.execute(() ->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {

                    log.error("exception", e);
                }
                countDownLatch.countDown();

            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}", count);
    }

    public static void add() {
        //lock.lock();
        try{
            count++;
        }finally {
            //lock.unlock();
        }
    }

}
