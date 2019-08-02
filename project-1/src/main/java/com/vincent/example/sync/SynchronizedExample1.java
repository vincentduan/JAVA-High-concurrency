package com.vincent.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedExample1 {

    /**
     * 修饰一个代码块,被修饰的代码称为同步语句块，作用范围是大括号括起来的代码，作用的对象是调用代码的对象
     */
    public void test1(int flag) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 - {}, {}", flag, i);
            }
        }
    }

    /**
     * 修饰一个方法，被修饰的方法称为同步方法，作用范围是整个方法，作用的对象是调用方法的对象
     */
    public synchronized void test2(int flag) {
        for (int i = 0; i < 10; i++) {
            log.info("test2 - {}, {}", flag, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 synchronizedExample1 = new SynchronizedExample1();
        SynchronizedExample1 synchronizedExample2 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            synchronizedExample1.test2(1);
        });
        executorService.execute(() -> {
            synchronizedExample2.test2(2);
        });
    }

}
