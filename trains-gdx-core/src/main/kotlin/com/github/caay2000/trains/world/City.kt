package com.github.caay2000.trains.world

import com.github.caay2000.trains.Configuration
import com.github.caay2000.trains.debug

class City(val name: String, override val position: Position, population: Int) : Location {

    override val locationsInRange: Set<Location> = mutableSetOf()
    override val connections: Set<Location> = mutableSetOf()

    override val offer: Map<CargoType, Cargo> = hashMapOf()
    override val demand: Set<CargoType> = mutableSetOf(CargoType.PAX, CargoType.MAIL)

    var population: Int = population
        private set

    private var populationDelta: Float = (1..1000).random().toFloat().div(1000)
    private var paxDelta: Float = 0f

    override fun update(delta: Float) {

        increaseOffer(delta)
        increasePopulation(delta)
    }

    private fun increaseOffer(delta: Float) {

        if (connected()) {
            val cargo = (this.offer as MutableMap).getOrPut(CargoType.PAX) { Cargo(CargoType.PAX) }
            if (cargo.quantity < this.population * Configuration.cityMaxRatioPAXPopulation) {
                this.paxDelta += this.population * delta * Configuration.cityRatioPopulationPAX
                if (this.paxDelta.toInt() > 0) {
                    val grow = this.paxDelta.toInt()
                    cargo.add(grow)
                    this.paxDelta -= this.paxDelta.toInt()
                    // debug(grow > 0) { "$name pax available increased by $grow to $cargo" }
                }
            }
        }
    }

    fun deliverCargo(delivered: Int) {
        population += delivered
    }

    private fun increasePopulation(delta: Float) {
        populationDelta += delta * cityGrowingRatio()
        val grow = this.populationDelta.toInt()
        this.population += grow
        this.populationDelta -= this.populationDelta.toInt()

        // debug(grow > 0) { "$name populationIncreased by $grow to $population" }
    }

    private fun cityGrowingRatio() =
        if (connected()) Configuration.cityRatioGrowingPopulationConnected
        else Configuration.cityRatioGrowingPopulationNotConnected
}
