package com.vincent.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class FutureExample {

    static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            log.info("do something in callable");
            Thread.sleep(5000);
            return "callable done";
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //Future<String> future = executorService.submit(new MyCallable());
        Future<String> future = executorService.submit(new Callable(){
            @Override
            public Object call() throws Exception {
                log.info("do something in callable");
                Thread.sleep(5000);
                return "callable done";
            }
        });
        log.info("do something in main");
        Thread.sleep(1000);
        String result = future.get(); // 如果call任务没有结束，则会阻塞在这里
        log.info("result: {}", result);
    }
}
