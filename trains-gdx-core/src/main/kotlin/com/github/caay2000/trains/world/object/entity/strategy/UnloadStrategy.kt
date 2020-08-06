package com.github.caay2000.trains.world.`object`.entity.strategy

import com.github.caay2000.trains.debug
import com.github.caay2000.trains.world.`object`.entity.Entity
import com.github.caay2000.trains.world.`object`.entity.Wagon
import kotlin.math.max
import kotlin.math.min

class UnloadStrategy(private val entity: Entity) {

    fun update(delta: Float): Float {

        var maxTimeUnloading = delta
        val stop = entity.route.currentStop()
        entity.wagons
            .filter { e -> stop.demand(e.model.cargoType) && e.load > 0 }
            .forEach {
                val unloadVolume = maxUnloadVolume(delta, it)
                stop.unloadCargo(it.model.cargoType, unloadVolume)
                unloadWagon(it, unloadVolume)
                maxTimeUnloading = min(maxTimeUnloading, delta - it.model.loadTimeUnit)
                // debug { "UNLOAD entity[${entity.id}] ${it.model.cargoType} unloaded $unloadVolume, remaining in wagon ${it.load}" }
            }
        return max(maxTimeUnloading, 0f)
    }

    private fun unloadWagon(it: Wagon, unloadVolume: Int) {
        it.load -= unloadVolume
    }

    private fun maxUnloadVolume(delta: Float, it: Wagon): Int {
        val ratio = delta / it.model.loadTimeUnit
        return min((it.model.capacity * ratio).toInt(), it.load)
    }
}

