package org.lee.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class NumberGenerator {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyToObservers() {
        observers.forEach(observer -> observer.update(this));
    }

    public abstract int getNumber();
    public abstract void execute();
}
