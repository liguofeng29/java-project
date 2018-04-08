package org.lee.design.mediator;

public class China extends StateColleague {
    public China(StateMediator stateMediator) {
        super(stateMediator);
    }

    @Override
    public String getName() {
        return "CHINA";
    }

    @Override
    public int getLevel() {
        return 1;
    }
}
