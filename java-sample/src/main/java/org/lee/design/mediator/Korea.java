package org.lee.design.mediator;

public class Korea extends StateColleague {
    public Korea(StateMediator stateMediator) {
        super(stateMediator);
    }

    @Override
    public String getName() {
        return "KOREA";
    }

    @Override
    public int getLevel() {
        return 2;
    }
}
