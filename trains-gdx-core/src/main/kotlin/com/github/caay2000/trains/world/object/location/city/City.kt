package com.github.caay2000.trains.world.`object`.location.city

import com.github.caay2000.trains.Configuration.cityRatioPopulationPAX
import com.github.caay2000.trains.debug
import com.github.caay2000.trains.world.`object`.entity.CargoType
import com.github.caay2000.trains.world.`object`.location.AbstractLocation
import com.github.caay2000.trains.world.position.Position

class City : AbstractLocation {

    val name: String
    private val population: Population

    private var production: Int = 0
    private var productionDelta: Float = (1..1000).random().toFloat().div(1000)

    constructor(position: Position, name: String, population: Int) :
        super(position) {

        this.name = name
        this.population = Population(this, population)
    }

    val size: Int
        get() = this.population.population

    override fun update(delta: Float) {

        population.update(delta)


        productionDelta += size * delta * cityRatioPopulationPAX
        val grow = this.productionDelta.toInt()
        this.production += grow
        this.productionDelta -= this.productionDelta.toInt()
        increaseProduction(CargoType.PAX, grow)

        // debug(grow > 0) { "PRODUCTION PAX ${name}[${id}] increased by $grow to ${produces()[CargoType.PAX]}" }
    }
}
