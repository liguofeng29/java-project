package org.lee.design.poxy;

public class Printer implements Printable {

    public Printer() {
        try {
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
