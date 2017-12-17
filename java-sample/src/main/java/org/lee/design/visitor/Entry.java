package org.lee.design.visitor;

public abstract class Entry implements Acceptor {
    public abstract String getName();
    public abstract int getSize();

    public Entry add(Entry entry) throws Exception {
        throw new Exception();
    }
}
