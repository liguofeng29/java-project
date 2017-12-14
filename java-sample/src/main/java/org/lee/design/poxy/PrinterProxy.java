package org.lee.design.poxy;

public class PrinterProxy implements Printable {
    private Printer printer;

    private synchronized void realize() {
        if (printer == null) {
            printer = new Printer();
        }
    }

    @Override
    public void print(String content) {
        realize();
        printer.print(content);
    }
}
