package org.lee.design.mediator;

public interface StateMediator {
    public void addState(StateColleague stateColleague);
    public void receive(StateColleague stateColleague, String message);
}

