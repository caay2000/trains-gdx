package com.github.caay2000.trains.world.entity

import com.github.caay2000.trains.Configuration
import com.github.caay2000.trains.DelayedCounter
import com.github.caay2000.trains.world.Position
import com.github.caay2000.trains.world.Route
import org.koin.core.KoinComponent

class Train(private val speed: Float, private val route: Route) : Entity, KoinComponent {

    private val eventHandler = TrainEventHandler()

    override val position: Position = Position(route.start.position)
    private var status: EntityStatus = EntityStatus.ON_ROUTE

    override fun update(delta: Float) {
        eventHandler.handle(this, this.status, delta)
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

    enum class EntityStatus {
        ON_ROUTE,
        STATION_STOP,
        UNLOADING,
        LOADING
    }

    private class TrainEventHandler {

        private val stationStopDelay = DelayedCounter(Configuration.stationStopTime)

        fun handle(train: Train, status: EntityStatus, delta: Float) {
            if (delta > 0) {
                when (status) {
                    EntityStatus.ON_ROUTE -> {
                        val result = train.move(delta)
                        if (result > 0) {
                            this.handle(train, EntityStatus.STATION_STOP, result)
                        }
                    }
                    EntityStatus.STATION_STOP -> {
                        val result = stationStopDelay.update(delta)
                        if (result > 0) {
                            train.route.endRoute()
                            this.handle(train, EntityStatus.ON_ROUTE, result)
                        }
                    }
                    EntityStatus.UNLOADING -> TODO()
                    EntityStatus.LOADING -> TODO()
                }
            }
        }
    }
}