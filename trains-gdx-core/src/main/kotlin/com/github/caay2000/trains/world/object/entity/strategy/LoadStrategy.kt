package com.github.caay2000.trains.world.`object`.entity.strategy

import com.github.caay2000.trains.world.`object`.entity.Entity

class LoadStrategy(private val entity: Entity) {

    fun update(
        delta: Float
    ): Float {

        entity.route.update()
        return delta
    }
}
