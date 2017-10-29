package org.lee.java.demo3;

public interface Parent {
    public void message(String body);

    public default void hey() {
        System.out.println("Parent: hey!");
    }
}