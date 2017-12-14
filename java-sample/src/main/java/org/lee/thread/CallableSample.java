package org.lee.thread;


import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * call()は戻り値あり、run()はなし
 *
 * call()は例外投げれる
 *
 *
 * 1. FutureTaskでCallableインスタンスをラップ
 * 2. FutureTaskをThreadのtargetにする
 * 3. FutureTask#getで戻り値取得
 *
 *
 */
public class CallableSample {




    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // FutureTaskでCallableをラップ
        FutureTask<Integer> futureTask = new FutureTask<Integer>(() -> {
            Thread.sleep(1000 * 2);
            System.out.println("3333");
            int shared = 0;

            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " " + shared++);
            }
            return shared;
        });

        System.out.println("1111");

        // TODO これ調査ね
        // call()はstart(),get()どっちで実行されるの？？
        // start()っぽいけど、
        new Thread(futureTask, "Thread-A").start();

        System.out.println("2222");


        Integer integer = futureTask.get();
        Thread.sleep(1000 * 2);
        System.out.println("4444");

        System.out.println(integer);
        System.out.println("5555");


    }
}

