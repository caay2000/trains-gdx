package com.github.caay2000.trains.world.`object`

import java.util.UUID

abstract class AbstractWorldObject : WorldObject {

    final override val id: UUID = UUID.randomUUID()
}