package com.github.caay2000.trains.world.`object`.location

import com.github.caay2000.trains.world.`object`.WorldObject
import com.github.caay2000.trains.world.`object`.entity.CargoType
import com.github.caay2000.trains.world.position.Position

interface Location : WorldObject {

    val position: Position
    val locationsInRange: Set<Location>
    val connections: Set<Location>

    fun connected(): Boolean = this.connections.isNotEmpty()
    fun distanceTo(to: Location): Float = position.distanceTo(to.position)

    //TODO should be created with constructor
    fun addLocationInRange(location: Location) = (this.locationsInRange as MutableSet).add(location)
    fun addConnection(location: Location) = (this.connections as MutableSet).add(location)

    fun demands(): Set<CargoType>
    fun demand(type: CargoType): Boolean
    fun addDemand(type: CargoType)
    fun unloadCargo(type: CargoType, load: Int)
    fun unloadedCargo(type: CargoType): Int
    fun consumeDemand(type: CargoType, load: Int): Boolean
}