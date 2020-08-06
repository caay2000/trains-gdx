package com.github.caay2000.trains.world.`object`.location

import com.github.caay2000.trains.world.`object`.entity.CargoType
import kotlin.math.min

internal class Offer {

    private val offers = mutableMapOf<CargoType, Int>()

    fun map(): Map<CargoType, Int> = offers

    fun add(type: CargoType, load: Int) {
        offers[type] = (offers[type] ?: 0) + load
    }

    fun consume(type: CargoType, load: Int): Int {
        if (!offers.containsKey(type)) {
            return 0
        }
        val loaded = min(offers[type]!!, load)
        offers[type] = offers[type]!!.minus(loaded)
        return loaded
    }
}
