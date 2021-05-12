package com.va.neoapp.models.country;

import java.util.List;
import java.util.Optional;

public class State {
    private List<String> cities;
    private String name;

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public Optional<String> getCityByName(String name) {
        return cities.stream().filter(city -> city.equals(name)).findFirst();
    }*/
}
