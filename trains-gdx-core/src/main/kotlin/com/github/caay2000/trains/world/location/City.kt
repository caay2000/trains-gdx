package com.github.caay2000.trains.world.location

import com.github.caay2000.trains.world.Position
import com.github.caay2000.trains.world.entity.Wagon

class City {

    val name: String
    val position: Position
    private val connections: Set<City> = mutableSetOf()
    val citiesInRange: Set<City> = mutableSetOf()

    internal val population: Population
    private val production: Production
    internal val consumption: Consumption

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

    fun loadWagon(wagon: Wagon) {
        this.production.load(wagon)
    }

    fun unloadWagon(wagon: Wagon) {
        this.consumption.unload(wagon)
    }

    fun update(delta: Float) {

        production.update(delta)
        population.update(delta)
    }
}