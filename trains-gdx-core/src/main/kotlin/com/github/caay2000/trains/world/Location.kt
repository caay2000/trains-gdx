package com.github.caay2000.trains.world

interface Location {

    val position: Position
    val locationsInRange: Set<Location>
    val connections: Set<Location>
    fun addLocationInRange(location: Location) = (this.locationsInRange as MutableSet).add(location)

    fun connected(): Boolean = this.connections.isNotEmpty()
    fun addConnection(location: Location) = (this.connections as MutableSet).add(location)
    fun distanceTo(to: Location): Float = position.distanceTo(to.position)

    fun update(delta: Float)
}