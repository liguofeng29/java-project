package org.lee.design.mediator;

import java.util.ArrayList;
import java.util.List;

public class UN implements StateMediator {


    protected List<StateColleague> stateColleagues = new ArrayList<>();

    @Override
    public void addState(StateColleague stateColleague) {
        this.stateColleagues.add(stateColleague);
    }

    @Override
    public void receive(StateColleague stateColleague, String message) {

        stateColleagues.stream()
            .filter(s -> !s.getName().equals(stateColleague.getName()))
            .filter(s -> s.getLevel() <= stateColleague.getLevel())
            .forEach(s -> s.receive(message));
    }
}
