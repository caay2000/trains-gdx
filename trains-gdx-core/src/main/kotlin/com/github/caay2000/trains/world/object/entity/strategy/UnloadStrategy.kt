package com.github.caay2000.trains.world.`object`.entity.strategy

import com.github.caay2000.trains.world.`object`.entity.Entity

class UnloadStrategy(private val entity: Entity) {

    fun update(delta: Float): Float {

        val stop = entity.route.currentStop()

        entity.wagons.filter { e -> stop.demand(e.model.cargoType) }



        return delta
    }
}

