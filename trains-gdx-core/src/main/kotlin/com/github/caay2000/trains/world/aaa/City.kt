package com.github.caay2000.trains.world.aaa

import com.github.caay2000.trains.world.WorldConfiguration

class City : WorldEntity {

    private val position: Position
    private val population: Int
    private val citiesInRange: Set<City>

    private val connections: Set<WorldEntity>

    constructor(
        position: Position,
        population: Int,
        cities: Set<City>
    ) {
        this.position = position
        this.population = population
        this.connections = mutableSetOf()
        this.citiesInRange = calculateCitiesInRange(cities)
    }

    override fun position(): Position = position
    override fun distanceTo(to: WorldEntity): Float = position.distanceTo(to.position())
    override fun move(to: WorldEntity, distance: Float) = position.move(to.position(), distance)
    override fun connected(): Boolean = connections.isNotEmpty()

    fun population() = population

    private fun calculateCitiesInRange(cities: Set<City>): Set<City> {
        var rangeCities = HashSet<City>()
        if (cities == null) {
            for (city in cities) {
                if (city.distanceTo(this) <= WorldConfiguration.Companion.maxRouteDistance) {
                    rangeCities.add(city)
                }
            }
        }
        return rangeCities
    }

    fun getCitiesInRange(): Set<City> {
        return citiesInRange;
    }
}