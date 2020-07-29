package com.github.caay2000.trains.world.`object`

import java.util.UUID

interface WorldObject {

    val id: UUID

    fun update(delta: Float)
}
