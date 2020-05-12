package com.github.caay2000.trains.world

import com.github.caay2000.trains.util.RandomGenerator
import com.github.caay2000.trains.world.aaa.City
import com.github.caay2000.trains.world.aaa.Route
import java.util.HashSet

class World(configuration: WorldConfiguration?) {
    val size: WorldSize
    val cities: Set<City>
    private val connectedCities: MutableSet<City> = HashSet()
    private val routes: MutableSet<Route> = HashSet<Route>()
    private val trains: MutableSet<Train> = HashSet()
    private var elapsed = 0f

    fun getRoutes(): Set<Route> {
        return routes
    }

    fun getTrains(): Set<Train> {
        return trains
    }

    fun update(delta: Float) {
        elapsed += delta
        generateNewRoute()
        for (train in trains) {
            train.move(delta)
        }
    }

    private fun generateNewRoute() {
        if (connectedCities.size == cities.size) {
            return
        }
        val startCity: City
        startCity = (if (connectedCities.size == 0) {
            RandomGenerator.randomItem(cities)
        } else {
            val list: List<City> = connectedCities.toList()
            if (list.size == 0) {
                return
            }
            RandomGenerator.randomItem(list)
        })

        val possibleRoutes = startCity.getCitiesInRange()
        val collect: Set<City> = possibleRoutes
            .filter { city -> !city.connected() }
            .toSet()
        if (collect.size == 0) {
            return
        }
        val endCity = RandomGenerator.randomItem(collect)
        val route = Route(startCity, endCity)
        routes.add(route)
        startCity.connected()
        connectedCities.add(startCity)
        endCity?.connected()
        connectedCities.add(endCity!!)
        addTrainToRoute(route)
    }

    private fun addTrainToRoute(route: Route) {
        trains.add(Train(route, RandomGenerator.randomPositiveInt(10, 30).toDouble()))
    }

    init {
        val generator: WorldGenerator = WorldGenerator.Companion.aWorldGenerator(configuration)
        cities = generator.generateCities(configuration)
        size = WorldSize(cities)
    }
}