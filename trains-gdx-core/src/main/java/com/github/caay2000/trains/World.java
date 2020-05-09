package com.github.caay2000.trains;

import java.util.ArrayList;
import java.util.List;

public class World {

    private final RandomGenerator random = new RandomGenerator();
    private final List<Position> cities = new ArrayList<>();

    private int maxX = 0;
    private int maxY = 0;
    private int minX = 0;
    private int minY = 0;

    private World(Configuration configuration) {
        generateCities(configuration);
    }

    private void addCity(Position city) {
        cities.add(city);
        if (city.getX() > maxX) {
            maxX = (int) city.getX();
        }
        if (city.getX() < minX) {
            minX = (int) city.getX();
        }
        if (city.getY() > maxY) {
            maxY = (int) city.getY();
        }
        if (city.getY() < minY) {
            minY = (int) city.getY();
        }
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getMinX() {
        return minX;
    }

    public int getMinY() {
        return minY;
    }

    private void generateCities(Configuration configuration) {
        long startTime = System.currentTimeMillis();
        cities.add(new Position());
        for (int i = 1; i < configuration.numberOfCities; i++) {
            Position newCity = createNewCity(cities, configuration);
            System.out.println("new city added: " + newCity);
            this.addCity(newCity);
        }
        long l = System.currentTimeMillis() - startTime;
        System.out.println("generating world time: " + l);
        System.out.println("cities list:" + cities);
        System.out.println("max values:"+maxX+","+minX+","+maxY+","+minY);
    }

    private Position createNewCity(List<Position> cities, Configuration configuration) {
        Position newCity = getRandomPosition(getRandomCity(cities), configuration.minDistanceBetweenCities, configuration.maxDistanceBetweenCities);
        if (validateNewCity(newCity, cities, configuration)) {
            return newCity;
        }
        return createNewCity(cities, configuration);
    }


    private boolean validateNewCity(Position newCity, List<Position> cities, Configuration configuration) {
        for (Position p : cities) {
            if (newCity.distanceTo(p) < configuration.minDistanceBetweenCities) {
                System.out.println("random city invalid: " + newCity);
                return false;
            }
        }
        return true;
    }

    private Position getRandomCity(List<Position> cities) {
        return cities.get(random.nextInt(0, cities.size() - 1));
    }

    private Position getRandomPosition(Position center, int minDistance, int maxDistance) {

        int rangeX = (int) center.getX() + maxDistance;
        int rangeY = (int) center.getY() + maxDistance;

        int x = random.nextInt(Math.negateExact(rangeX), rangeX);
        int y = random.nextInt(Math.negateExact(rangeY), rangeY);

        Position newPosition = new Position(x, y);
        if (newPosition.distanceTo(center) > minDistance) {
            return newPosition;
        }
        return getRandomPosition(center, minDistance, maxDistance);
    }

    public List<Position> getCities() {
        return cities;
    }

    public static class Configuration {

        private int numberOfCities = 10;
        private int maxDistanceBetweenCities = 50;
        private int minDistanceBetweenCities = 10;

        public Configuration() {

        }

        public Configuration withNumberOfCities(int numberOfCities) {
            this.numberOfCities = numberOfCities;
            return this;
        }

        public Configuration withMaxDistanceBetweenCities(int maxDistanceBetweenCities) {
            this.maxDistanceBetweenCities = maxDistanceBetweenCities;
            return this;
        }

        public Configuration withMinDistanceBetweenCities(int minDistanceBetweenCities) {
            this.minDistanceBetweenCities = minDistanceBetweenCities;
            return this;
        }

        public World build() {
            return new World(this);
        }

    }
}
