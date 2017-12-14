package org.lee.thread;

public class ThreadState {

    private static class RunnableA implements Runnable {
        int shared = 0;

        @Override
        public void run() {

            new Thread(() -> {
                try {
                    for (int i = 0; i < 10; i++) {


                        System.out.println(Thread.currentThread().getName() + " is " + Thread.currentThread().isAlive());
                        Thread.sleep(1000);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "thread-sub-A").start();


            new Thread(() -> {
                try {
                    for (int i = 0; i < 10; i++) {


                        System.out.println(Thread.currentThread().getName() + " is " + Thread.currentThread().isAlive());
                        Thread.sleep(1000);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "thread-sub-B").start();

        }
    }

    // mainスレッドが新でもサブスレッドは影響を受けない
    // スレッドが生成された時点でメインと同等な地位を得る
    public static void main(String[] args) {

        Thread mainThread = new Thread(new RunnableA(), "thread-main");
        mainThread.start();

        while (true) {
            System.out.println(mainThread.isAlive());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        System.exit(0);
    }




}
