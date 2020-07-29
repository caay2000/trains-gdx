package com.github.caay2000.trains.world.`object`.location

import com.github.caay2000.trains.world.`object`.AbstractWorldObject
import com.github.caay2000.trains.world.`object`.WorldObject
import com.github.caay2000.trains.world.`object`.entity.CargoType
import com.github.caay2000.trains.world.position.Position

abstract class AbstractLocation(
    override val position: Position
) : Location, WorldObject, AbstractWorldObject() {

    override val locationsInRange: Set<Location> = mutableSetOf()
    override val connections: Set<Location> = mutableSetOf()

    private val demand = Demand()

    override fun demands() = this.demand.list()
    override fun demand(type: CargoType) = this.demands().contains(type)
    override fun addDemand(type: CargoType) = this.demand.addDemand(type)
    override fun unloadCargo(type: CargoType, load: Int) = this.demand.unloadCargo(type, load)
    override fun unloadedCargo(type: CargoType) = this.demand.cargo(type)
    override fun consumeDemand(type: CargoType, load: Int) = this.demand.consumeCargo(type, load)
}