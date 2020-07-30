package com.github.caay2000.trains.world.`object`.entity.strategy

import com.github.caay2000.trains.info
import com.github.caay2000.trains.world.`object`.entity.Entity

class MoveStrategy(private val entity: Entity) {

    // TODO can handle acceleration

    fun update(delta: Float): Float {

        val missing = (entity.maxSpeed * delta) - entity.position.distanceTo(entity.route.currentStop().position)
        return if (missing <= 0) {
            move(delta)
        } else {
            stationStop(delta, missing)
        }
    }

    private fun stationStop(delta: Float, missing: Float): Float {
        entity.position.translate(entity.route.currentStop().position)
        return delta * missing / (entity.maxSpeed * delta)
    }

    private fun move(delta: Float): Float {
        entity.position.move(entity.route.currentStop().position, entity.maxSpeed * delta)
        return 0f
    }
}
