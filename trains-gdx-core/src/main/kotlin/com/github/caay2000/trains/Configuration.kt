package com.github.caay2000.trains

object Configuration {

    var numberOfCities = 300
    var maxDistanceBetweenCities = 80
    var minDistanceBetweenCities = 50
    var minCityPopulation = 100
    var maxCityPopulation = 5000
    var maxRouteDistanceBetweenCities = 120

    var cityRatioGrowingPopulationConnected = 0.005f
    var cityRatioGrowingPopulationNotConnected = 0.0001f
    var cityRatioPopulationPAX = 0.0002f
    var cityRatioPopulationMail = 0.00005f
    var cityMaxRatioPAXPopulation = 0.5
    var cityDeliveredCargoPopulationGrowRate = 0.2

    var stationStopTime = 2f
}