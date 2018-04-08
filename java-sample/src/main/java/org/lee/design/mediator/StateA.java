package org.lee.design.mediator;

public class StateA extends StateColleague {
    public StateA(StateMediator stateMediator) {
        super(stateMediator);
    }

    @Override
    public String getName() {
        return "STATE-A";
    }

    @Override
    public int getLevel() {
        return 3;
    }
}
