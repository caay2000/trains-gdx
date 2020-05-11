package com.github.caay2000.trains.world;

import com.github.caay2000.trains.common.Position;

import java.util.HashSet;
import java.util.Set;

public class City implements WorldObject {

    private final Position position;
    private final int population;
    private boolean connected;

    private Set<City> citiesInRange;

    public City(float x, float y, int population) {
        this.position = new Position(x, y);
        this.population = population;
        this.connected = false;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public int getPopulation() {
        return population;
    }

    public boolean isConnected() {
        return connected;
    }

    public boolean isNotConnected() {
        return !isConnected();
    }

    @Override
    public float distanceTo(WorldObject to) {
        return position.distanceTo(to.getPosition());
    }

    public Set<City> getCitiesInRange(Set<City> cities) {
        if (citiesInRange == null) {
            citiesInRange = new HashSet<>();
            for (City city : cities) {
                if (city.distanceTo(this) <= WorldConfiguration.maxRouteDistance) {
                    citiesInRange.add(city);
                }
            }
        }
        return citiesInRange;
    }

    public void connected() {
        this.connected = true;
    }
}
