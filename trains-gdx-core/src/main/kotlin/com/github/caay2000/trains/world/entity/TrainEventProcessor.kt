package com.github.caay2000.trains.world.entity

import com.github.caay2000.trains.Configuration
import com.github.caay2000.trains.event.EntityStationReadyToUnloadEvent
import com.github.caay2000.trains.event.EntityStationStationReadyEvent
import com.github.caay2000.trains.event.EntityStationStationStopEvent
import com.github.caay2000.trains.event.EventBus

class TrainEventProcessor {

    fun move(entity: Entity, delta: Float) {

        val deltaRemainder = this.moveEntity(entity, delta)
        if (deltaRemainder > 0) {
            EventBus.dispatchMessage(message = EntityStationStationStopEvent(entity), delta = deltaRemainder)
        }
    }

    private fun moveEntity(entity: Entity, delta: Float): Float {
        val missing = (entity.speed * delta) - entity.position.distanceTo(entity.route.end.position)
        return if (missing <= 0) {
            entity.position.move(entity.route.end.position, entity.speed * delta)
            0f
        } else {
            entity.position.translate(entity.route.end.position)
            delta * missing / (entity.speed * delta)
        }
    }

    fun stationStop(entity: Entity, delta: Float) {

        entity.status = Entity.EntityStatus.STATION_STOP
        EventBus.dispatchMessage(message = EntityStationReadyToUnloadEvent(entity), delta = delta, delay = Configuration.stationStopTime)
    }

    fun startUnloading(entity: Entity, delta: Float) {

        val location = entity.getCurrentStation()
        location.loadEntity(entity, delta)



        // EventBus.dispatchMessage(message = EntityStationStationReadyEvent(entity), delta = delta)
    }

    fun stationLeave(entity: Entity, delta: Float) {
        entity.status = Entity.EntityStatus.MOVING
        entity.route.endRoute()
        entity.update(delta)
    }
}