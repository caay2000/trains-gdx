package com.github.caay2000.trains.world

interface Location {

    val position: Position
    val locationsInRange: Set<Location>
    val connections: Set<Location>
    val offer: Map<CargoType, Cargo>
    val demand: Set<CargoType>

    fun connected(): Boolean = this.connections.isNotEmpty()
    fun distanceTo(to: Location): Float = position.distanceTo(to.position)
    fun addLocationInRange(location: Location) = (this.locationsInRange as MutableSet).add(location)
    fun addConnection(location: Location) = (this.connections as MutableSet).add(location)

    fun update(delta: Float)
}