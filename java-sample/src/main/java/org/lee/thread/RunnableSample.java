package org.lee.thread;

public class RunnableSample {

    private static class RunnableA implements Runnable {
        int shared = 0;

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " " + shared++);
            }
        }
    }


    public static void main(String[] args) {
        Thread currentThread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName());

//         別target();
        共有target();

    }

    /**
     * Runnableが同じインスタンスであるなら、フィールドは共有される
     */
    private static void 共有target() {
        Runnable target = new RunnableA();
        new Thread(target, "thread-C").start();
        new Thread(target, "thread-D").start();
    }

    private static void 別target() {
        new Thread(new RunnableA(), "thread-A").start();
        new Thread(new RunnableA(), "thread-B").start();
    }
}
