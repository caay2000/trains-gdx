package com.github.caay2000.trains

object Configuration {

    val numberOfCompanies = 3
    var gameSpeed = 10f

    var entityAccelerationRate = 10f

    var numberOfCities = 300
    var maxDistanceBetweenCities = 80
    var minDistanceBetweenCities = 50
    var minLocationPopulation = 100
    var maxLocationPopulation = 5000
    var maxRouteDistanceBetweenCities = 120

    var cityRatioGrowingPopulationConnected = 0.005f
    var cityRatioGrowingPopulationNotConnected = 0.0001f
    var cityRatioPopulationPAX = 0.0002f
    var cityRatioPopulationMail = 0.00005f
    var cityMaxRatioPAXPopulation = 0.5
    var cityDeliveredCargoPopulationGrowRate = 0.2

    var stationStopTime = 2f

    var companyStartingBalance = 10000
    var companyMinBalanceForNewRoute = 8000
}