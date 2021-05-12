package com.va.neoapp.models.country;

import java.util.List;
import java.util.Optional;

public class Country {
    private List<State> states;
    private String name;

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /* public Optional<State> getStateByName(String name) {
        return States.stream().filter(state -> state.getName().equals(name)).findFirst();
    }*/
}
