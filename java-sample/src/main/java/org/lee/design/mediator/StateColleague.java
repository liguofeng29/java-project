package org.lee.design.mediator;

public abstract class StateColleague {

    protected StateMediator stateMediator;

    public StateColleague(StateMediator stateMediator) {
        this.stateMediator = stateMediator;
    }


    public abstract String getName();

    public abstract int getLevel();

    public void receive(String message) {
        System.out.println(getName() + " receive : " + message);
    }

    public void send(String message) {
        stateMediator.receive(this, message);
    }
}
