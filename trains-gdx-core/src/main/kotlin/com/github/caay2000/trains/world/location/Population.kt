package com.github.caay2000.trains.world.location

import com.github.caay2000.trains.Configuration
import com.github.caay2000.trains.debug

internal class Population {

    private val city: City
    var population: Int
        private set

    private var populationDelta: Float = (1..1000).random().toFloat().div(1000)

    constructor(
        city: City,
        population: Int
    ) {
        this.city = city
        this.population = population
    }

    fun update(delta: Float) {
        val deliveredCargoGrow = city.consumption.growPopulation()
        populationDelta += delta * cityGrowingRatio() + deliveredCargoGrow
        val grow = this.populationDelta.toInt()
        this.population += grow
        this.populationDelta -= this.populationDelta.toInt()

        debug(grow > 0) { "POPULATION ${city.name} increased by $grow to $population" }
    }

    private fun cityGrowingRatio() =
        if (city.connected()) Configuration.cityRatioGrowingPopulationConnected
        else Configuration.cityRatioGrowingPopulationNotConnected
}