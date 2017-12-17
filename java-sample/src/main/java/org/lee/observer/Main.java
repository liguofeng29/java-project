package org.lee.observer;

public class Main {

    public static void main(String[] args) {

        RandomNumberGenerator g = new RandomNumberGenerator();

        g.addObserver(new DigitObserver());
        g.addObserver(new GraphObserver());

        g.execute();
    }
}
