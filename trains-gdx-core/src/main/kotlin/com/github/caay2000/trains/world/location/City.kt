package com.github.caay2000.trains.world.location

import com.github.caay2000.trains.world.Position

class City {

    val name: String
    val position: Position
    private val connections: Set<City> = mutableSetOf()
    val citiesInRange: Set<City> = mutableSetOf()

    private val population: Population
    private val production: Production
    private val consumption: Consumption

    constructor(name: String, position: Position, population: Int) {

        this.name = name
        this.position = position
        this.population = Population(this, population)
        this.production = Production(this)
        this.consumption = Consumption(this)
    }

    val size: Int
        get() = this.population.population

    fun connected(): Boolean = this.connections.isNotEmpty()
    fun distanceTo(to: City): Float = position.distanceTo(to.position)
    fun addCityInRange(City: City) = (this.citiesInRange as MutableSet).add(City)
    fun addConnection(City: City) = (this.connections as MutableSet).add(City)

    fun update(delta: Float) {

        production.update(delta)
        population.update(delta)
    }
}
