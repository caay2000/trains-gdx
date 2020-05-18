package com.github.caay2000.trains.world.location

import com.github.caay2000.trains.world.CargoType

class Consumption {

    private val city: City
    private val consumed: Map<CargoType, Int> = mutableMapOf()

    constructor(city: City) {
        this.city = city
    }

    fun put(type: CargoType, units: Int) {
        (consumed as MutableMap)[type] = units
    }
}
