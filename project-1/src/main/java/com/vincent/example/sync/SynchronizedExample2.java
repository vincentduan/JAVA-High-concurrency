package com.vincent.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedExample2 {

    /**
     * 修饰一个类,被修饰的代码称为同步语句块，作用范围是大括号括起来的代码，作用的对象是调用代码的对象
     */
    public static void test1(int flag) {
        synchronized (SynchronizedExample2.class) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 - {}, {}", flag, i);
            }
        }
    }

    /**
     * 修饰一个静态方法，被修饰的方法称为同步方法，作用范围是整个方法，作用的对象是调用方法的对象
     */
    public static synchronized void test2(int flag) {
        for (int i = 0; i < 10; i++) {
            log.info("test2 - {}, {}", flag, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample2 synchronizedExample1 = new SynchronizedExample2();
        SynchronizedExample2 synchronizedExample2 = new SynchronizedExample2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            synchronizedExample1.test1(1);
        });
        executorService.execute(() -> {
            synchronizedExample2.test1(2);
        });
    }

}
