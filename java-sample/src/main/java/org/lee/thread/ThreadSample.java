package org.lee.thread;


/**
 * Threadはスレッドを表現するクラス
 * <p>
 * Threadかそのサブクラスである必要がある？
 */
public class ThreadSample {

    public static class ThreadA extends Thread {
        // sharedはスレッド共有されない！！
        int shared = 0;

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(this.getName() + " " + shared++);
            }
        }
    }

    public static void main(String[] args) {
        Thread currentThread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName());

        new ThreadA().start();
        new ThreadA().start();
    }
}
