package com.github.caay2000.trains.world.`object`.entity.strategy

import com.github.caay2000.trains.world.`object`.entity.Entity
import com.github.caay2000.trains.world.`object`.entity.Wagon
import com.github.caay2000.trains.world.company.Money
import com.github.caay2000.trains.world.company.toMoney
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.math.max
import kotlin.math.min

class UnloadStrategy(private val entity: Entity) {

    private val logger: Logger = LoggerFactory.getLogger(UnloadStrategy::class.java)

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
                val amount = processIncome(unloadVolume)
                logger.info("UNLOAD entity[${entity.id}] ${it.model.cargoType} unloaded $unloadVolume, remaining in wagon ${it.load}, amount received $amount")
            }
        return max(maxTimeUnloading, 0f)
    }

    private fun processIncome(unloadVolume: Int): Money {
        val amount = calculateValue(unloadVolume)
        this.entity.company.balance.income(amount)
        return amount
    }

    private fun calculateValue(unloadVolume: Int) = unloadVolume.times(100).toMoney()

    private fun unloadWagon(it: Wagon, unloadVolume: Int) {
        it.load -= unloadVolume
    }

    private fun maxUnloadVolume(delta: Float, it: Wagon): Int {
        val ratio = delta / it.model.loadTimeUnit
        return min((it.model.capacity * ratio).toInt(), it.load)
    }
}

