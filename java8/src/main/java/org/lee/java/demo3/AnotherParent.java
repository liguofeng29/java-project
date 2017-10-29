package org.lee.java.demo3;

public interface AnotherParent {
    public void message(String body);

    public default void hey() {
        System.out.println("Another parent: hey!");
    }
}