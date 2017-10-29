package org.lee.java.demo3;

public class ParentImpl implements Parent {
    @Override
    public void message(String body) {
        System.out.println("Parent:message");
    }
}