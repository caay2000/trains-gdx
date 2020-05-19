package com.github.caay2000.trains.world.entity

import com.github.caay2000.trains.DelayedCounter
import com.github.caay2000.trains.world.Position

class Train {

    private val eventHandler = TrainEventHandler()
    private val speed: Float
    private val route: Route
    private var wagons: List<Wagon>
    val position: Position

    private var status: EntityStatus = EntityStatus.ON_ROUTE

    constructor(speed: Float, route: Route, wagons: List<Wagon>) {
        this.speed = speed
        this.route = route
        this.wagons = wagons
        this.position = Position(route.start.position)
    }

    fun update(delta: Float) {
        eventHandler.handle(this, delta)
    }

    private fun move(delta: Float): Float {

        val missing = (this.speed * delta) - position.distanceTo(route.end.position)
        return if (missing <= 0) {
            position.move(route.end.position, speed * delta)
            0f
        } else {
            position.translate(route.end.position)
            delta * missing / (speed * delta)
        }
    }

    private fun loadWagons() {
        val station = route.end
        for (wagon in wagons) {
            station.loadWagon(wagon)
        }
    }

    private fun unloadWagons() {

        val station = route.end
        for (wagon in wagons) {
            station.unloadWagon(wagon)
        }
    }

    enum class EntityStatus {
        ON_ROUTE,
        STATION_STOP,
        UNLOADING,
        LOADING
    }

    private class TrainEventHandler {

        private val stationStopDelay = DelayedCounter(1f)

        fun handle(train: Train, delta: Float) {
            if (delta > 0) {
                when (train.status) {
                    EntityStatus.ON_ROUTE -> {
                        val result = train.move(delta)
                        if (result > 0) {
                            train.status = EntityStatus.STATION_STOP
                            this.handle(train, result)
                        }
                    }
                    EntityStatus.STATION_STOP -> {
                        val result = stationStopDelay.update(delta)
                        if (result > 0) {
                            train.status = EntityStatus.UNLOADING
                            this.handle(train, result)
                        }
                    }
                    EntityStatus.UNLOADING -> {
                        train.unloadWagons()
                        train.status = EntityStatus.LOADING
                        this.handle(train, delta)
                    }
                    EntityStatus.LOADING -> {
                        train.loadWagons()
                        train.route.endRoute()
                        train.status = EntityStatus.ON_ROUTE
                        this.handle(train, delta)
                    }
                }
            }
        }
    }
}