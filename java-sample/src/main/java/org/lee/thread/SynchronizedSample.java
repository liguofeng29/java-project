package org.lee.thread;

public class SynchronizedSample {

    // 銀行アカウント
    private static class Account {
        private int id;
        private long balance;

        public Account(int id, long balance) {
            this.id = id;
            this.balance = balance;
        }

        // メソッド同期
        public synchronized void drawBalance(long balance) {
            if (this.balance - balance >= 0) {
                System.out.println("引き出しＯＫ");
                this.balance -= balance;

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("残りは" + this.balance);
            } else {
                System.out.println("引き出しＮＧ");
            }
        }
    }


    // 引き出しスレッド
    private static class DrawThread extends Thread {
        private Account account;
        private long balance;

        public DrawThread(Account account, long balance) {
            this.account = account;
            this.balance = balance;
        }

        @Override
        public void run() {
//            // ブロック同期
//            synchronized (account) {
//                if (account.balance - balance >= 0) {
//                    System.out.println("引き出しＯＫ");
//                    account.balance -= balance;
//
//                    try {
//                        Thread.sleep(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                    System.out.println("残りは" + account.balance);
//                } else {
//                    System.out.println("引き出しＮＧ");
//                }
//            }

            // 同期メソッド
            account.drawBalance(balance);
        }
    }

    public static void main(String[] args) {
        Account account = new Account(1, 1000);
        new DrawThread(account, 600).start();
        new DrawThread(account, 600).start();
    }
}
