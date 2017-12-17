package org.lee.observer;

public class GraphObserver implements Observer {
    @Override
    public void update(NumberGenerator generator) {
        for (int i = 0; i < generator.getNumber(); i++) {
            System.out.print("*");
        }

        System.out.println();

    }
}