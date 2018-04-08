package org.lee.design.mediator;

public class USA extends StateColleague {
    public USA(StateMediator stateMediator) {
        super(stateMediator);
    }

    @Override
    public String getName() {
        return "USA";
    }

    @Override
    public int getLevel() {
        return 1;
    }
}
