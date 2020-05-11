package com.github.caay2000.trains.world

class WorldConfiguration private constructor() {
//
//    fun withNumberOfCities(numberOfCities: Int): WorldConfiguration {
//        this.numberOfCities = numberOfCities
//        return this
//    }
//
//    fun withMaxDistanceBetweenCities(maxDistanceBetweenCities: Int): WorldConfiguration {
//        this.maxDistanceBetweenCities = maxDistanceBetweenCities
//        return this
//    }
//
//    fun withMinDistanceBetweenCities(minDistanceBetweenCities: Int): WorldConfiguration {
//        this.minDistanceBetweenCities = minDistanceBetweenCities
//        return this
//    }
//
//    fun withMaxRouteDistance(maxRouteDistance: Int): WorldConfiguration {
//        this.maxRouteDistance = maxRouteDistance
//        return this
//    }

    companion object {
        //    public static int numberOfCities = 10;
        //    int maxDistanceBetweenCities = 50;
        //    int minDistanceBetweenCities = 10;
        //    int minPopulation = 100;
        //    int maxPopulation = 5000;
        //
        //    public static int maxRouteDistance = 50;
        var numberOfCities = 30
        var maxDistanceBetweenCities = 150
        var minDistanceBetweenCities = 100
        var minPopulation = 100
        var maxPopulation = 5000
        var maxRouteDistance = 200
        fun worldConfiguration(): WorldConfiguration {
            return WorldConfiguration()
        }
    }
}