package com.github.caay2000.trains.world;

import com.github.caay2000.trains.util.RandomGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.github.caay2000.trains.world.WorldGenerator.aWorldGenerator;

public class World {

    private final WorldSize size;
    private final Set<City> cities;
    private final Set<City> connectedCities = new HashSet<>();

    private final Set<Route> routes = new HashSet();

    private final Set<Train> trains = new HashSet<>();

    private float elapsed = 0;

    public World(WorldConfiguration configuration) {

        WorldGenerator generator = aWorldGenerator(configuration);
        this.cities = generator.generateCities(configuration);
        this.size = new WorldSize(cities);
    }

    public Set<City> getCities() {
        return cities;
    }

    public Set<Route> getRoutes() {
        return routes;
    }

    public Set<Train> getTrains() {
        return trains;
    }

    public WorldSize getSize() {
        return size;
    }

    public void update(float delta) {
        elapsed += delta;
        generateNewRoute();
        for (Train train : trains) {
            train.move(delta);
        }
    }

    private void generateNewRoute() {
        if (connectedCities.size() == cities.size()) {
            return;
        }
        City startCity;
        if (connectedCities.size() == 0) {
            startCity = RandomGenerator.randomItem(
                    cities.stream().collect(Collectors.toSet()));

        } else {
            Set<City> list = connectedCities.stream()
                    .collect(Collectors.toSet());
            if (list.size() == 0) {
                return;
            }
            startCity = RandomGenerator.randomItem(
                    list);
        }
        Set<City> possibleRoutes = startCity.getCitiesInRange(cities);

        Set<City> collect = possibleRoutes.stream()
                .filter(e -> e.isNotConnected())
                .collect(Collectors.toSet());
        if (collect.size() == 0) {
            return;
        }
        City endCity = RandomGenerator.randomItem(collect);

        Route route = new Route(startCity, endCity);
        routes.add(route);
        startCity.connected();
        connectedCities.add(startCity);
        endCity.connected();
        connectedCities.add(endCity);

        addTrainToRoute(route);



    }

    private void addTrainToRoute(Route route) {
        trains.add(new Train(route, RandomGenerator.randomPositiveInt(10, 30)));
    }
}
