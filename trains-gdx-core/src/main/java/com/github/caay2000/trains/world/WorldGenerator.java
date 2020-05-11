package com.github.caay2000.trains.world;

import com.github.caay2000.trains.common.Position;

import java.util.HashSet;
import java.util.Set;

import static com.github.caay2000.trains.util.RandomGenerator.randomPositiveInt;
import static com.github.caay2000.trains.util.RandomGenerator.randomSignedInt;

class WorldGenerator {

    private final WorldConfiguration configuration;

    private WorldGenerator(WorldConfiguration configuration) {
        this.configuration = configuration;
    }

    public static WorldGenerator aWorldGenerator(WorldConfiguration configuration) {
        return new WorldGenerator(configuration);
    }

    public Set<City> generateCities(WorldConfiguration configuration) {

        Set<City> cities = new HashSet<>();
        for (int i = 0; i < configuration.numberOfCities; i++) {
            cities.add(generateNewCity(cities));
        }
        return cities;
    }

    private City generateNewCity(Set<City> cities) {

        Position position = generateCityPosition(cities);
        int population = generateCityPopulation();

        return new City(
                position.getX(),
                position.getY(),
                population);
    }

    private int generateCityPopulation() {
        return randomPositiveInt(configuration.minPopulation, configuration.maxPopulation);
    }

    private Position generateCityPosition(Set<City> cities) {

        if (cities.size() == 0) {
            return new Position(0, 0);
        }
        Position position = new Position.Generator()
                .withCenter(getRandomCityPosition(cities))
                .withMaxDistance(configuration.maxDistanceBetweenCities)
                .withMinDistance(configuration.minDistanceBetweenCities)
                .generate();

        if (validatePosition(position, cities)) {
//            System.out.println("valid position " + position);
            return position;
        }
//        System.out.println("invalid position " + position);
        return generateCityPosition(cities);
    }

    private boolean validatePosition(Position position, Set<City> cities) {

        for (City city : cities) {
            if (position.distanceTo(city.getPosition()) < configuration.minDistanceBetweenCities) {
                return false;
            }
        }
        return true;
    }

    private Position getRandomCityPosition(Set<City> cities) {
        return cities.stream().skip(randomSignedInt(0, cities.size() - 1)).findFirst().get().getPosition();
    }
}
