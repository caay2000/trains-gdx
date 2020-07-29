package com.github.caay2000.trains.world.`object`.location.city

import com.github.caay2000.trains.world.position.Position
import com.github.caay2000.trains.world.`object`.location.AbstractLocation

class City : AbstractLocation {

    val name: String
    private val population: Population

    constructor(position: Position, name: String, population: Int) :
        super(position) {

        this.name = name
        this.population = Population(this, population)
    }

    val size: Int
        get() = this.population.population

    override fun update(delta: Float) {

        population.update(delta)
    }
}
