package com.github.caay2000.trains.world

import java.util.UUID

abstract class WorldObject {
    val id: UUID = UUID.randomUUID()

    abstract fun update(delta: Float)
}