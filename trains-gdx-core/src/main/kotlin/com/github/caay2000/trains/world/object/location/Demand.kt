package com.github.caay2000.trains.world.`object`.location

import com.github.caay2000.trains.world.`object`.entity.CargoType

class Demand {

    // constructor(vararg cargoType: CargoType) {
    //     for (type in cargoType) {
    //         addDemand(type)
    //     }
    // }

    private val demands = hashMapOf<CargoType, Int>()

    fun addDemand(cargoType: CargoType) {
        if (!list().contains(cargoType)) {
            this.demands[cargoType] = 0
        }
    }

    fun list(): Set<CargoType> = demands.keys

    fun cargo(type: CargoType): Int = demands[type] ?: 0

    fun unloadCargo(type: CargoType, load: Int) {
        if (list().contains(type)) {
            demands[type] = demands[type]!!.plus(load)
        }
    }

    fun consumeCargo(type: CargoType, load: Int): Boolean {
        if (cargo(type) >= load) {
            demands[type] = demands[type]!!.minus(load)
            return true
        }
        return false
    }
}
