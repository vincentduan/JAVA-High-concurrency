package com.vincent.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

@Slf4j
public class LockExample5 {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        new Thread(()->{
            try {
                reentrantLock.lock(); // 线程加入到AQS等待队列里面去
                log.info("wait signal"); // 第一步：等待信号
                condition.await(); // 线程从正常的AQS队列中移除，然后加入到condition的等待队列中去
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("get signal");// 第四步：得到信号
            reentrantLock.unlock();
        }).start();
        new Thread(()->{
            reentrantLock.lock();
            log.info("get lock"); // 第二步
            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            condition.signalAll();
            log.info("send signal ----"); //第三步：发送信号
            reentrantLock.unlock();
        }).start();
    }

}