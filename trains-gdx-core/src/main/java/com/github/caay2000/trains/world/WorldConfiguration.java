package com.github.caay2000.trains.world;

public class WorldConfiguration {

//    public static int numberOfCities = 10;
//    int maxDistanceBetweenCities = 50;
//    int minDistanceBetweenCities = 10;
//    int minPopulation = 100;
//    int maxPopulation = 5000;
//
//    public static int maxRouteDistance = 50;

    public static int numberOfCities = 10;
    public static int maxDistanceBetweenCities = 50;
    public static int minDistanceBetweenCities = 10;
    public static int minPopulation = 100;
    public static int maxPopulation = 5000;

    public static int maxRouteDistance = 50;

    private WorldConfiguration() {
    }

    public static WorldConfiguration worldConfiguration() {
        return new WorldConfiguration();
    }

    public WorldConfiguration withNumberOfCities(int numberOfCities) {
        this.numberOfCities = numberOfCities;
        return this;
    }

    public WorldConfiguration withMaxDistanceBetweenCities(int maxDistanceBetweenCities) {
        this.maxDistanceBetweenCities = maxDistanceBetweenCities;
        return this;
    }

    public WorldConfiguration withMinDistanceBetweenCities(int minDistanceBetweenCities) {
        this.minDistanceBetweenCities = minDistanceBetweenCities;
        return this;
    }

    public WorldConfiguration withMaxRouteDistance(int maxRouteDistance) {
        this.maxRouteDistance = maxRouteDistance;
        return this;
    }

}
