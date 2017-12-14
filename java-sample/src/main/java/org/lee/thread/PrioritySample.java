package org.lee.thread;

import org.omg.PortableServer.THREAD_POLICY_ID;

public class PrioritySample {

    public static class PriorityThread extends Thread {

        public PriorityThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            int i = 0;
            while (true) {
                System.out.println(this.getName() + " is running. time is " + i++);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {

        Thread.currentThread().setPriority(6);

        Thread high = new PriorityThread("MAX");
        high.setPriority(Thread.MAX_PRIORITY);
        high.start();

        Thread low = new PriorityThread("MIN");
        low.setPriority(Thread.MIN_PRIORITY);
        low.start();
    }
}
