package com.github.caay2000.trains.world.generator

import com.github.caay2000.trains.world.position.Position

object PositionGenerator {

    private var maxDistance = 100
    private var minDistance = 50

    private var x = 0
    private var y = 0

    fun withCenter(center: Position): PositionGenerator {
        this.x = center.x.toInt()
        this.y = center.y.toInt()
        return this
    }

    fun withMaxDistance(maxDistance: Int): PositionGenerator {
        this.maxDistance = maxDistance
        return this
    }

    fun withMinDistance(minDistance: Int): PositionGenerator {
        this.minDistance = minDistance
        return this
    }

    fun generate(): Position =
        Position(
            ((x - maxDistance..x - minDistance) + (x + minDistance..x + maxDistance)).random(),
            ((y - maxDistance..y - minDistance) + (y + minDistance..y + maxDistance)).random()
        )
}