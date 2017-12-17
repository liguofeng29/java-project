package org.lee.design.templatemethod;

public class CharDisplay extends AbstractDisplay {

    private char content;

    public CharDisplay(char content) {
        this.content = content;
    }

    @Override
    protected void open() {
        System.out.print("<<");
    }

    @Override
    protected void print() {
        System.out.print(content);
    }

    @Override
    protected void close() {
        System.out.println(">>");
    }
}
