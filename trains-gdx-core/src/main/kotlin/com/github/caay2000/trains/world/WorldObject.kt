package com.github.caay2000.trains.world

import com.github.caay2000.trains.common.Position

interface WorldObject {
    val position: Position
    fun distanceTo(to: WorldObject): Float
}