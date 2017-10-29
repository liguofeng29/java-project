package org.lee.java.demo3;

public class ChildImpl implements Child {
    @Override
    public void message(String body) {
        System.out.println("Child:message");
    }
}