package com.thread.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author guoyj
 * @Date 2020/6/27 22:32
 */
public class ExceptionThreadDemo {

    private static void method1() {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            threadPool.submit(() -> {
                System.out.println("current thread name = " + Thread.currentThread().getName());
                Object object = null;
                System.out.print("result## " + object.toString());
            });
        }
    }

    private static void method2() {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            threadPool.submit(() -> {
                System.out.println("current thread name = " + Thread.currentThread().getName());
                try {
                    Object object = null;
                    System.out.print("result## " + object.toString());
                } catch (Exception e) {
                    System.out.println("程序出错啦!!!");
                }
            });
        }
    }

    private static void method3() {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            Future future = threadPool.submit(() -> {
                System.out.println("current thread name = " + Thread.currentThread().getName());
                Object object = null;
                System.out.print("result## " + object.toString());
            });
            try {
                future.get();
            } catch (Exception e) {
                System.out.println("线程执行发生异常!!!");
            }
        }
    }

    private static void method4() {
        ExecutorService threadPool = Executors.newFixedThreadPool(1, r -> {
            Thread t = new Thread(r);
            t.setUncaughtExceptionHandler(
                    (t1, e) -> {
                        System.out.println(t1.getName() + "线程抛出的异常" + e);
                    });
            return t;
        });
        threadPool.execute(() -> {
            Object object = null;
            System.out.print("result## " + object.toString());
        });
    }

    private static void method5() {
        ExecutorService threadPool = new ExtendedExecutor();
        for (int i = 0; i < 5; i++) {
            threadPool.submit(() -> {
                System.out.println("current thread name = " + Thread.currentThread().getName());
                Object object = null;
                System.out.print("result## " + object.toString());
            });
        }
    }


    public static void main(String[] args) {
       /* // 异常任务
        method1();
        // 线程池捕获异常
        method2();
        //
        method3();
        //
        method4();*/
        //
        method5();
    }


}


