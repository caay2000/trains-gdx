package com.github.caay2000.trains.world.`object`.location

import com.github.caay2000.trains.world.`object`.entity.CargoType
import com.github.caay2000.trains.world.position.Position
import kotlin.random.Random

object LocationSupport {

    fun randomLocation(): Location = TestLocation(randomPosition())

    fun randomLocationWithDemands(vararg types: CargoType): Location {
        val sut = TestLocation(randomPosition())
        types.forEach { sut.addDemand(it) }
        return sut
    }

    fun randomLocationWithProduction(production: List<Pair<CargoType, Int>>): Location {
        val sut = TestLocation(randomPosition())
        production.forEach{ sut.increaseProduction(it.first, it.second) }
        return sut
    }

    private fun randomPosition(): Position =
        Position(Random.nextInt(-20, 20), Random.nextInt(-20, 20))

    class TestLocation(position: Position) : Location, AbstractLocation(position) {
        override fun update(delta: Float) {}
    }
}