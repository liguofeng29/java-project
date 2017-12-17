package org.lee.design.poxy;

public class Printer implements Printable {

    public Printer() {
        try {
            System.out.println("インスタンス生成10秒。");
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void print(String content) {
        System.out.println(content);
    }
}
