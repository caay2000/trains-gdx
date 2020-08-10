package com.github.caay2000.trains.world.`object`.entity.strategy

import com.github.caay2000.trains.world.`object`.entity.CargoType
import com.github.caay2000.trains.world.`object`.entity.Entity
import com.github.caay2000.trains.world.`object`.entity.Wagon
import com.github.caay2000.trains.world.`object`.location.Location
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.math.max
import kotlin.math.min

class LoadStrategy(private val entity: Entity) {

    private val logger: Logger = LoggerFactory.getLogger(LoadStrategy::class.java)

    fun update(delta: Float): Float {

        var maxTimeLoading = delta

        val currentStop = entity.route.currentStop()
        val cargoTypes = demandedCargosInRoute(currentStop)

        entity.wagons.filter { e -> cargoTypes.contains(e.model.cargoType) }
            .forEach {
                val amountLoaded = currentStop.consumeProduction(it.model.cargoType, maxLoadVolume(delta, it))
                if (amountLoaded > 0) {
                    loadWagon(it, amountLoaded)
                    maxTimeLoading = min(maxTimeLoading, wagonLoadTime(delta, it, amountLoaded))
                    logger.info("LOAD entity[${entity.id}] ${it.model.cargoType} loaded $amountLoaded, actual load in wagon ${it.load}")
                }
            }
        return max(maxTimeLoading, 0f)
    }

    private fun demandedCargosInRoute(currentStop: Location): Set<CargoType> {
        val nextStopsDemands = entity.route.remainingStops()
            .flatMap { location -> location.demands() }.toSet()

        return currentStop.produces().keys
            .filter { type -> nextStopsDemands.contains(type) }
            .toSet()
    }

    private fun loadWagon(it: Wagon, loaded: Int) {
        it.load = it.load.plus(loaded)
    }

    private fun maxLoadVolume(delta: Float, it: Wagon): Int {
        val ratio = delta / it.model.loadTimeUnit
        return min((it.model.capacity * ratio).toInt(), it.model.capacity - it.load)
    }

    private fun wagonLoadTime(delta: Float, it: Wagon, loaded: Int) =
        delta - (it.model.loadTimeUnit / (it.model.capacity / loaded))
}
