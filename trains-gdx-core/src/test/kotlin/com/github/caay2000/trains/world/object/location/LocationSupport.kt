package com.github.caay2000.trains.world.`object`.location

import com.github.caay2000.trains.world.`object`.entity.CargoType
import com.github.caay2000.trains.world.`object`.entity.EntitySupport
import com.github.caay2000.trains.world.position.Position
import kotlin.random.Random

object LocationSupport {

    fun randomLocation(): Location = EntitySupport.TestLocation(randomPosition())
    fun randomLocation(position: Position = Position()) = TestLocation(position)
    fun randomLocation(vararg types: CargoType): Location {
        val sut = EntitySupport.TestLocation(randomPosition())
        for (type in types) {
            sut.addDemand(type)
        }
        return sut
    }

    fun randomPosition(): Position =
        Position(Random.nextInt(-20, 20), Random.nextInt(-20, 20))

    class TestLocation(position: Position) : Location, AbstractLocation(position) {
        override fun update(delta: Float) {}
    }
}