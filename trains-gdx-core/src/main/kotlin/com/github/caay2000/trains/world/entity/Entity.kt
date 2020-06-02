package com.github.caay2000.trains.world.entity

import com.github.caay2000.trains.event.EntityMoving
import com.github.caay2000.trains.event.EntityStationStationStopEvent
import com.github.caay2000.trains.event.EventBus
import com.github.caay2000.trains.world.Position
import com.github.caay2000.trains.world.WorldObject
import com.github.caay2000.trains.world.location.City

class Entity : WorldObject {

    internal val speed: Float
    internal val route: Route
    internal val wagons: List<Wagon>

    internal var status: EntityStatus
    val position: Position

    constructor(speed: Float, route: Route, wagons: List<Wagon> = mutableListOf()) {
        this.speed = speed
        this.route = route
        this.wagons = wagons
        this.position = Position(route.end.position)
        this.status = EntityStatus.STATION_STOP
        EventBus.dispatchMessage(EntityStationStationStopEvent(this))
    }

    override fun update(delta: Float) {
        when (status) {
            EntityStatus.MOVING -> EventBus.dispatchMessage(EntityMoving(this), delta)
        }
    }

    override fun toString(): String {
        return "Entity(id=$id)"
    }

    fun getCurrentStation(): City {
        if (this.route.end.position == this.position) {
            return this.route.end
        } else throw RuntimeException("incorrect call")
    }

    enum class EntityStatus {
        MOVING,
        STATION_STOP
    }
}