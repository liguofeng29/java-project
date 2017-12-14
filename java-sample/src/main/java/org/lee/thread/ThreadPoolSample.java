package org.lee.thread;

import java.util.concurrent.*;

public class ThreadPoolSample {


    public static void main(String[] args) {

        // スレッドプール生成();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

//
//
//        Runnable runnableA = new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    for (int i = 0; i < 5; i++) {
//                        System.out.println(Thread.currentThread().getName());
//                        Thread.sleep(1000 * 1);
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//
//        executorService.submit(runnableA);
//        executorService.submit(runnableA);
//
//        executorService.submit(runnableA, true);
//        executorService.submit(runnableA, true);


        System.out.println("111");
        Future<Boolean> future = executorService.submit(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(500);
                    System.out.println(Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return true;
        });


        try {
            Thread.sleep(1000 * 3);
            System.out.println("222");

            // TODO これの実行で結果取得するんじゃないんだっけ？
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("333");

        // これやらないとmainが終わらないね！
        executorService.shutdown();
    }

    private static void スレッドプール生成() {
        /**
         * thread pool factory
         */
        // 1.
        Executors.newCachedThreadPool();

        // 2.
        Executors.newFixedThreadPool(3);

        // 3.
        Executors.newSingleThreadExecutor();

        // 4.
        Executors.newScheduledThreadPool(2);

        // 5.
        Executors.newWorkStealingPool(3);

        // 6.
        Executors.newWorkStealingPool();


        // ExecutorService is thread pool
    }
}
