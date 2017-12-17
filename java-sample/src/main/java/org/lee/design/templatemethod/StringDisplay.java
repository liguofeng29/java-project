package org.lee.design.templatemethod;

public class StringDisplay extends AbstractDisplay {

    private String content;

    public StringDisplay(String content) {
        this.content = content;
    }


    @Override
    protected void open() {
        System.out.println("+" + getA(content) + "+");
    }

    @Override
    protected void print() {
        System.out.println("|" + content + "|");
    }

    @Override
    protected void close() {
        System.out.println("+" + getA(content) + "+");
    }

    private String getA(String content) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < content.length(); i++) {
            sb.append("-");
        }

        return sb.toString();

    }
}
