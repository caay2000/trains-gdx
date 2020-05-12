package com.github.caay2000.trains.world.aaa

interface WorldEntity {

    fun position(): Position
    fun connected(): Boolean
    fun distanceTo(to: WorldEntity): Float
    fun move(to: WorldEntity, distance: Float)
}