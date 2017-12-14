package org.lee.thread;


import java.time.Clock;

/**
 *
 * join()することで、結果を待つ
 *
 *
 */
public class ThreadJoin {

    public static void main(String[] args) throws InterruptedException {

        Thread threadA = new Thread(() -> {
            System.out.println("--処理Ａ--");
            try {
                Thread.sleep(1000 * 3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            System.out.println("--処理Ｂ--");
            try {
                Thread.sleep(1000 * 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        long start = System.currentTimeMillis();

        threadA.start();

        threadB.start();

        threadA.join();

        threadB.join();

        System.out.println("ＡＢ処理時間 : " + (System.currentTimeMillis() - start));

        System.out.println("--メイン処理--");
    }
}
