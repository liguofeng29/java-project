package org.lee.java.demo3;

import java.util.Iterator;

public interface Child extends Parent, AnotherParent {
    @Override
    default void hey() {
        Parent.super.hey();
    }
}
