package org.lee.design.poxy;

public class Main {

    public static void main(String[] args) {
        Printable p = new PrinterProxy();
        p.print("aaaa");

    }
}
