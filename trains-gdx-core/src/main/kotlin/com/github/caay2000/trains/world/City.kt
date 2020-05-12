package com.github.caay2000.trains.world

class City : Location {

    override val position: Position
    override val locationsInRange: Set<Location> = mutableSetOf()
    override val connections: Set<Location> = mutableSetOf()
    val population: Int

    constructor(position: Position, population: Int) {
        this.position = position
        this.population = population
    }

    override fun update(delta: Float) {
    }
}
