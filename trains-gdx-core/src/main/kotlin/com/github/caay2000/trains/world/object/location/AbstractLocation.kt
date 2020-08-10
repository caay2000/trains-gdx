package com.github.caay2000.trains.world.`object`.location

import com.github.caay2000.trains.world.`object`.AbstractWorldObject
import com.github.caay2000.trains.world.`object`.WorldObject
import com.github.caay2000.trains.world.`object`.entity.CargoType
import com.github.caay2000.trains.world.position.Position

abstract class AbstractLocation(
    override val position: Position
) : Location, WorldObject, AbstractWorldObject() {

    final override val locationsInRange: Set<Location> = mutableSetOf()
    final override val connections: Set<Location> = mutableSetOf()

    private val demand = Demand()
    private val offer = Offer()

    final override fun demands() = this.demand.list()
    final override fun demand(type: CargoType) = this.demands().contains(type)
    final override fun addDemand(type: CargoType) = this.demand.addDemand(type)
    final override fun unloadCargo(type: CargoType, load: Int) = this.demand.unloadCargo(type, load)
    final override fun unloadedCargo(type: CargoType) = this.demand.cargo(type)
    final override fun consumeDemand(type: CargoType, load: Int) = this.demand.consumeCargo(type, load)

    final override fun produces() = offer.map()
    final override fun increaseProduction(type: CargoType, load: Int) = offer.add(type, load)
    final override fun consumeProduction(type: CargoType, load: Int) = offer.consume(type, load)
}